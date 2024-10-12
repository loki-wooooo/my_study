//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
    val map1 = mapOf("key1" to 10, "key2" to 20, "key3" to 30)
    println("map1 : $map1")

    val map2 = mutableMapOf<String, Int>()
    println("map2 : $map2")

    // 담을 Type이 다양하면 "Any"타입을 사용
    val map3 = mapOf<String, Any>("key1" to 10, "key2" to 11.11, "key3" to true)
    println("map3 : $map3")

    println("----------------------------")

    println("map1 key1: ${map1.get("key1")}")
    // get 보다 []를 더 많이 사용함
    println("map1 key1: ${map1["key1"]}")

    println("----------------------------")

    println("map1 size : ${map1.size}")
    println("map1 keys : ${map1.keys}")
    println("map1 values : ${map1.values}")

    println("map1 contains key1: ${map1.containsKey("key1")}")
    println("map1 contains key100: ${map1.containsKey("key100")}")

    println("map1 contains value10 : ${map1.containsValue(10)}")
    println("map1 contains value100 : ${map1.containsValue(100)}")

    println("----------------------------")

    map2.put("key1",100)
    println("map2 : $map2")

    // key가 없다면 insert, 있다면, update
    map2["key2"] = 200
    println("map2 : $map2")

    map2.remove("key2")
    println("map2 : $map2")

    println("----------------------------")

    val map100 = map1.toMutableMap()
    map100["key100"] = 100
    println("map100 : $map100")

    val map200 = map1.toMap()
    // 불변형을 통한 에러
    //map200["key200"] = 200

    
}