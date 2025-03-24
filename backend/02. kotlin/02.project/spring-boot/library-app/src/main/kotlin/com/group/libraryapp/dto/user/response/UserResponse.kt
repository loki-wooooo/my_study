package com.group.libraryapp.dto.user.response

import com.group.libraryapp.domain.user.User

// dto를 data class로 변경
// 데이터를 보유하는 목적으로 만들
data class UserResponse(
    //user: User // 변경처리
    val id: Long,
    val name: String,
    val age: Int?,
) {

    //정적팩터리 메서드를 사용하는게 가장 좋음
    companion object {
        fun of(user: User): UserResponse {
            return UserResponse(
                id = user.id!!,
                name = user.name,
                age = user.age,
            )
        }
    }

    // 부생성자를 사용하는게 좋음
//    constructor(user: User) : this(
//        // null값이 아닌값을 넣어야 하기 떄문에
//        id = user.id!!,
//        name = user.name,
//        age = user.age,
//    )
}
