package lecture

object Inheritance extends App {

    //Define a superclass
    class Animal(name: String) {
        val species = "Animal";

        def eat(food: String): Unit = println(s"${this.species} eats ${food}")

        def speak(): Unit = println(s"$name makes a sound")
    }

    trait Swimmer {
        def swim(): Unit
    }

    // Define a subclass
    // 여러개 상속받을시 "with" 키워드 사용
    class Dog(name: String) extends Animal(name) with Swimmer {
        override val species: String = "Dog"

        override def speak(): Unit = println(s"$name barks")

        def swim(): Unit = println(s"${name} can swim")

        override def eat(food: String): Unit = {
            super.eat(food)
        }
    }

    class Cat(name: String) extends Swimmer {
        def swim(): Unit = println(s"${name} cannot swim")
    }

    // Create instances
    val animal = new Animal("Generic Animal")
    val dog = new Dog("Rex")
    val cat = new Cat("Kitty")

    // calling methods
    animal.speak();
    dog.speak();
    dog.eat("food")

    abstract class Person(name: String) {
        def walk(): Unit
    }

    trait Runner {
        def run(): Unit
    }

    class Tom(name: String) extends Person(name) with Swimmer with Runner {
        override def walk(): Unit = println(s"${name} can walk")

        def swim(): Unit = println(s"${name} can swim")

        def run(): Unit = println(s"${name} can run")
    }

    val tom = new Tom("tom")
    tom.swim();
    tom.run();
    tom.walk()

}
