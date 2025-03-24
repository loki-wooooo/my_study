import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    // index를 1000까지 사용함
    static boolean[] isEurekaNumber = new boolean[1001];


    public static void preprocess() {

        //1. K보다 작은 삼각수를 구함.
        int[] triangleNumbers = new int[50]; // 삼각수의 최대는 1000
        int triangleNumberCount = 0;

        // 무한루프를 이용해서 1000 이상이면 그냥 끝냄
        for (int i = 0; ; i++) {
            int triangleNumber = i * (i + 1) / 2;
            if (triangleNumber >= 1000) {
                break;
            }
            triangleNumbers[triangleNumberCount++] = triangleNumber;
        }

        //2. 구해진 삼각수 합으로 K를 나타낼 수 있는지 확인.

        //case.1 for문 3번
        for (int i = 0; i < triangleNumberCount; i++) {
            for (int j = 0; j < triangleNumberCount; j++) {
                for (int k = 0; k < triangleNumberCount; k++) {
                    int sum = triangleNumbers[i] + triangleNumbers[j] + triangleNumbers[k];
                    if (sum <= 1000) {
                        isEurekaNumber[sum] = true;
                    }
                }
            }
        }

        //case.2 두수의 합을 더한 후, 나머지의 값을 더함
//        //두개의 합에서 나머지를 더함.
//        boolean[] isSumOfTriangleNumber = new boolean[1000];
//        for (int i = 0; i < triangleNumberCount; i++) {
//            for (int j = 0; j < triangleNumberCount; j++) {
//                int sum = triangleNumbers[i] + triangleNumbers[j];
//                if (sum < 1000) {
//                    isSumOfTriangleNumber[sum] = true;
//                }
//            }
//        }
//
//        for (int i = 0; i < 1000; i++) {
//            // 두수의합이 삼각수의 값인 1000이 넘으면 볼 이유가 없음
//            if (!isSumOfTriangleNumber[i]) {
//                continue;
//            }
//            // 이후 삼각수의 합을 체그함
//            for (int j = 0; j < triangleNumberCount; j++) {
//                int sum = i + triangleNumbers[j];
//                if (sum <= 1000) {
//                    isEurekaNumber[sum] = true;
//                }
//            }
//        }


    }


    public static void main(String[] args) {

        /**
         * 문제
         *  삼각수 T^n = N(N+1) / 2 로 정의될 때,
         *  주어진 K가 정확히 3개의 삼각수의 합으로 표현될 수 있는가?
         *  * 입력받는 K는  K (3 ≤ K ≤ 1,000) 사이
         *
         *  1. 모든 삼각수를 구한다.
         *  2. 주어진 숫자를 세 개의 삼각의 합으로 표현할 수 있는지 확인한다.
         * */

        // [3,1000]
        // isEurekaNumber[]
        preprocess();

        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        while (T-- > 0) {
            int K = sc.nextInt();

            System.out.println(isEurekaNumber[K] ? "1" : "0");
        }


    }

}