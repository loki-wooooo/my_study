package io.github.lokiwooooo.domain.log.dto;

import io.github.lokiwooooo.domain.common.dto.CommonDto;
import io.github.lokiwooooo.domain.log.entity.LogDetailType;
import io.github.lokiwooooo.domain.log.entity.LogType;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(makeFinal = false, level = AccessLevel.PRIVATE)

public class LogDto extends CommonDto {

    Long id;
    LogType logType;
    LogDetailType logDetailType;
    String logContent;
    String ip;
    List<LogDto> logDtoList;
}
