import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        /**
         * 문제 : 입력으로 주어진 순서대로 규칙에 따라 줄을 설 때 각 학생들이 뒤로 물러난 걸음 수의 총합
         * 1. 자신보다 먼저 줄을 선 학생 중 자신보다 키가 큰 학생이 있는지 찾는다.
         *  1-1. 자신보다 큰 학생이 없다면 줄의 가장 뒤로 가서 선다
         * 2. 자신보다 큰 학생 중 가장 앞에 있는 학생(A) 앞에 선다.
         * 3. A와 A 뒤의 모든 학생들은 공간을 만들기 위해 한 발씩 뒤로 물러난다.
         *
         * 삽입 정렬
         *
         * */

        int a[] = {6,2,3,7,5,1,4};
        System.out.println(a.length);

        // 6 2 3 7 5 1 4
        Scanner sc = new Scanner(System.in);
        int[] h = new int[7];
        for (int i = 0; i < 7; i++) {
            h[i] = sc.nextInt();
        }

        // 정렬 횟수 구하기
        int count = 0;
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < i; j++) {
                //뒤에서 부터 정렬해서 보내줌 
                if (h[j] > h[i]) {
                    count++;
                }
            }
        }

        System.out.println(count);


        // 원하는 숫자를 20개 받음
        // case.1
//        Scanner sc = new Scanner(System.in);
//        int[] h = new int[20];
//        for (int i = 0; i < h.length; i++) {
//            h[i] = sc.nextInt();
//        }
//
//        // 반복문을 통해 현재 물러나게된 횟수를 구함
//        int ans = 0;
//        for (int i = 0; i < 20; i++) {
//            for (int j = 0; j < i; j++) {
//                if (h[i] > h[j]) {
//                    ans++;
//                }
//            }
//        }
//        System.out.println(ans);

        // case.2
//        Scanner sc = new Scanner(System.in);
//        //test case
//        int P = sc.nextInt();
//        while (P-- > 0) {
//            // 입력받은 정수를 배열에 넣어둠
//            int T = sc.nextInt();
//            int[] h = new int[20];
//            for (int i = 0; i < T; i++) {
//                h[i] = sc.nextInt();
//            }
//
//            int cnt = 0;
//            int[] sorted = new int[20];
//            for (int i = 0; i < 20; i++) {
//                // 1. 줄 서있는 학생 중 자신보다 큰 학생을 찾는다.
//                boolean find = false;
//                for (int j = 0; j < i; j++) {
//                    if (sorted[j] > h[i]) {
//                        find = true;
//                        // 2. 찾았다면 해당 학생 앞에 선다.
//                        // 3. 그 학생과 그 뒤의 학생이 한칸씩 뒤로 물러난다.
//                        for (int k = i - 1; k >= j; k--) {
//                            sorted[k + 1] = sorted[k];
//                            cnt++;
//                        }
//                        sorted[j] = h[i];
//                        break;
//                    }
//                }
//
//                // 찾지 못했다면 제일 뒤에 선다.
//                if (!find) {
//                    sorted[i] = h[i];
//                }
//
//            }
//
//            System.out.println(T + " " + cnt);
//
//
//        }


    }
}