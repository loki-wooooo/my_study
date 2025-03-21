package lecture

import scala.collection.mutable.ArrayBuffer
import scala.collection.mutable.ListBuffer
import scala.collection.mutable.HashMap
import scala.collection.mutable.HashSet

object MutableCollections extends App {
    //ArrayBuffer
    val buffer = ArrayBuffer[Int]()
    buffer += 1
    buffer ++= Seq(5, 6)
    buffer -= 6
    buffer.remove(0)
    println(buffer)

    //ListBuffer
    val listBuffer = ListBuffer[String]()
    listBuffer += "Scala"
    listBuffer.prepend("Hello")
    listBuffer -= "Scala"
    println(listBuffer)

    //HashMap
    val hashMap = HashMap[Int, String]()
    hashMap ++= Seq(1 -> "Scala", 2 -> "Java")
    hashMap.put(3, "Python")
    hashMap -= 2
    println(hashMap)

    //HashSet
    val hashSet = HashSet[String]()
    hashSet += "Scala"
    hashSet ++ Seq("Java", "Scala")
    hashSet -= "Java"
    println(hashSet.contains("Scala"))
    println(hashSet)


}
