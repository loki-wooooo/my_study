package com.group.libraryapp.service.user

import com.group.libraryapp.domain.user.User
import com.group.libraryapp.domain.user.UserRepository
import com.group.libraryapp.dto.user.request.UserCreateRequest
import com.group.libraryapp.dto.user.request.UserUpdateRequest
import org.assertj.core.api.AssertionsForInterfaceTypes.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.DisplayName
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

    @AfterEach
    fun clean() {
        userRepository.deleteAll()
    }

    @Test
    @DisplayName("유저 저장이 정상 작동한다.")
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

    @Test
    @DisplayName("유저 조회가 정상 작동한다.")
    fun getUsersTest() {

        // givne
        userRepository.saveAll(
            listOf(
                User("A", 20),
                User("B", null),
            )
        )

        // when
        val results = userService.getUsers();

        // then
        assertThat(results).hasSize(2) //[UserResponses(),UserResponses()]
        assertThat(results).extracting("name").containsExactlyInAnyOrder("A", "B") // ["A", "B"]
        assertThat(results).extracting("age").containsExactlyInAnyOrder(20, null)
    }

    @Test
    @DisplayName("유저 수정이 정상 작동한다.")
    fun updateUserNameTest() {
        //givne
        val savedUser = userRepository.save(User("A", null));
        val requesr = UserUpdateRequest(savedUser.id, "B");

        //when
        userService.updateUserName(requesr);

        //then
        val result = userRepository.findAll()[0]
        assertThat(result.name).isEqualTo("B")
    }

    @Test
    @DisplayName("유저 삭제가 정상 작동한다.")
    fun deletedUserTest() {
        //given
        userRepository.save(User("A", null));

        //when
        userService.deleteUser("A");

        //then
        assertThat(userRepository.findAll()).isEmpty()
    }
}