package com.example.userservice.service;


import com.example.userservice.dto.UserDto;
import com.example.userservice.jpa.UserEntity;

public interface UserService {

    UserDto getUserByUserId(final String userId) throws Exception;

    // DB에 있는 그대로의 데이터를 보여주기위해서 해당 entity로 정의함
    Iterable<UserEntity> getUserByAll() throws Exception;

    UserDto createUser(final UserDto userDto) throws Exception;
}
