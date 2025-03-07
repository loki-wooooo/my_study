package lecture

import lectureclass.Person

object OOP extends App{
  val person1 = new Person("Alice", 30)
  val person2 = new Person("Bob", 25)

  println(person1.greet())
  println(person2.greet())
  println(person1.greet(name = "Tom"))

  val person3 = new Person()
  println(person3.greet())



}
