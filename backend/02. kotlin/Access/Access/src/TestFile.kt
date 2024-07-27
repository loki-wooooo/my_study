private class PrivateClass1
public class PublicClass1

// class에는 protected를 붙힐 수 없다.
protected class ProtectedClass1
internal class InternalClass1

// 생략시 public
// open 부모 클래스 명시
open class TestClass1{

    private val private1 = 100
    public val public1 = 200
    protected val protected1 = 300
    internal val internal1 = 400
}