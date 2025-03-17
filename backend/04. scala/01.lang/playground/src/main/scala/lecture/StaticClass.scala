package lecture

object StaticClass extends App {

    //define class
    class Person(name: String, age: Int) {
        def greet(): String = s"Hello, my name is $name and I am $age years Old"
    }

    //define companion object
    object Person {

        //Factory method to create Person instance
        //apply 메서드를 정의하면 객체를 마치 함수처럼 호출할
        def apply(name: String, age: Int): Person = new Person(name, age)

        //static utility method
        def isValidAge(age: Int): Boolean = age >= 0
    }

    val person = Person("Alice", 30)

    // Accessing the greet method and utility method
    println(person.greet())
    println(Person.isValidAge(25))

}
