package io.github.lokiwooooo.domain.token.repository;

import io.github.lokiwooooo.domain.token.dto.TokenDto;
import io.github.lokiwooooo.domain.token.entity.Token;
import io.github.lokiwooooo.mapper.GenericMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TokenMapper extends GenericMapper<TokenDto, Token> {
    @Mapping(target = "user", source = "userDto")
    Token toEntity(TokenDto tokenDto);
}
