package com.group.libraryapp.domain.book

import javax.persistence.*

// 기본생성자가 있어야지 argument로 생성이 가능함
// kotlin은 주생성자를 받고 있기때문에, 기본생성자가 없어서 에러남
// plugin을 이용해서 변경
// getter는 일반적으로 kotlin에서 사용 가능함
@Entity
class Book(
    val name: String,

    // null 이 될수 없는 String 형식(Type)
    // Index번호가 아닌 문자열로 저장됨
    @Enumerated(EnumType.STRING)
    val type: BookType,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // null값도 가능하고 초기값은 null
    val id: Long? = null,
) {

    // 초기화 블럭
    init {
        if (name.isBlank()) {
            throw IllegalArgumentException("이름은 비어 있을 수 없습니다.")
        }
    }

    //코틀린 컨벤션에서는 companion object가 아래 들어가는게 정석
    /**
     * companion object는 클래스가 메모리에 로드될 때 함께 생성되는 동반 객체입니다
     * 클래스당 하나의 companion object만 선언
     * Java의 static 멤버와 유사한 역할을 하지만, 완전히 동일하지는 않습니다
     *
     * 클래스 레벨의 함수와 변수 정의
     * 팩토리 메서드 구현
     * 싱글톤 패턴 구현
     *
     * --> 정적 팩토리 메서드를 통해 Test 코드에서는 문제없이 사용할 수 있음.
     * */
    companion object {
        fun fixture(
            // default 파라미터
            name: String = "책 이름",
            type: BookType = BookType.COMPUTER,
            id: Long? = null,
        ): Book {
            return Book(name, type, id);
        }
    }
}