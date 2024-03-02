package study.querydsl.dto;

import lombok.Data;

@Data
public class MemberSearchCondition {

    // 회원명, 팀명, 나이(ageGoe, ageLoe)
    private String username;
    private String teamName;
    private Integer age; //integer 사용이유 값이 null일수도 있어서
    private Integer ageLoe; //크거나 같거나
    private Integer ageGoe; //작거나 같거나

}
