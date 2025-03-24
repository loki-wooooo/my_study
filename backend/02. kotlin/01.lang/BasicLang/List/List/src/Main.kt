//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {

    // Generic 작성을 추천함
//    val list = listOf<Int>(1, 2, 3, 4, 5, 6)
    val list1 = listOf(10, 20, 30, 40, 50)
    val list2 = listOf("문자열1", "문자열2", "문자열3")

    println("list1 : $list1")
    println("list2 : $list2")

    // 가변형 리스트
    // 값이 비어있을시는 반드시 제네릭타입을 명시해 줘야함
    val list3 = mutableListOf<Int>()
    // 타입에 대한 추론이 가능하기 떄문에 해당 내용은 Generic을 생략함
    val list4 = mutableListOf("문자열1", "문자열2", "문자열3")
    println("list3 : $list3")
    println("list4 : $list4")

    // 아무것도 없는 리스트 만들기
    val list5 = emptyList<Int>()
    // emptyList -> 비어있는 불변형리스트 "add" 키워드를 사용할 수 없음
//    list5.add
    println("list5 : $list5")

    // "null"값을 제외한 나머지만 리스트를 만들어줌
    val list6 = listOfNotNull(10, 20, null, 30, null, 40, 50)
    println("list6 : $list6")

    for (item in list1){
        println("list1: $item")
    }

    println("list1 size : ${list1.size}")

    println("-----------------------------")

    println("list1 0 : ${list1.get(0)}")
    println("list1 1 : ${list1.get(1)}")
    // 해당 방법을 많이 사용 *
    println("list1 2 : ${list1[2]}")
    println("list1 3 : ${list1[3]}")

    println("-----------------------------")

    val list7 = listOf(10, 20, 30)

    // 왼쪽에서 오른쪽으로 찾아감
    val index1 = list7.indexOf(20)
    println("index1 : $index1")

    // 오른쪽에서 왼쪽으로 찾아감
    val index2 = list7.lastIndexOf(20)
    println("index2 : $index2")

    // list1 의 1 ~ 3 값만 추출
    val list8 = list1.subList(1, 3)
    println("list8 : $list8")

    println("-----------------------------")

    println("list3 : $list3")
    list3.add(10)
    list3.add(20)
    list3.add(30)
    println("list3 : $list3")

    list3.addAll(listOf(40, 50, 60, 70))
    println("list3 : $list3")

    list3.add(1, 100)
    list3.addAll(2, listOf(200, 300, 400))
    println("list3 : $list3")

    list3.remove(100)
    println("list3 : $list3")

    list3.removeAll(listOf(200, 300))
    println("list3 : $list3")

    //index 번호 1번에 해당하는 값을 제거함
    list3.removeAt(1)
    println("list3 : $list3")

    list3.set(1, 200)
    println("list3 : $list3")

    // set or [] 가능
    list3[2] = 300
    println("list3 : $list3")

    println("-----------------------------")

    // List1을 가변형으로 변경해서 list100으로 넣음
    val list100 = list1.toMutableList()
    list100.add(1000)
    println("list100 : $list100")

    // 불변형
    val list200 = list1.toList()
//    list200.add


}