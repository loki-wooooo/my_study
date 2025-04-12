import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    static class Score {

        Score(int staffIndex, double scr) {
            this.staffIndex = staffIndex;
            this.scr = scr;
        }

        int staffIndex;
        double scr;
    }

    // 내림차순으로 정렬
    public static void sortScoresDescOrder(Score[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j < i; j++) {
                if (arr[i].scr > arr[j].scr) {
                    Score cur = arr[i];
                    for (int k = i; k > j; k--) {
                        arr[k] = arr[k - 1];
                    }
                    arr[j] = cur;
                }
            }
        }
    }

    public static void main(String[] args) {
        /**
         * 문제: 득표수가 주어질 떄 (중략)에 따라 스태프가 받을 칩의 개수를 구해보자.
         * 1. 전체 득표수의 5% 미만의 스태프를 후보에서 제외
         * 2. 남은 스태프마다 받은 득표수를 1~14로 나눈 점수를 얻는다.
         * 3. 전체 점수 집합에서 점수가 큰 1~14번 스태프에게 칩을 1개씩 지급
         * 4. 스태프 이름에 대해 사전순으로 후보 스태프와 받은 칩의 수를 출력
         *
         * */

        Scanner sc = new Scanner(System.in);
        int X = sc.nextInt(); // 총 득표수
        int N = sc.nextInt(); // 받게될 스탬프의 수

        // 1. 전체 득표수의 5% 미만의 스태프를 후보에서 제외
        double validCut = X * 0.05;
        boolean[] validCandidate = new boolean[26];
        int[] staffVote = new int[26];
        int candidateCount = 0;

        for (int i = 0; i < N; i++) {
            String name = sc.next();
            int vote = sc.nextInt();

            // 5%미만 스태프를 필터링
            if (vote > validCut) {
                //이름의 맨 앞글자를 index로 받음
                int index = name.charAt(0) - 'A';
                validCandidate[index] = true;
                staffVote[index] = vote;
                candidateCount++;
            }
        }

        // 2. 남은 스태프마다 받은 득표수를 1~14로 나눈 점수를 얻는다.
        Score[] scores = new Score[candidateCount * 14];
        int scoreIndex = 0;
        for (int i = 0; i < 26; i++) {
            if (validCandidate[i]) {
                for (int j = 0; j < 14; j++) {
                    scores[scoreIndex++] = new Score(i, (double) staffVote[i] / j);
                }
            }
        }

        // 3. 전체 점수 집합에서 점수가 큰 1~14번 스태프에게 칩을 1개씩 지급 -- sort
        sortScoresDescOrder(scores);

        int[] ans = new int[26];
        for (int i = 0; i < 14; i++) {
            ans[scores[i].staffIndex]++;
        }

        // 4. 스태프 이름에 대해 사전순으로 후보 스태프와 받은 칩의 수를 출력
        for (int i = 0; i < 26; i++) {
            if (validCandidate[i]) {
                System.out.println((char) (i + 'A') + " " + ans[i]);
            }
        }

    }
}