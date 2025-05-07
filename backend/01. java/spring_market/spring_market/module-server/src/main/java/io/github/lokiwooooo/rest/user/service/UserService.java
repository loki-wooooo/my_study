package io.github.lokiwooooo.rest.user.service;

import io.github.lokiwooooo.domain.user.dto.UserDto;
import io.github.lokiwooooo.domain.user.entity.User;
import io.github.lokiwooooo.domain.user.repository.UserMapper;
import io.github.lokiwooooo.domain.user.repository.UserRepository;
import io.github.lokiwooooo.rest.user.dto.UserListResponseDto;
import io.github.lokiwooooo.rest.user.dto.UserRequestDto;
import io.github.lokiwooooo.rest.user.dto.UserResponseDto;
import io.github.lokiwooooo.userdetail.CustomUserDetails;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Transactional(readOnly = true)
@Slf4j
public class UserService {

    UserRepository userRepository;
    UserMapper userMapper;

    public UserListResponseDto findAll(
            final CustomUserDetails customUserDetails
            , final UserRequestDto userRequestDto
    ) {

        //Step.1 user 정보 호출
        List<User> userList = userRepository.findAll();
        List<UserDto> userDtoList = userMapper.toDtoList(userList);

        //Step.2 user 정보를 list response dto로 변환
        List<UserResponseDto> userResponseDtoList = userDtoList.stream()
                .map(userDto -> UserResponseDto.builder()
                        .id(userDto.getId())
                        .name(userDto.getName())
                        .email(userDto.getEmail())
                        .build())
                .collect(Collectors.toList());

        //Step.3 리턴
        return UserListResponseDto.builder()
                .userResponseDtoList(userResponseDtoList)
                .build();
    }

    // 사용자 명으로 찾기
    public UserResponseDto findByName(final UserRequestDto userRequestDto) {

        User user = userRepository.findByName(userRequestDto.getName()).orElse(null);
        UserDto userDtoData = userMapper.toDto(user);
        return UserResponseDto.builder()
                .id(userDtoData.getId())
                .name(userDtoData.getName())
                .email(userDtoData.getEmail())
                .httpStatus(HttpStatus.OK)
                .build();
    }

    // 사용자 ID로 찾기
    public UserResponseDto findById(
            final CustomUserDetails customUserDetails
            , final String id
    ) {
        User user = userRepository.findById(id).orElse(null);
        if (user == null) {
            return UserResponseDto.builder().id(id).httpStatus(HttpStatus.NOT_FOUND).build();
        }
        UserDto userDto = userMapper.toDto(user);
        return UserResponseDto.builder()
                .id(userDto.getId())
                .name(userDto.getName())
                .email(userDto.getEmail())
                .httpStatus(HttpStatus.OK).build();
    }


}
