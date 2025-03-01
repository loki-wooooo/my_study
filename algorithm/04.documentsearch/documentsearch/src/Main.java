import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {


        /**
         * 문제: 주어진 단어가 문서에 등장하는 횟수
         * - 문서의 지금 글자부터 주어진 단어와 한글자씩 비교한다.
         * - 단어와 완전히 일치하면 개수를 올린다.
         * - 해당 단어가 등장한 이후부터 2를 반복한다.
         *
         * - 단어와 매치되지 않으면, 다음 글자에서 2를 반복한다.
         *
         * ex) doc : aaaaaaaa
         *     word: aaa
         * */

        Scanner inputData = new Scanner(System.in);
        /**
         * next vs nextLine
         * 공백으로 받느냐 vs 라인으로 받느냐
         * */
        String doc = inputData.nextLine();
        String word = inputData.nextLine();


        //Case.1 반복문을 통해 답을 해결
//        Integer count = 0;
//        Integer startIndex = 0;
//        while (true) {
//            //index 리턴
//            Integer findIndex = doc.indexOf(word, startIndex);
//            //word의 문자값을 찾지 못했을때
//            if (findIndex < 0) {
//                break;
//            }
//            count++;
//
//            //찾은 index값 + 단어의 길이
//            startIndex = findIndex + word.length();
//        }

        //Case.2 replace를 통해 답을 해결
        String docReplace = doc.replace(word, "");
        //(원래 문서 길이 - 단어에 해당하는 내용이 지워진 문서길이) / 단어 길이
        int count = (doc.length() - docReplace.length()) / word.length();
        System.out.println(count);


//        Scanner sc = new Scanner(System.in);
//        // next 공백문자를 반환하기 전까지 문자열을 반환
//        // nextLine 개행문자를 기준으로 문자열을 한 문자열로 받음
//        String doc = sc.nextLine();
//        String word = sc.nextLine();
//
//        // case.1 indexof를 이용한 방법
////        int count = 0;
////        int startIndex = 0;
////        while (true) {
////            int findIndex = doc.indexOf(word, startIndex);
////            if (findIndex < 0) {
////                break;
////            }
////            count++;
////            // 지금 찾은 문자열의 길이만큼 뒤로 밀어줌
////            startIndex = findIndex + word.length();
////        }
////        System.out.println(count);
//
//        // case.2 replace 이용
//        String replaced = doc.replace(word, "");
//        int length = doc.length() - replaced.length();
//        int count = length / word.length();
//        System.out.println(count);
    }
}