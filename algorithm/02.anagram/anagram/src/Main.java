import java.util.Scanner;

public class Main {

    public static int[] getAlphabetCount(String word) {
        int[] counts = new int[26];
        for (int i = 0; i < word.length(); i++) {
            counts[word.charAt(i) - 'a']++;
        }
        return counts;
    }

    /**
     * 애너그램 만들기,
     * 문제 : 두 단어를 애너그램으로 만들기 위해 제거해야하는 문자의 최소 개수
     * 애너그램 : 단어의 구성 (알파벳과 그 개수)이 완전히 같은 단어
     * <p>
     * 1. A: "aabbcc" B: "xxyybb"의 답은 왜 8일까?
     * - A의 {a,a,b,b}가 B에 없으니 지워야함
     * - B의 {x,x,y,y}가 A에 없으니 지워야함
     * 2. 없애야만 하는 문자 : 공통 문자를 제외한 전부
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // A,B의 알파뱃 구성을 잡음
        String a = scanner.next();
        String b = scanner.next();

        // 알파뱃의 크기가 총 "26" 이여서 배열의 값을 26으로 지정
        int countA[] = getAlphabetCount(a);
        int countB[] = getAlphabetCount(b);

//        int countA[] = new int[26]; //dared : [1, 0, 0, 2 ...]
//        int countB[] = new int[26];
//
//        // 반복되는 문장을 함수로 빼서 사용하도록 리팩토링 처리
//        for (int i = 0; i < a.length(); i++) {
//            countA[a.charAt(i) - 'a']++; // -'a'의 의미는 ascii값 때문에 뺴줌 -> a의 ascii 코드의 값은 97이여서 countA[97]로 들어감 인덱스가 안맞음
//        }
//
//        for (int i = 0; i < b.length(); i++) {
//            countB[b.charAt(i) - 'a']++;
//        }

        int answer = 0;
        //a와 b의 갯수의 차이
        for (int i = 0; i < 26; i++) {
//            if (countA[i] > countB[i]) {
//                answer += countA[i] - countB[i];
//            } else if (countA[i] < countB[i]) {
//                answer += countB[i] - countA[i];
//            }

            // 절대값 변경 (음수 양수 상관없이 음수로 리턴)
            answer += Math.abs(countA[i] - countB[i]);
        }
        System.out.println(answer);
    }


//    public static int[] getAlphabetCount(final String str) {
//        int[] count = new int[26];
//        for (int i = 0; i < str.length(); i++) {
//            // 알파벳의 갯수를 저장하는 배열
//            count[str.charAt(i) - 'a']++; //'a'를 빼는 것은 알파벳 위치를 0-25 사이의 인덱스로 변환하는 과정
//        }
//
//        return count;
//    }
//
//    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        String a = sc.next();
//        String b = sc.next();
//
//        int[] countA = getAlphabetCount(a);
//        int[] countB = getAlphabetCount(b);
//
//        int ans = 0;
//        for (int i = 0; i < 26; i++) {
//            //절대값을 리턴해주는 함순
//            ans += Math.abs(countA[i] - countB[i]);
////            if (countA[i] > countB[i]) {
////                ans += countA[i] - countB[i];
////            } else if (countA[i] < countB[i]) {
////                ans += countB[i] - countA[i];
////            }
//        }
//        System.out.println(ans);
//    }
}

