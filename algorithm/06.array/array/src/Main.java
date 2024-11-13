import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        /**
         *  순서(Index)를 갖는 데이터의 집합
         * 생성과 동시에 크기가 고정
         *
         * 문제
         * 모든 행과 열에 경비원이 최소 한 명씩 있어야 할때
         * 추가로 필요한 경비원의 인원 수
         *
         * => 각 행,열에 대해서 경비원이 있는지 확인
         * => 보호받지 못하는 행/열 개수를 구한다.
         * => 둘 중 큰 값을 출력한다.
         * */

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();
        char[][] map = new char[n][m];
        for (int i = 0; i < n; i++) {
            map[i] = sc.next().toCharArray();
        }

        // case 1
//        int existRowCount = 0;
//        for (int i = 0; i < n; i++) {
//            boolean exist = false;
//            // 경비원 확인
//            for (int j = 0; j < m; j++) {
//                if (map[i][j] == 'X') {
//                    exist = true;
//                    break;
//                }
//                if (exist) {
//                    existRowCount++;
//                }
//            }
//        }
//
//        int existColCount = 0;
//        for (int i = 0; i < m; i++) {
//            boolean exist = false;
//            // 경비원 확인
//            for (int j = 0; j < n; j++) {
//                if (map[j][i] == 'X') {
//                    exist = true;
//                    break;
//                }
//                if (exist) {
//                    existRowCount++;
//                }
//            }
//        }

//        int needRowCount = n - existRowCount;
//        int needColCount = m - existColCount;

        //case 2

        boolean[] existRow = new boolean[n];
        boolean[] existCol = new boolean[m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 'X') {
                    existRow[i] = true;
                    existCol[j] = true;
                }
            }
        }

        int needRowCount = n;
        int needColCount = m;
        for (int i = 0; i < n; i++) {
            if (existRow[i]) {
                needRowCount--;
            }
        }

        for (int i = 0; i < n; i++) {
            if (existCol[i]) {
                needColCount--;
            }
        }

        System.out.println(Math.max(needRowCount, needColCount));

    }
}