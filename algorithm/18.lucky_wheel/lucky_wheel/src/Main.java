import java.util.Arrays;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        /**
         * 문제: 바퀴를 시계방향으로 S칸 돌릴때마다 화살표가 가리키는 글자가 주어질 떄 바퀴의 각 칸에 적어놓은 알파벳을 알아내자
         *
         * 1. 바퀴의 커서 인덱스를 S만큼 움직인다.
         *  1-0. 커서 인덱스가 배열 범위를 넘어갔다면 조정한다.
         *  1-1. 도착한 칸이 아직 알아내지 못한 글자라면, 기록한다.
         *  1-2. 도착한 칸의 글자가 적힌 글자와 다르다면 바퀴에 존재하지 않는다.
         *  1-3. 도착한 칸의 글자가 적힌 글자와 같다면 넘어간다.
         *
         * 2. 바퀴에 기록된 글자를 마지막으로 도착한 글자부터 출력한다.
         *  wheel[] 0 1 2 3 4 5 6 7
         *          ? ? ? ? ? ? ? ?
         *
         * */
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt(); //바퀴의 칸수
        int K = sc.nextInt(); //바퀴를 돌린수

        char[] wheel = new char[N];
        Arrays.fill(wheel, '?');
        int curIndex = 0;

        while (K-- > 0) {
            int step = sc.nextInt();
            char nextAlphabet = sc.next().charAt(0);
            int nextIndex = ((curIndex - step) % N + N) % N; // 음수 값의 대응
            if (wheel[nextIndex] == '?') {
                wheel[nextIndex] = nextAlphabet;
            } else if (wheel[nextIndex] != nextAlphabet) {
                System.out.println("!");
                return;
            }

            curIndex = nextIndex;
        }

        boolean[] used = new boolean[26];
        for (int i = 0; i < N; i++) {
            if (wheel[i] == '?') continue;
            if (used[wheel[i] - 'A']) { // AAAA 이런 케이스는 중복 문자열이라 값이 나오는게 아닌 "!" 출력
                System.out.println("!");
                return;
            }
            used[wheel[i] - 'A'] = true;
        }

        for (int i = 0; i < N; i++) {
            System.out.print(wheel[(curIndex + i) % N]);
        }
        System.out.println();

        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
    }
}