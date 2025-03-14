package lecture

object Enum extends App {

    enum Color {
        case Red, Green, Blue
    }

    // Access Enum
    println(Color.Red)
    // Access with String
    println(Color.valueOf("Red"))
    // Access th position
    println(Color.Blue.ordinal)
    // List all the enum values
    Color.values.foreach(color => println(color))


    //enum with constructor
    enum Planet(val mass: Double, radius: Double) {
        case Earth extends Planet(5, 97e24, 6.378e6)
        case Mars extends Planet(6.39e23, 3.389e6)
    }

    println(Planet.Earth.mass)

    //enum can have method
    enum Shape {
        case Circle(radius: Double)
        case Rectangle(width: Double, height: Double)

        def area: Double = this match {
            case Circle(radius) => Math.PI * radius * radius
            case Rectangle(width, height) => width * height
        }
    }

    val shape: Shape = Shape.Circle(10)
    println(shape.area)

}
