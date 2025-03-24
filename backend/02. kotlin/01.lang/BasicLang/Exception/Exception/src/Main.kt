//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
    try{
//        val a1 = 10 / 0

//        val str:String? = null
//        println(str!!.length)

        val str2 = "안녕하세요"
        val a2:Int = str2.toInt()
        println("a2 : $a2")

    } catch (e:ArithmeticException){
        println("수학적 오류가 발생 하였습니다.")
    } catch (e:KotlinNullPointerException){
        println("Null 오류가 발생 하였습니다.")
    } catch (e: Exception){
        println("예외 발생")
        e.printStackTrace()
    }


    println ("이 부분이 실행 될까요?")

}