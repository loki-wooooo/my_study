fun main() {

    val str1 = "abcd"

    //마치 string에 upperclass가 있는것처럼 보임
    println(str1.getUpperString())
}

// 기존의 String class에 확장
fun String.getUpperString(): String {
    return this.toUpperCase()
}