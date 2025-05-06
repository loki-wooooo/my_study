package io.github.lokiwooooo.domain.user.repository;

import io.github.lokiwooooo.domain.user.dto.UserDto;
import io.github.lokiwooooo.domain.user.entity.User;
import io.github.lokiwooooo.mapper.GenericMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper extends GenericMapper<UserDto, User> {
}
