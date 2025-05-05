package io.github.lokiwooooo.domain.log.repository;

import io.github.lokiwooooo.domain.log.dto.LogDto;
import io.github.lokiwooooo.domain.log.entity.Log;
import io.github.lokiwooooo.mapper.GenericMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LogMapper extends GenericMapper<LogDto, Log> {
}

