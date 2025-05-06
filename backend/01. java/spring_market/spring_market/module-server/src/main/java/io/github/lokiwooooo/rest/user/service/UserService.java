package io.github.lokiwooooo.rest.user.service;

import io.github.lokiwooooo.domain.user.dto.UserDto;
import io.github.lokiwooooo.domain.user.entity.User;
import io.github.lokiwooooo.domain.user.repository.UserMapper;
import io.github.lokiwooooo.domain.user.repository.UserRepository;
import io.github.lokiwooooo.rest.user.dto.UserRequestDto;
import io.github.lokiwooooo.rest.user.dto.UserResponseDto;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Transactional(readOnly = true)
@Slf4j
public class UserService {

    UserRepository userRepository;
    UserMapper userMapper;

    public UserResponseDto findByName(final UserRequestDto userRequestDto) throws Exception {
        User user = userRepository.findByName(userRequestDto.getName()).orElse(null);
        UserDto userDtoData = userMapper.toDto(user);
        return UserResponseDto.builder()
                .id(userDtoData.getId())
                .name(userDtoData.getName())
                .email(userDtoData.getEmail())
                .httpStatus(HttpStatus.OK)
                .build();
    }


}
