import java.util.Scanner;

public class Main {

    public static void swapCandy(char[][] map, int r1, int c1, int r2, int c2) {
        char temp = map[r1][c1];
        map[r1][c1] = map[r2][c2];
        map[r2][c2] = temp;
    }

    public static int findMaxRow(char[][] map) {
        int N = map.length;
        int maxRow = 0;
        for (int r = 0; r < N; r++) {
            int len = 1;
            for (int c = 0; c < N; c++) {
                // 지금값과 이전값이 같으면 추가
                if (map[r][c] == map[r][c - 1]) {
                    len++;
                } else {
                    // 연속성 답에 갱신
                    // 최대값 비교
                    maxRow = Math.max(maxRow, len);
                    len = 1;
                }
            }
            //마지막에 연결된 row 체크
            // 마지막 행에 대한 중복 for문이 실행안되서 max 값 적용
            maxRow = Math.max(maxRow, len);
        }
        return maxRow;
    }

    public static int findMaxColumn(char[][] map) {
        int N = map.length;
        int maxRow = 0;
        for (int c = 0; c < N; c++) {
            int len = 1;
            for (int r = 0; r < N; r++) {
                // 지금값과 이전값이 같으면 추가
                if (map[r][c] == map[r - 1][c]) {
                    len++;
                } else {
                    // 연속성 답에 갱신
                    // 최대값 비교
                    maxRow = Math.max(maxRow, len);
                    len = 1;
                }
            }
            //마지막에 연결된 row 체크
            // 마지막 행에 대한 중복 for문이 실행안되서 max 값 적용
            maxRow = Math.max(maxRow, len);
        }
        return maxRow;
    }


    public static void main(String[] args) {

        /**
         * 문제: 색이 다른 사탕이 인접한 두 칸을 골라 사탕을 서로 교환할 때, 같은 색으로 이루어진 가장 긴 연속 부분 행/열의 최댓값
         *
         * 1. 가능한 모든 쌍의 사탕을 서로 교환한다.
         * 2. 교환한 상태에서 가장 긴 연속 부분 행/열을 찾는다.
         * 3. 교환한 사탕을 원복한다.
         * */

        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        char[][] map = new char[N][N];
        for (int i = 0; i < N; i++) {
            map[i] = sc.next().toCharArray();
        }

        int ans = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {

                // 열체크(ㅡ)
                // 체크 후 다시 변경
                if (j + 1 < N && map[i][j] != map[i][j + 1]) {
                    swapCandy(map, i, j, i, j + 1);
                    ans = Math.max(ans, Math.max(findMaxColumn(map), findMaxRow(map)));
                    swapCandy(map, i, j, i, j + 1);
                }

                // 행체크(ㅣ)
                // 체크 후 다시 변경
                if (i + 1 < N && map[i][j] != map[i + 1][j]) {
                    swapCandy(map, i, j, i + 1, j);
                    ans = Math.max(ans, Math.max(findMaxColumn(map), findMaxRow(map)));
                    swapCandy(map, i, j, i + 1, j);
                }
            }
        }

        System.out.println(ans);
    }
}