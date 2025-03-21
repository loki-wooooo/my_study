package com.group.libraryapp.service.user

import com.group.libraryapp.domain.user.User
import com.group.libraryapp.domain.user.UserRepository
import com.group.libraryapp.domain.user.loanhistory.UserLoanHistory
import com.group.libraryapp.domain.user.loanhistory.UserLoanHistoryRepository
import com.group.libraryapp.domain.user.loanhistory.UserLoanStatus
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
    @Autowired val userRepository: UserRepository,
    @Autowired val userLoanHistoryRepository: UserLoanHistoryRepository,
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
                User(null, "A", 20),
                User(null, "B", null),
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
        val savedUser = userRepository.save(User(null, "A", null));
        // 한번생성된 유저는 절대 null이 나올수없어서 "!!"로 not null로 변경처리
        val requesr = UserUpdateRequest(savedUser.id!!, "B");

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
        userRepository.save(User(null, "A", null));

        //when
        userService.deleteUser("A");

        //then
        assertThat(userRepository.findAll()).isEmpty()
    }

    @Test
    @DisplayName("대출 기록이 없는 유저도 응답에 포함된다.")
    fun getUserLoanHistoriesTest() {
        //given
        userRepository.save(User(null, "A", null));

        //when
        val results = userService.getUserLoanHistories();

        //then
        assertThat(results).hasSize(1)
        assertThat(results[0].name).isEqualTo("A")
        assertThat(results[0].books).isEmpty()
    }

    @Test
    @DisplayName("대출 기록이 많은 유저의 응답이 정상 동한다.")
    fun getUserLoanHistoriesTest2() {
        //given
        val savedUser = userRepository.save(User(null, "A", null));
        userLoanHistoryRepository.saveAll(listOf(
            UserLoanHistory.fixture(null, savedUser, "책1", UserLoanStatus.LOADED),
            UserLoanHistory.fixture(null, savedUser, "책2", UserLoanStatus.LOADED),
            UserLoanHistory.fixture(null, savedUser, "책3", UserLoanStatus.RETURNED),
        ))


        //when
        val results = userService.getUserLoanHistories();

        //then
        assertThat(results).hasSize(1)
        assertThat(results[0].name).isEqualTo("A")
        assertThat(results[0].books).hasSize(3)
        assertThat(results[0].books).extracting("name").containsExactlyInAnyOrder("책1","책2","책3")
        assertThat(results[0].books).extracting("isReturn").containsExactlyInAnyOrder(false,false,true)
    }

}