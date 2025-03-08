import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        /**
         * 배열
         *  - 원소를 몇개담을지
         *  - 타입이 어떤 타입을 사용할지
         *  - 반복문이랑 같이 자주 등장
         *  - 순서를 갖는 데이터의 집합
         *  - 생성과 동시에 크기가 정해짐
         *  - 메모리상에 1열로 저장
         *
         *
         *  * 시간복잡도
         *      - get(int idx) : idx 원소 집합 O(1)
         *      - change(int idx, int val) : idx번째 원소를 val로 변경 O(1)
         *      - append(int val) : 가장 뒤에 원소 삽입 O(1)
         *      - insert(int idx, int val) : 현재 idx번째 원소의 앞에 원소 삽입 O(N)
         *      - erase(int idx) : idx번쨰 원소 삭제 O(N)
         * */

        /**
         * 성지키기
         * 문제: 모든 행과 열에 경비원이 최소 한 명씩 있어야할 때 추가로 필요한 경비원의 최소 수
         *
         * 1. 각 행/열에 대해 경비원이 있는지 확인한다.
         * 2. 보호받지 못하는 행/열의 개수를 구한다.
         * 3. 둘 중 큰 값을 출력한다.
         *
         * */

        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int M = sc.nextInt();
        char[][] map = new char[N][M];
        for (int i = 0; i < N; i++) {
            map[i] = sc.next().toCharArray();
        }


        //Case.1

        // 1. 각 행/열에 대해 경비원이 있는지 확인한다.
        // 행에대한 체크
        int existRowCount = 0;
        for (int r = 0; r < N; r++) {
            boolean exist = false;
            for (int c = 0; c < M; c++) {
                if (map[r][c] == 'X') {
                    exist = true;
                    break;
                }
            }
            if (exist) {
                existRowCount++;
            }
        }

        // 열에 대한 체크
        int existColumnCount = 0;
        for (int c = 0; c < M; c++) {
            boolean exist = false;
            for (int r = 0; r < N; c++) {
                if (map[r][c] == 'X') {
                    exist = true;
                    break;
                }
            }
            if (exist) {
                existColumnCount++;
            }
        }

        // 2. 보호받지 못하는 행/열의 개수를 구한다.
        int needRowCount = N - existRowCount;
        int columnCount = M - existColumnCount;

        // 3. 둘 중 큰 값을 출력한다.
        System.out.println(Math.max(needRowCount, columnCount));

        //case2
        // 1. 각 행/열에 대해 경비원이 있는지 확인한다.
        boolean[] existRow = new boolean[N];
        boolean[] existColumn = new boolean[M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 'X') {
                    existRow[i] = true;
                    existColumn[j] = true;
                }
            }
        }

        // 2. 보호받지 못하는 행/열의 개수를 구한다.
        // 총 배열의 갯수를 지정
        int needRowCountData = N;
        int columnCountData = M;
        
        // row가 'true'인 애를 다 뺴줌
        for (int i = 0; i < N; i++) {
            if (!existRow[i]) {
                needRowCountData--;
            }
        }

        for (int i = 0; i < M; i++) {
            if (!existColumn[i]) {
                columnCountData--;
            }
        }

        // 3. 둘 중 큰 값을 출력한다.
        // 보호가 필요한 내용중 큰 값을 기준으로 출력
        System.out.println(Math.max(needRowCountData, columnCountData));

    }
}