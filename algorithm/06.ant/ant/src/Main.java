import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        /**
         * 시간 복잡도 : 입력크기와 알고리즘간의 관계
         * - 알고리즘의 복잡도를 나타내는 지표 중 하나
         * - 입력 크기에 대해 프로그램의 동작시간을 가늠해볼 수 있는 수단
         * ex)
         *  for(int i=0; i < str.length(); i++) {
         *      int alphabetIndex = str.charAt(i) - 'A';
         *      count[alphabetIndex] ++;
         *  }
         *
         *  BigO 계산법 : O(L)
         *
         * 문제 :  W X H 격자 공간에서 대각선으로 이동하는 개미의 T시간 후 위
         * 제한 :
         *     2 <= W, H <= 40000
         *     1 <= T <= 200000000
         * */

        Scanner sc = new Scanner(System.in);
        int w = sc.nextInt();
        int h = sc.nextInt();
        int p = sc.nextInt();
        int q = sc.nextInt();
        int t = sc.nextInt();

        // case1 p의 값에서 시작
//        int timeX = t % (2 * w);
//        int currentX = p;
//        int deltaX = 1;
//
//        while (timeX-- > 0) {
//            if (currentX == w) {
//                deltaX = -1;
//            } else if (currentX == 0) {
//                deltaX = 1;
//            }
//            currentX += deltaX;
//        }
//
//        int timeY = t % (2 * h);
//        int currentY = q;
//        int deltaY = 1;
//
//        while (timeY-- > 0) {
//            if (currentY == h) {
//                deltaY = -1;
//            } else if (currentY == 0) {
//                deltaY = 1;
//            }
//            currentY += deltaY;
//        }

        //case2 0의 값에서 시작
        int currentX = (t + p) % (2 * w);
        int currentY = (t + q) % (2 * h);
        if(currentX > w) {
            currentX = 2 * w - currentX;
        }
        if(currentY > h) {
            currentY = 2 * h - currentY;
        }

        System.out.println(currentX + " " + currentY);

//        /**
//         * 문제 : W X H 격자 공간에서 대각선으로 이동하는 개미의 T시간 후 위
//         * 제한 :
//         *  2 <= W, H <= 40000
//         *  1 <= T <= 200000000
//         * */
//
//        Scanner sc = new Scanner(System.in);
//        int w = sc.nextInt();
//        int h = sc.nextInt();
//        int p = sc.nextInt();
//        int q = sc.nextInt();
//        int t = sc.nextInt();
//
//        int timeX = t % (2 * w);
//        int currentX = p;
//        int deltaX = 1;
//        while (timeX-- > 0) {
//            if (currentX == w) deltaX = -1;
//            else if (currentX == 0) deltaX = 1;
//            currentX += deltaX;
//        }
//
//
//        int timeY = t % (2 * h);
//        int currentY = q;
//        int deltaY = 1;
//        while (timeY-- > 0) {
//            if (currentY == h) deltaY = -1;
//            else if (currentY == 0) deltaY = 1;
//            currentY += deltaY;
//        }
//
//        System.out.println(currentX + " " + currentY);

    }
}