package study.datajpa.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)

//tostring시 해당 내용의 컬럼만 찍음
//가급적 연관관계 변수는 tostring x
@ToString(of = {"id", "username", "age"})
@NamedQuery(
        name = "Member.findByUsername",
        query = "select m from Member m where m.username = :username"
)
@NamedEntityGraph(name = "Member.all", attributeNodes = @NamedAttributeNode("team"))
public class Member extends JpaBaseEntity{

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;
    private String username;
    private Integer age;

    /*
     * 가짜 객체를 갖고있다가, 실제로 team을 호출 할 때 불러옴
     * */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;


    // 아무렇게나 호출 X
    // JPA Entity는 기본 생성자가 필요함
    // 표준스팩 protected 까지만 허용함
//    protected Member(){}


    public Member(String username, Integer age, Team team) {
        this.username = username;
        this.age = age;
        this.team = team;
    }

    public Member(String username, Integer age) {
        this.username = username;
        this.age = age;
    }

    public Member(String username) {
        this.username = username;
    }


    //연관관계 편의 메서드
    public void changeTeam(final Team team) {
        this.team = team;
        team.getMembers().add(this);
    }
}
