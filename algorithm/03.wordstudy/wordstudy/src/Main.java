import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public static int[] getAlphabetCount(final String str) {
        int count[] = new int[26];
        for (int i = 0; i < str.length(); i++) {
            count[str.charAt(i) - 'A']++;
//            char ch = str.charAt(i);
//            if ('A' <= ch && ch <= 'Z') {
//                count[ch - 'A']++;
//            } else {
//                count[ch - 'a']++;
//            }
        }
        return count;
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.next().toUpperCase();
        int count[] = getAlphabetCount(str);

//        // 최대값
//        int maxCount = -1;
//
//        // 많이 사용한 알파벳
//        int maxAlphabetIndex = -1;
//
//        for (int i = 0; i < count.length; i++) {
//            // 최대값 구하기
//            if (count[i] > maxCount) {
//                maxCount = count[i];
//                maxAlphabetIndex = i;
//            }
//        }

        int maxCount = -1;
        char maxAlphabet = '?';
        for (int i = 0; i < count.length; i++) {
            // 최대값 구하기
            if (count[i] > maxCount) {
                maxCount = count[i];
                maxAlphabet = (char)('A' + i);
            } else if(count[i] == maxCount) {
                maxAlphabet = '?';
            }
        }

        System.out.println((char) ('A' + maxAlphabet));


    }
}