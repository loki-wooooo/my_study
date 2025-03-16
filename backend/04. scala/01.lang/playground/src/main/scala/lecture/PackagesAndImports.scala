package lecture

// selective imports
import util.{CoreUtil, ExtraUtil}

//wildcard imports
// import util._

object PackagesAndImports extends App{

  val coreUtil = new CoreUtil
  println(coreUtil.CONSTANT)
  val extraUtil = new ExtraUtil

  //full package name;
  // val coreUtil2 = new util.CoreUtil

  // package object
  println(commonValue)
  commonMethod()

  //scoping
  import util._

  println(utilValue)
}

