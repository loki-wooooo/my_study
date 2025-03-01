import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        /**
         * 문제: HH:MM:SS 포맷의 두 시각의 차이를 HH:MM:SS 포맷으로 출력
         * 1. ':" 문자를 기준으로 시간, 분, 초를 쪼갠다.
         *  -> substring, split
         * 2. 두 시간, 분, 초의 차이를 계산한다.
         *  -> 모든 단위를 가장 작은 단위로 변경해서 계산(초로 변경)
         * 3. 구해진 시간을 HH:MM:SS 형태로 출력한다.
         *
         * 4 소금을 투하할때까지 필요한 시간을 HH:MM:SS 형태로 출력
         *  이 시간은 1초보다 크거나 같고, 24시간보다 작거나 같다.
         * */
        Scanner input = new Scanner(System.in);
        String current = input.next();
        String drop = input.next();

        //Case1. substring 사용
        int currentHour = Integer.parseInt(current.substring(0, 2));
        int currentMinute = Integer.parseInt(current.substring(3, 5));
        int currentSecond = Integer.parseInt(current.substring(6, 8));
        int currentSecondAmount = currentHour * 3600 + currentMinute * 60 + currentSecond;

        int dropHour = Integer.parseInt(drop.substring(0, 2));
        int dropMinute = Integer.parseInt(drop.substring(3, 5));
        int dropSecond = Integer.parseInt(drop.substring(6, 8));
        int dropSecondAmount = dropHour * 3600 + dropMinute * 60 + dropSecond;

        //Case2. split 사용
//        String[] currentUnit = current.split(":");
//        int currentHour = Integer.parseInt(currentUnit[0]);
//        int currentMinute = Integer.parseInt(currentUnit[1]);
//        int currentSecond = Integer.parseInt(currentUnit[2]);
//        int currentSecondAmount = currentHour * 3600 + currentMinute * 60 + currentSecond;
//
//        String[] dropUnit = drop.split(":");
//        int dropHour = Integer.parseInt(dropUnit[0]);
//        int dropMinute = Integer.parseInt(dropUnit[1]);
//        int dropSecond = Integer.parseInt(dropUnit[2]);
//        int dropSecondAmount = dropHour * 3600 + dropMinute * 60 + dropSecond;

        int needSecondAmount = dropSecondAmount - currentSecondAmount;
        // 이 시간은 1초보다 크거나 같고, 24시간보다 작거나 같다. 조건에 맞춰 0이 나올때 00:00:00을 변환해줌
        if (needSecondAmount <= 0) {
            needSecondAmount += 24 * 3600; // 음수면 1일을 더해줌
        }

        int needHour = needSecondAmount / 3600; // 시간 값
        int needMinute = needSecondAmount % 3600 / 60; // 몫은 시간
        int needSecond = needSecondAmount % 60; // 초에 대한 값을 반환 (몫은 분에 대한 반환 값)
        System.out.printf("%02d:%02d:%02d", needHour, needMinute, needSecond);


//
//        Scanner sc = new Scanner(System.in);
//        String current = sc.next();
//        String drop = sc.next();
//
//        String[] currentUnit = current.split(":");
//        int currentHour = Integer.parseInt(currentUnit[0]);
//        int currentMinute = Integer.parseInt(currentUnit[1]);
//        int currentSecond = Integer.parseInt(currentUnit[2]);
//        // 천체 초로 변환
//        int currentSecondAmount = currentHour * 3600 + currentMinute * 60 + currentSecond;
//
//        String[] dropUnit = drop.split(":");
//        int dropHour = Integer.parseInt(dropUnit[0]);
//        int dropMinute = Integer.parseInt(dropUnit[1]);
//        int dropSecond = Integer.parseInt(dropUnit[2]);
//        // 천체 초로 변환
//        int dropSecondAmount = dropHour * 3600 + dropMinute * 60 + dropSecond;
//
//        // 필요시간
//        int needSecondAmount = dropSecondAmount - currentSecondAmount;
//        // 값이 음수이면, 하루를 더해줌(초로 표현)
//        if (needSecondAmount < 0) {
//            needSecondAmount += 24 * 3600;
//        }
//
//        // 초로 반환
//        int needHour = needSecondAmount / 3600;
//        // 남은 초에 대한 값으 반환
//        int needMinute = needSecondAmount % 3600 / 60;
//        // 나머지만 사용
//        int needSecond = needSecondAmount % 60;
//        System.out.printf("%02d:%02d:%02d", needHour, needMinute, needSecond);
    }
}