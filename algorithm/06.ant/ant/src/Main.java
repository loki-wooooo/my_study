import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        /**
         * 문제 : W X H 격자 공간에서 대각선으로 이동하는 개미의 T시간 후 위
         * 제한 :
         *  2 <= W, H <= 40000
         *  1 <= T <= 200000000
         * */

        Scanner sc = new Scanner(System.in);
        int w = sc.nextInt();
        int h = sc.nextInt();
        int p = sc.nextInt();
        int q = sc.nextInt();
        int t = sc.nextInt();

        int timeX = t % (2 * w);
        int currentX = p;
        int deltaX = 1;
        while (timeX-- > 0) {
            if (currentX == w) deltaX = -1;
            else if (currentX == 0) deltaX = 1;
            currentX += deltaX;
        }


        int timeY = t % (2 * h);
        int currentY = q;
        int deltaY = 1;
        while (timeY-- > 0) {
            if (currentY == h) deltaY = -1;
            else if (currentY == 0) deltaY = 1;
            currentY += deltaY;
        }

        System.out.println(currentX + " " + currentY);

    }
}