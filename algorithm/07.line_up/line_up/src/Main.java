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

        Scanner scanner = new Scanner(System.in);
        int p = scanner.nextInt();
        // 시간 복잡도 -> O(P) * O(N^2)
        while (p-- > 0) {
            int t = scanner.nextInt();

            int[] h = new int[20];
            for (int i = 0; i < 20; i++) {
                h[i] = scanner.nextInt();
            }

            //저장과 정렬할 배열
            int cnt = 0;
            int[] sorted = new int[20];
            for (int i = 0; i < 20; i++) {
                // 1. 줄 서있는 학생 중 자신보다 큰 학생을 찾음
                // 1-1. 찾지 못했다면 가장 뒤에 선다.
                boolean isFind = false;
                for (int j = 0; j < i; j++) {
                    if (sorted[j] > h[i]) {
                        // 2. 찾았다면 그학생 앞에 선다.
                        // 3. 그 학생과 그 뒤의 학생이 모두 한칸씩 물러난다.
                        isFind = true;
                        for (int k = i - 1; k >= j; k++) {
                            sorted[k + 1] = sorted[k];
                            cnt++;
                        }
                        sorted[j] = h[i];
                        break;
                    }
                }
                // 맨뒤에 세움
                if (!isFind) {
                    sorted[i] = h[i];
                }
            }
            System.out.println(t + " " + cnt);
        }
    }
}