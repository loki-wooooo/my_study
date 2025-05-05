package io.github.lokiwooooo.config;

import io.minio.MinioClient;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @author HSWoo
 * @version 0.0.1
 * @since 2025-02-25
 * <p>
 * MinIO Configuration
 */
@Configuration
@Slf4j
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class MinIOConfig {

    @Bean
    public MinioClient minioClient(
            @Value("${spring.minio.host}") String host,
            @Value("${spring.minio.port}") int port,
            @Value("${spring.minio.access-key}") String accessKey,
            @Value("${spring.minio.secret-key}") String secretKey
    ) {
        log.debug("Creating minIO Client");
        return MinioClient.builder()
                .endpoint(String.format("http://%s:%d", host, port))
                .credentials(accessKey, secretKey)
                .build();
    }

}
