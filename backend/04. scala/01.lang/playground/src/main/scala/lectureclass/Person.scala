package lectureclass

class Person(val name: String, age: Int) {

  def greet(): String = s"Hello, my name is $name and I am $age years old."

  def greet(name: String): String = s"Hello, my name is ${this.name} and I am $age years old."

  //construct
  def this(name: String) = this(name, 40)

  def this() = this("John")

}
