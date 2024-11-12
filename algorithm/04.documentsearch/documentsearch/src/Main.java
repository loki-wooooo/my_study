import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // next 공백문자를 반환하기 전까지 문자열을 반환
        // nextLine 개행문자를 기준으로 문자열을 한 문자열로 받음
        String doc = sc.nextLine();
        String word = sc.nextLine();

        // case.1 indexof를 이용한 방법
//        int count = 0;
//        int startIndex = 0;
//        while (true) {
//            int findIndex = doc.indexOf(word, startIndex);
//            if (findIndex < 0) {
//                break;
//            }
//            count++;
//            // 지금 찾은 문자열의 길이만큼 뒤로 밀어줌
//            startIndex = findIndex + word.length();
//        }
//        System.out.println(count);

        // case.2 replace 이용
        String replaced = doc.replace(word, "");
        int length = doc.length() - replaced.length();
        int count = length / word.length();
        System.out.println(count);
    }
}