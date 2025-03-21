import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        /*
         * 아홉 개의 줄에 걸쳐 난쟁이들의 키가 주어진다.
         * 주어지는 키는 100을 넘지 않는 자연수이며, 아홉 난쟁이의 키는 모두 다르며, 가능한 정답이 여러 가지인 경우에는 아무거나 출력한다.
         * */
        Scanner sc = new Scanner(System.in);

        /**
         * Step.1 9개의 숫자를 받음
         * 20
         * 7
         * 23
         * 19
         * 10
         * 15
         * 25
         * 8
         * 13
         * */
        int[] n = new int[9];
        for (int i = 0; i < 9; i++) {
            n[i] = sc.nextInt();
        }

        // Step.2 받은 데이터를 전체 더해줌
        int sum = 0;
        for (int i = 0; i < n.length; i++) {
            sum += n[i];
        }

        // Step.3 전체 합에서 두수의 합을 뺸 값이 100일때 멈춤
        boolean isFound = false;
        int[] f = new int[7];
        for (int i = 0; i < n.length; i++) {
            for (int j = i + 1; j < n.length; j++) {
                if (sum - n[i] - n[j] == 100) {
                    int searchIndex = 0;
                    for (int k = 0; k < n.length; k++) {
                        if (k != i && k != j) {
                            f[searchIndex++] = n[k];
                        }
                    }
                    isFound = true;
                    break;
                }
                if (isFound) {
                    break;
                }
            }
        }

        // 추출한 값을 오름차순으로 정렬
        sorted(f);
        for (int i = 0; i < f.length; i++) {
            System.out.println(f[i]);
        }
    }

    // 정렬처리
    // 현재값과, 다음값을 비교하고 정렬처리
    public static void sorted(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < i; j++) {
                // 현재 기준의 값을 한칸씩 뒤로 밀고, 현제값을 채워넣음
                if (arr[i] < arr[j]) {
                    int cus = arr[i];
                    for (int k = i; k > j; k--) {
                        arr[k] = arr[k - 1];
                    }
                    arr[j] = cus;
                    break;
                }
            }
        }
    }

}