fun main() {
    printDirection(Direction.NORTH)
    printDirection(Direction.EAST)
    printDirection(Direction.SOUTH)
    printDirection(Direction.WEST)

    val dir: Direction = Direction.NORTH
    printDirection(dir)

    println(Direction.WEST)
    println(Direction.EAST)

    println("-------------------")

    printNumber(Number.TWO)

}

// as is
//val north = 1
//val south = 2
//val east = 3
//val west = 4

// to be
enum class Direction {
    NORTH, SOUTH, WEST, EAST
}

enum class Number(val num: Int, val str: String) {
    ONE(1, "일"),
    TWO(2, "이"),
    THREE(3, "삼")
}

fun printDirection(a1: Direction) {
    when (a1) {
        Direction.NORTH -> println("North")
        Direction.SOUTH -> println("South")
        Direction.WEST -> println("West")
        Direction.EAST -> println("East")
    }
}

fun printNumber(a1: Number) {

    when (a1) {
        Number.ONE -> println("One")
        Number.TWO -> println("Two")
        Number.THREE -> println("Three")
    }

    when (a1.num) {
        1 -> println("One")
        2 -> println("Two")
        3 -> println("Three")
    }

    when (a1.str) {
        "일" -> println("One")
        "이" -> println("Two")
        "삼" -> println("Three")
    }

    println("a1.num : ${a1.num}")
    println("a1.str : ${a1.str}")
}