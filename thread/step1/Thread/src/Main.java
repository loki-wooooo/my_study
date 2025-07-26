//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        // case.1 클래스 생성
//        System.out.println("main() - begin");
//        Runnable myThread = new MyThread();
//        Thread thread = new Thread(myThread); // Runnable의 파생객체를 Thread에 넣음 (생성)
//        thread.start(); // Thread 인스턴스가 새로 생성 되어 시작
//
//        try {
//            Thread.sleep(500); // 부정확함.(500ms + 알파)
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        System.out.println("main() - end");

        // case.2 익명클래스
//        System.out.println("main() - begin");
//        Thread thread = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                System.out.println("MyThread.run() - begin");
//                System.out.println("MyThread.run() - end");
//            }
//        });
//        thread.start(); // Thread 인스턴스가 새로 생성 되어 시작

        // case.3 람다
        System.out.println("main() - begin");
        Thread thread = new Thread(() -> {
            System.out.println("MyThread.run() - begin");
            System.out.println("MyThread.run() - end");
        });
        thread.start(); // Thread 인스턴스가 새로 생성 되어 시작

        try {
            Thread.sleep(500); // 부정확함.(500ms + 알파)
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("main() - end");
    }
}

class MyThread implements Runnable {
    public void run() {
        System.out.println("MyThread.run() - begin");
        System.out.println("MyThread.run() - end");
    }
}