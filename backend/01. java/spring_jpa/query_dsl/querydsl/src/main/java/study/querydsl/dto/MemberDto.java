package study.querydsl.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MemberDto {
    private String username;
    private int age;

    /**
     * Query DSL의 의존도를 갖게됨
     * DTO의 방향성에서 순수하지 않음 (DTO는 변수를 담는 공간인데 의존성이 높아짐)
     *  -> 깔끔하게 갖고갈 수 없음.
     * */
    @QueryProjection
    public MemberDto(final String username, final int age) throws Exception{
        this.username = username;
        this.age = age;
    }
}
