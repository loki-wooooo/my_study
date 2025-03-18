package lecture

object AnonymousFunction extends App {
    //basic
    var constant: () => Int = () => 3
    val add: (Int, Int) => Int = (a: Int, b: Int) => a + b

    // type inference
    // 데이터 타입 추론
    val add1 = (a: Int, b: Int) => a + b
    val add2: (Int, Int) => Int = (a, b) => a + b

    // 1 param with _ placeholder
    val double = (_: Int) * 2
    val increments: Int => Int = _ + 1

    // 2 param with parenthesis and _ placeholder
    val add3: (Int, Int) => Int = _ + _

    // multiple line
    val add4 = (x: Int, y: Int) => {
        x + y
    }

    // multiple lines with curly braces
    val add5 = {
        (x: Int, y: Int) => x + y
    }

}
