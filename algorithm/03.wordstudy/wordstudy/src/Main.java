import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    /*
     * 문제 : 문자열에서 가장 많이 등장한 알파벳(대소문자 구분하지 않음)
     * 1. 각 알파벳의 갯수를 구함
     * 2. 그 중 최대값을 구함
     * */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // 대소문자 들어온 문자열을 대문자 or 소문자 통일
        String str = sc.next().toUpperCase();
        int[] count = getAlphabetCount(str);

        int maxCount = -1;
        char maxAlphabet = '?';
        for (int i = 0; i < count.length; i++) {
            if (count[i] > maxCount) {
                maxCount = count[i];
                maxAlphabet = (char) ('A' + i);
            } else if (count[i] == maxCount) {
                maxAlphabet = '?';
            }
        }

        System.out.println(maxAlphabet);
    }


    public static int[] getAlphabetCount(final String str) {
        int[] count = new int[26];
        for (int i = 0; i < str.length(); i++) {
            count[str.charAt(i) - 'A']++;
//            char ch = str.charAt(i);
//            // 대문자와 소문자 확인
//            if ('A' <= ch && ch <= 'Z') {
//                count[ch - 'A']++;
//            } else {
//                count[ch - 'a']++;
//            }
        }
        return count;
    }


//    public static int[] getAlphabetCount(final String str) {
//        int count[] = new int[26];
//        for (int i = 0; i < str.length(); i++) {
//            count[str.charAt(i) - 'A']++;
////            char ch = str.charAt(i);
////            if ('A' <= ch && ch <= 'Z') {
////                count[ch-'A']++;
////            } else {
////                count[ch-'a']++;
////            }
//        }
//        return count;
//    }
//
//
//    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        String str = sc.next().toUpperCase();
//        int count[] = getAlphabetCount(str);
//
////        // 최대값
////        int maxCount = -1;
////
////        // 많이 사용한 알파벳
////        int maxAlphabetIndex = -1;
////
////        for (int i = 0; i < count.length; i++) {
////            // 최대값 구하기
////            if (count[i] > maxCount) {
////                maxCount = count[i];
////                maxAlphabetIndex = i;
////            }
////        }
//
//        int maxCount = -1;
//        char maxAlphabet = '?';
//        for (int i = 0; i < count.length; i++) {
//            // 최대값 구하기
//            if (count[i] > maxCount) {
//                maxCount = count[i];
//                maxAlphabet = (char)('A' + i);
//            } else if(count[i] == maxCount) {
//                maxAlphabet = '?';
//            }
//        }
//
//        System.out.println((char) ('A' + maxAlphabet));
//
//
//    }
}