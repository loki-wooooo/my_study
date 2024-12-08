package com.group.libraryapp.service.user

import com.group.libraryapp.domain.user.User
import com.group.libraryapp.domain.user.UserRepository
import com.group.libraryapp.dto.user.request.UserCreateRequest
import com.group.libraryapp.dto.user.request.UserUpdateRequest
import com.group.libraryapp.dto.user.response.UserLoanHistoryResponse
import com.group.libraryapp.dto.user.response.UserResponse
import com.group.libraryapp.utils.fail
import com.group.libraryapp.utils.findByIdOrThrow
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserService(
    private val userRepository: UserRepository
) {

    @Transactional
    fun saveUser(requesat: UserCreateRequest) {
        val newUser = User(null, requesat.name, requesat.age)
        userRepository.save(newUser)
    }

    @Transactional(readOnly = true)
    fun getUsers(): List<UserResponse> {

        //존재하는 유저를 UserResponse로 변경처리
        return userRepository.findAll().map { user ->
            UserResponse.of(user)
        }
    }

    @Transactional
    fun updateUserName(request: UserUpdateRequest) {
        // ::IllegalArgumentException -> java IllegalArgumentException::New
//        val user = userRepository.findById(request.id).orElseThrow(::IllegalArgumentException)
        //curd extension 을 사용해서 처리할 수 있음.
//        val user = userRepository.findByIdOrNull(request.id) ?: fail()
        // 확장 함수를 이용해서 해당 내용을 더 간략하게 변경처리
        val user = userRepository.findByIdOrThrow(request.id)
        user.updateName(request.name)
    }

    @Transactional
    fun deleteUser(name: String) {
        val user = userRepository.findByName(name) ?: fail()
        userRepository.delete(user)
    }

    @Transactional(readOnly = true)
    fun getUserLoanHistories(): List<UserLoanHistoryResponse> {
        //리팩토링 변경 처리
        return userRepository.findAllWithHistories().map(UserLoanHistoryResponse::of)
    }

}