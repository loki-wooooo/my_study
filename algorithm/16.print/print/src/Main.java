import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        /**
         * 1730 판화
         * 문제: 로봇 팔의 명령 순서가 주어졌을 때, 목판 위에 패인 조각도의 흔적
         *
         * 각 칸에 대한 흔적
         * - : 가로 방향으로만 경유한 경우
         * ㅣ : 세로 방향으로만 경유한 경우
         * + : 가로/세로 모두 경유한 경우
         * . : 경유한 적 없는 칸인 경우
         *
         * 1. 팔을 명령 순서대로 움직인다.
         *  - D: 아래로 움직일 수 있다면, 지금 칸과 다음 에 세로 흔적을 남긴다.
         *  - U: 위 움직일 수 있다면, 지금 칸과 다음 에 세로 흔적을 남긴다.
         *  - R: 오른쪽으로 움직일 수 있다면, 지금 칸과 다음 에 가로 흔적을 남긴다.
         *  - L: 왼쪽로 움직일 수 있다면, 지금 칸과 다음 에 가로 흔적을 남긴다.
         * 2. 누적된 흔적을 움직인다.
         *  boolean[][] passVertical = new boolean[N][N]
         *  boolean[][] passHorizontal = new boolean[N][N]
         *  int curR, curC =0;
         * */

        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        //hasnext -> 값이 있는지에 대한 유/무 확인
        String command = sc.hasNext() ? sc.next() : "";

        boolean[][] passVertical = new boolean[N][N];
        boolean[][] passHorizontal = new boolean[N][N];
        int curR = 0;
        int curC = 0;

        // 2차원 배열의 인덱스 구조 해당구조를 알면 상,하,좌,우 에 대한 위치를 이해하기 쉬움
        // [0,0] | [0,1] | [0,2] | [0,3] | [0,4]
        // [1,0] | [1,1] | [1,2] | [1,3] | [1,4]
        // [2,0] | [2,1] | [2,2] | [2,3] | [2,4]
        // [3,0] | [3,1] | [3,2] | [3,3] | [3,4]
        // [4,0] | [4,1] | [4,2] | [4,3] | [4,4]

        for (int i = 0; i < command.length(); i++) {
            char cmd = command.charAt(i);
            if (cmd == 'D') {
                // 음수면 더이상 내려갈 곳이 없음
                if (curR == N - 1) {
                    continue;
                } else {
                    // 이동흔적
                    passVertical[curR][curC] = passVertical[curR + 1][curC] = true;
                    curR++; // 실제 이동
                }
            } else if (cmd == 'U') {
                if (curR == 0) {
                    continue;
                } else {
                    passVertical[curR][curC] = passVertical[curR - 1][curC] = true;
                    curR--;
                }
            } else if (cmd == 'L') {
                if (curC == 0) {
                    continue;
                } else {
                    passHorizontal[curR][curC] = passHorizontal[curR][curC - 1] = true;
                    curC--;
                }
            } else {
                // 제일 오른쪽
                if (curC == N - 1) {
                    continue;
                } else {
                    passHorizontal[curR][curC] = passHorizontal[curR][curC + 1] = true;
                    curC++;
                }
            }
        }

        for (int i = 0; i < N; i++) {
            String ans = "";
            for (int j = 0; j < N; j++) {
                /**
                 * 수평 수직
                 * 수직
                 * 수평
                 * 아무것도 안지나감
                 * */
                if (passVertical[i][j] && passHorizontal[i][j]) {
                    ans += "+";
                } else if (passVertical[i][j]) {
                    ans += "|";
                } else if (passHorizontal[i][j]) {
                    ans += "-";
                } else {
                    ans += ".";
                }
            }
        }
        System.out.println(ans);
    }
}