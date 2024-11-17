package com.group.libraryapp.dto.user.request

import org.springframework.lang.Nullable

class UserCreateRequest(
    val name: String, // kotlin에서 null에 대한 내용 확인
    val age: Int?
)
