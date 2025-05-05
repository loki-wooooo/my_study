package io.github.lokiwooooo.files;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

@Slf4j
public class FileUtil {

    // static 메서드 사용을 위해 빈 생성자 생성 못하도록 변경
    private FileUtil() {
        throw new IllegalStateException("### util class :: FileUtil ###");
    }

    /**
     * 신규 파일 경로 생성
     */
    public static Boolean mkdir(final String path) throws Exception {
        Path directoryPath = Paths.get(path);

        try {
            if (Files.exists(directoryPath)) {
                return true;
            } else {
                // 디렉토리가 없으면 생성
                Files.createDirectories(directoryPath);
                log.info("### directory create: {} ###", directoryPath);
                return true;
            }
        } catch (IOException e) {
            log.error("### directory create failed: {} ###", e.getMessage());
            return false;
        }
    }

    /**
     * 파일 압출 풀기
     */
    public static Boolean unzip(final MultipartFile multipartFile, String parentDirectory) throws Exception {
        Boolean isUnZip = false;

        String originalFileName = multipartFile.getOriginalFilename();
        String folderName = originalFileName.substring(0, originalFileName.lastIndexOf('.'));
        String destDirectory = parentDirectory + File.separator + folderName;

        File destDir = new File(destDirectory);
        if (!destDir.exists()) {
            destDir.mkdirs();
        }

        try (ZipInputStream zipIn = new ZipInputStream(multipartFile.getInputStream())) {
            ZipEntry entry = zipIn.getNextEntry();
            while (entry != null) {
                String filePath = destDirectory + File.separator + entry.getName();
                if (!entry.isDirectory()) {
                    new File(filePath).getParentFile().mkdirs();
                    extractFile(zipIn, filePath);
                } else {
                    File dir = new File(filePath);
                    dir.mkdirs();
                }
                zipIn.closeEntry();
                entry = zipIn.getNextEntry();
            }

            // 내부 디렉토리의 내용을 상위 디렉토리로 이동
            File[] innerFiles = destDir.listFiles();
            if (innerFiles != null && innerFiles.length == 1 && innerFiles[0].isDirectory()) {
                File innerDir = innerFiles[0];
                for (File file : innerDir.listFiles()) {
                    Files.move(file.toPath(), Paths.get(destDirectory, file.getName()), StandardCopyOption.REPLACE_EXISTING);
                }
                // 빈 내부 디렉토리 삭제
                Path path = innerDir.toPath();
                Files.delete(path);
            }

            isUnZip = true;
        } catch (Exception e) {
            log.error("### unzip error :: {} ###", e.getMessage());
        }
        return isUnZip;
    }

    /**
     * 파일 디렉터리 확인하기
     */
    public static List<String> getSubDirectories(final String parentPath) throws Exception {
        List<String> returnList = new ArrayList<>();

        Path directory = Paths.get(parentPath);
        try (Stream<Path> subdirectories = Files.list(directory).filter(Files::isDirectory)) {
            subdirectories.forEach(dir -> returnList.add(dir.getFileName().toString()));
        } catch (IOException e) {
            log.error("### getSubDirectories error :: {} ###", e.getMessage());
        }
        return returnList;
    }

    private static void extractFile(ZipInputStream zipIn, String filePath) throws IOException {
        try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(filePath))) {
            byte[] bytesIn = new byte[4096];
            int read;
            while ((read = zipIn.read(bytesIn)) != -1) {
                bos.write(bytesIn, 0, read);
            }
        }
    }

    // 디렉토리 + 디렉토리 안의 파일 삭제
    public static Boolean deleteDirectory(String pathStr) throws IOException {

        Boolean isDeleted = false;
        try {
            Path path = Paths.get(pathStr);
            Files.walkFileTree(path, new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    Files.delete(file);
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                    Files.delete(dir);
                    return FileVisitResult.CONTINUE;
                }
            });
            isDeleted = true;

        } catch (Exception exception) {
            log.error("### deleteDirectory error :: {} ###", exception.getMessage());
        }
        return isDeleted;
    }

}
