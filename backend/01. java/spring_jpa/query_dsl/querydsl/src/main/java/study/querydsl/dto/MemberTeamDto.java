package study.querydsl.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

@Data
public class MemberTeamDto {

    private Long memberId;
    private String username;
    private Integer age;
    private Long teamId;
    private String teamName;

    //단점 DTO가 순수했으면 좋겠는데 QueryDSL에 의존으로 변경됨
    @QueryProjection
    public MemberTeamDto(final Long memberId, final String username, final Integer age, final Long teamId, final String teamName) throws Exception {
        this.memberId = memberId;
        this.username = username;
        this.age = age;
        this.teamId = teamId;
        this.teamName = teamName;
    }

}
