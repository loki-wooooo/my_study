package io.github.lokiwooooo.util.dto;

import jakarta.persistence.MappedSuperclass;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(makeFinal = false, level = AccessLevel.PRIVATE)
@MappedSuperclass
public class ResponsePageDto extends ResponseCommonDto{

    Integer pageNumber;      // 현재 페이지 번호
    Integer pageSize;        // 페이지 크기
    Integer totalPages;      // 총 페이지 수
    Long totalElements;  // 총 요소 수
    Boolean first;       // 첫 페이지 여부
    Boolean last;        // 마지막 페이지 여부
}
