package com.group.libraryapp.service

import com.group.libraryapp.domain.user.UserRepository
import com.group.libraryapp.dto.user.request.UserCreateRequest
import com.group.libraryapp.service.user.UserService
import org.assertj.core.api.AssertionsForInterfaceTypes.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class UserServiceTest @Autowired constructor(
    // @Autowired 생략 가능
//    @Autowired val userService: UserService,
//    @Autowired val userRepository: UserRepository
    @Autowired val userService: UserService,
    @Autowired val userRepository: UserRepository
) {

    @Test
    fun saveUserTest() {

        // given
        val request = UserCreateRequest("hswoo", null);

        // when
        userService.saveUser(request);

        // than
        val result = userRepository.findAll();
        assertThat(result).hasSize(1);
        assertThat(result[0].name).isEqualTo("hswoo")
        assertThat(result[0].age).isNull()

    }
}