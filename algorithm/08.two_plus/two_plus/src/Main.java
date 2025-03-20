import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        /**
         * 문제: 서로 다른 양의 정수로 이루어진 수열에서 두 수의 합이 X가 되는 쌍의 개수
         * ex)
         *          7 -> 7개의 정수를 받음
         *          1 2 3 4 5 6 7
         *          8 -> 두수의 합이 8
         *          -> 8이 되는 값의 총 개수
         *          1 + 7
         *          2 + 6
         *          3 + 4
         * */
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] a = new int[N];
        for (int i = 0; i < N; i++) {
            a[i] = sc.nextInt();
        }
        int X = sc.nextInt();

        boolean[] exist = new boolean[10000001];
        for (int i = 0; i < N; i++) {
            // 값이 들어온 배열에 true를 저장함
            exist[a[i]] = true;
        }

        int ans = 0;
        // case1
//        for (int i = 0; i < N; i++) {
//            int pairValue = X - a[i];
//            if (0 <= pairValue && pairValue <= 1000000) {
//                ans += exist[pairValue] ? 1 : 0; // 존재하는 값이 있다면 true 없다면, false
//            }
//        }
//        System.out.println(ans / 2);

        // case2
        for (int i = 0; i <= (X - 1) / 2; i++) {
            if (i <= 1000000 && X - i <= 10000000) {
                ans += exist[i] && exist[X - i] ? 1 : 0;
            }
        }
        System.out.println(ans);
    }
}

