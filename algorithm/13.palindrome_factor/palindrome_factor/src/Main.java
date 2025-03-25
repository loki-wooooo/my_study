import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        /**
         * 문제: 주어진 숫자가 [2, 64] 진법으로 표현했을때 회문이 될 수 있는가?
         *  1, 가능한 모든 진법에 대해 진법을 변환한다.
         *  2. 변환된 수가 회문이 될 수 있는지 확인한다.
         *
         * 회문
         *  - 거꾸로 읽어도 바로 읽은 것과 같은 문장이나 낱말, 숫자, 문자열 듯
         *  ex)
         *      ABCDCBA (뒤로, 앞으로 읽어도 동일함)
         * */

        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        //Case1
//        while (T-- > 0) {
//            int x = sc.nextInt();
//
//            Boolean ans = false;
//            for (int i = 2; i < 64; i++) {
//                int[] baseDigit = new int[20];
//                int baseLength = convertBase(x, i, baseDigit);
//                if (isPalindrome(baseDigit, baseLength)) {
//                    ans = true;
//                    break;
//                }
//            }
//            System.out.println(ans ? "1" : "0");
//        }

        //Case2
        while (T-- > 0) {
            int x = sc.nextInt();

            Boolean ans = false;
            for (int i = 2; i < 64; i++) {
                int[] baseDigit = convertBase(x, i);
                if (isPalindrome(baseDigit)) {
                    ans = true;
                    break;
                }
            }
            System.out.println(ans ? "1" : "0");
        }
    }

    // 배열에 대한 값을 임의로 선점하지않고, 나온내용을 기반으로 갖고옴
    public static int[] convertBase(int x, int n) {
        int len = 0;
        int copyX = x;

        while (copyX > 0) {
            // 진법으로 입력받은 값을 나눠서 0보다 클때까지 len값을 구함
            copyX = copyX / n;
            len++;
        }

        int[] digits = new int[len];
        len = 0;
        while (x > 0) {
            digits[len++] = x % n;
            x = x / n;
        }
        return digits;
    }

    private static boolean isPalindrome(int[] baseDigit) {
        for (int i = 0; i < baseDigit.length / 2; i++) {
            if (baseDigit[i] != baseDigit[baseDigit.length - i - 1]) {
                return true;
            }
        }
        return false;
    }


//    // 가능한 모든 진법에 대해 진법을 변환한다.
//    public static int convertBase(int x, int base, int[] reverseDigit) {
//        // 2진법기준으로 100만이 제일 크기때문에 배열을 좀더 크게 조정함.
//        int len = 0;
//        while (x > 0) {
//            reverseDigit[len++] = x % base;
//            x = x / base;
//        }
//        return len;
//    }
//
//    //변환된 수가 회문이 될 수 있는지 확인한다.
//    public static boolean isPalindrome(int[] digit, int len) {
//
//        for (int i = 0; i < len / 2; i++) {
//            // 가운데를 기점으로 대칭되는 값을 비교처리
//            if (digit[i] != digit[len - i - 1]) {
//                return false;
//            }
//        }
//        return true;
//    }

//    // 가능한 모든 진법에 대해 진법을 변환한다.
//    public static int[] convertBase(int x, int base) {
//        // 2진법기준으로 100만이 제일 크기때문에 배열을 좀더 크게 조정함.
//        int[] reverseDigit = new int[20];
//        int len = 0;
//        while (x > 0) {
//            reverseDigit[len++] = x % base;
//            x = x / base;
//        }
//        return reverseDigit;
//    }
//
//    //변환된 수가 회문이 될 수 있는지 확인한다.
//    public static boolean isPalindrome(int[] digit) {
//
//        for (int i = 0; i < digit.length / 2; i++) {
//            // 가운데를 기점으로 대칭되는 값을 비교처리
//            if (digit[i] != digit[digit.length - i - 1]) {
//                return false;
//            }
//        }
//        return true;
//    }


}