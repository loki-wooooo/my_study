public class JavaMain {

    public int javaA1 = 100;
    public static int javaA2 = 200;

    public void javaMethod1() {
        System.out.println("javaMethod1");
    }

    public static void javaMethod2() {
        System.out.println("javaMethod2");
    }

    public static void main(String[] args) {

        TestClass t1 = new TestClass();
        //kotlin 은 getter 메서드로 호출 필요함 -> property로 만들기 때문에
        System.out.printf("t1.a1 : %d\n", t1.getA1());
        t1.testFun1();

//        System.out.printf("TestClass.a2 : %d\n", TestClass.a2);
//        TestClass.testFun2()

        // Companion -> 키워드 추가 필요함
        // kotlin 의 정적 멤버를 사용하기 위해서
        System.out.printf("TestClass.a2 : %d\n", TestClass.Companion.getA2());
        TestClass.Companion.testFun2();

        // kotlin @JVMstatic 어노테이션 사용시 Compainon 생략
        System.out.printf("TestClass.a3 : %d\n", TestClass.getA3());
        TestClass.testFun3();

    }
}
