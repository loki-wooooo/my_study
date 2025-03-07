package lectureclass

class Car {
  var model: String = "modelY"
  var year: Int = 2023

  //primary constructor
  def this(model: String, year: Int) = {
    this()
    this.model = model;
    this.year = year
  }

  def this(model: String) = {
    this()
    this.model = model
  }

  def this(year: Int) = {
    this()
    this.year = year
  }

  def introduce(): Unit = {
    println(s"${this.model}(${this.year}) is starting...")
  }

}
