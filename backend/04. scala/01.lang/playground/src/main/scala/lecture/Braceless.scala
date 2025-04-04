package lecture

object Braceless extends App {

    def add(a: Int, b: Int): Int = {
        a + b
    }

    println(add(3, 4))

    def addV3(a: Int, b: Int): Int =
        a + b

    println(addV3(3, 4))

    def checkNumer(x: Int): String =
        if (x > 0)
            "Positive"
        else if (x < 0)
            "Negative"
        else
            "Zero"

    println(checkNumer(3))

    def checkNumberV3(x: Int): String =
        if x > 0 then
            "Positive"
        else if x < 0 then
            "Negative"
        else
            "Zero"

    println(checkNumberV3(2))

    val numbers = List(1, 2, 3, 4, 5)
    val doubled = for
        num <- numbers
    yield num * 2
    println(doubled)

    val doubledV3 =
        for
            num <- numbers
        yield num * 2
    println(doubledV3)

    val day = "Monday"
    val message = day match {
        case "Monday" => "Start of the week"
        case "Tuesday" => "Second day of the week"
        case "Wednesday" => "Midweek"
        case _ => "Another day"
    }
    println(message)

    val messageV3 =
        day match
            case "Monday" => "Start of the week"
            case "Tuesday" => "Second day of the week"
            case "Wednessday" => "Midweek"
            case _ => "Another day"
    println(messageV3)

    class CarV3(val brand: String, val model: String, var mileage: Double):
        def drive(distance: Double): Unit =
            println(s"Driving $distance miles")
            mileage += distance
        end drive

        def displayInfo(): Unit =
            println(s"Brand: $brand, Model: $model, Mileage: $mileage")
        end displayInfo

        val tesla = new CarV3("Tesla", "ModelX", 2000)
        tesla.drive(10)
        tesla.displayInfo()

    // space & tab을 섞어 사용하지 않는게 좋음


}
