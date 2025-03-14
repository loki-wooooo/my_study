package lecture

object Generics extends App {

    //Generic Class
    class MyContainer[T](value: T) {
        def getContent: T = value
    }

    //Create instance of MyContainer with different types
    val intContainer = new MyContainer[Int](42)
    val stringContainer = new MyContainer("Hello, World")

    val intValue: Int = intContainer.getContent
    val stringValue: String = stringContainer.getContent
    println(intValue)
    println(stringValue)

    //Generic Method
    class MyList {
        def makeTuple[A, B](first: A, sencod: B): (A, B) = {
            (first, sencod)
        }
    }

    val myList = new MyList
    val intStringTuple = myList.makeTuple(43, "Tuple")
    val doubleBooleanTuple = myList.makeTuple(3.14, true)
    println(intStringTuple)
    println(doubleBooleanTuple)


    class Animal

    class Dog extends Animal;

    class Cat extends Animal;

    // covariance
    class AnimalContainer1[+T](value: T)

    val dogContainer1: AnimalContainer1[Animal] = new AnimalContainer1[Dog](new Dog)
    val catContainer1: AnimalContainer1[Animal] = new AnimalContainer1[Cat](new Cat)

    // invariance
    class AnimalContainer2[T](value: T)

    val dogContainer2: AnimalContainer2[Animal] = new AnimalContainer2[Animal](new Animal)
    val catContainer2: AnimalContainer2[Animal] = new AnimalContainer2[Animal](new Animal)

    // contravariance
    class AnimalContainer3[-T](value: T)

    val dogContainer3: AnimalContainer3[Dog] = new AnimalContainer3[Animal](new Animal)
    val catContainer3: AnimalContainer3[Cat] = new AnimalContainer3[Animal](new Animal)

    // Bounds Type
    class Container[T <: Animal](value: T) {
        //this container can hold animals or their subtypes;
        def getAnimal: T = value
    }

    class PetContainer[T >: Dog](pet: T) {
        def getPet: T = pet
    }

    val animalContainer1 = new Container(new Dog)
    println(animalContainer1.getAnimal)
    val animalContainer2 = new Container(new Cat)
    println(animalContainer2.getAnimal)

    val dogContainer = new PetContainer(new Dog)
    println(dogContainer.getPet)
    val animalPetContainer = new PetContainer(new Animal)
    println(animalPetContainer.getPet)

}
