package com.group.libraryapp.domain.book

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

// 기본생성자가 있어야지 argument로 생성이 가능함
// kotlin은 주생성자를 받고 있기때문에, 기본생성자가 없어서 에러남
// plugin을 이용해서 변경
// getter는 일반적으로 kotlin에서 사용 가능함
@Entity
class Book(
    val name: String,

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
}