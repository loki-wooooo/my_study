package study.datajpa.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

// entity는 되도록 @Data 지양
@Data
@AllArgsConstructor
public class MemberDto {

    private Long id;

    private String username;

    private String teamName;

}
