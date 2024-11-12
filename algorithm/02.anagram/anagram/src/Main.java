import java.util.Scanner;

public class Main {

    public static int[] getAlphabetCount(final String str) {
        int[] count = new int[26];
        for (int i = 0; i < str.length(); i++) {
            // 알파벳의 갯수를 저장하는 배열
            count[str.charAt(i) - 'a']++; //'a'를 빼는 것은 알파벳 위치를 0-25 사이의 인덱스로 변환하는 과정
        }

        return count;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String a = sc.next();
        String b = sc.next();

        int[] countA = getAlphabetCount(a);
        int[] countB = getAlphabetCount(b);

        int ans = 0;
        for (int i = 0; i < 26; i++) {
            //절대값을 리턴해주는 함순
            ans += Math.abs(countA[i] - countB[i]);
//            if (countA[i] > countB[i]) {
//                ans += countA[i] - countB[i];
//            } else if (countA[i] < countB[i]) {
//                ans += countB[i] - countA[i];
//            }
        }
        System.out.println(ans);
    }
}

