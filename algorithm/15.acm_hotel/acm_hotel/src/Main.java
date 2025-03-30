import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        /**
         * 문제: 엘리베이로부터 가까운 방 먼저, 같다면 아래층부터 배정할떄 N번쨰 손님에게 배정될 방 번호는?
         *
         * 1. 차례로 배정해보자.
         * 2. 한 번에 계산해보자.
         *
         *
         *
         * 4 9 14
         * 3 8 13
         * 2 7 12 .....
         * 1 6 11
         * 0 5 10
         * */
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        while (T-- > 0) {
            int H = sc.nextInt(); // 세로 (층)
            int W = sc.nextInt(); // 가로 (호)
            int N = sc.nextInt(); // 몇번쨰 손님


            /**
             * 111번째 손님 -> 11호
             * 112번쨰 손님 -> 12호
             * N/H로 하면? 같은 11호
             * 
             * */
            int distance = (N - 1) / H + 1;//같은 호수끼리 같은 몫
            int floor = (N - 1) % H + 1;//같은 층수끼리 같은 나머지
            System.out.printf("%d%02d\n", floor, distance);// 10자리이하는 "0" 붙여서
        }
    }
}