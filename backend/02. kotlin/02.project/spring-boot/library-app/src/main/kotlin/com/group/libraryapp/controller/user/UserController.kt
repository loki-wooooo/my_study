package com.group.libraryapp.controller.user

import com.group.libraryapp.dto.user.request.UserCreateRequest
import com.group.libraryapp.dto.user.request.UserUpdateRequest
import com.group.libraryapp.dto.user.response.UserLoanHistoryResponse
import com.group.libraryapp.dto.user.response.UserResponse
import com.group.libraryapp.service.user.UserService
import org.springframework.web.bind.annotation.*

@RestController
class UserController(
    private val userService: UserService,
) {

    @PostMapping("/user")
    fun saveUser(@RequestBody request: UserCreateRequest) {
        userService.saveUser(request);
    }

    // "{}" "return" 문법을 생략해서 사용할 수 있음
    @GetMapping("/user")
//    fun getUsers(): List<UserResponse> = userService.getUsers();
    fun getUsers(): List<UserResponse> {
        return userService.getUsers();
    }

    @PutMapping("/user")
    fun updateUserName(@RequestBody request: UserUpdateRequest) {
        userService.updateUserName(request);
    }

    @DeleteMapping("/user")
    // String? -> @RequestParam -> false로 자동 변경처리
//    fun deleteUser(@RequestParam name: String?) {
    fun deleteUser(@RequestParam name: String) {
        userService.deleteUser(name);
    }

    @GetMapping("/user/loan")
    fun getUserLoanHistories(): List<UserLoanHistoryResponse> {
        return userService.getUserLoanHistories();
    }

}