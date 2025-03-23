import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        /**
         * 문제 : 10진법 수 N이 주어진다. 이 수를 B진법으로 바꿔 출력하는 프로그램을 작성하시오. 10진법을 넘어가는 진법은 숫자로 표시할 수 없는 자리가 있다. 이런 경우에는 다음과 같이 알파벳 대문자를 사용
         *
         * 풀이법 :
         *  100(10진수)
         *    - 1100100(2진수)
         *    - 10201(3진수)
         *    - 1210(4진수)
         *    - 400(5진수)
         *       :
         *       :
         *    - 2U(35진수)
         *    - 2S(36진수)
         *
         * 과정내용:
         *  1. N을 B진법으로 나타냈을때, 가장 큰 자릿수의 지수 K를 찾는다.
         *  2. N에 들어갈 수 있는 B^K항의 자릿값, D^K를 구한다.
         *  3. 다음 자릿수의 표현범위를 넘는 부분을 제외하고, 2로 돌아가 다음 자릿수를 구한다.
         *  4. 가장 큰 자리수의 자릿값을 구했으니, 그대로 출력함
         * */

        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt(); // 값
        int B = sc.nextInt(); // 지수

        // 1. N을 B로 나눈 나머지를 구하고, B로 나눔
        // 2. 이떄 가장 마지막 나머지로부터 가장 앞 자릿값이 된다.

        String ans = "";

        // case.1
//        while (N > 0) {
//            int D = N % B;
//            // 진법에 따라 나누면 몫으로 다시 나눠야함
//            N /= B; // N = N / B;
//
//            // 나머지에 대해서 진법 처리
//            if (D < 10) {
//                ans += D;
//            } else {
//                // 10진수 이상은 앏파벳 대문자로 표현
//                ans += (char) (D - 10 + 'A');
//            }
//        }
//
//        // 갖고온 값을 진법으로 표현
//        // 배열이기 떄문에 1부터 시작 "-1" 표기
//        for (int i = ans.length() - 1; i >= 0; i--) {
//            System.out.print(ans.charAt(i));
//        }
//        System.out.println();

        // case.2
        int basePower = 1;
        int K = 0;
        //입력받은 값에 진법에 따른 지수를 구함
        // B^K
        while ((long) basePower * B <= N) {
            basePower *= B;
            K++;
        }

        while (K >= 0) {
            int D = N / basePower;

            // 나눈후 몫의 나머지 or 입력받은 값에서 뺀 값 둘다 상관없음
            N %= basePower;
            basePower /= B;
            if (D < 10) {
                ans += D;
            } else {
                ans += (char) (D - 10 + 'A');
            }
            K--;
        }
        System.out.println(ans);


    }
}