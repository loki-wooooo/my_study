package study.datajpa.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"id", "name"})
public class Team {

    @Id @GeneratedValue
    @Column(name = "team_id")
    private Long id;

    private String name;

    //FK가 없는곳에 mappedBy 사용
    @OneToMany(mappedBy = "team")
    private List<Member> members = new ArrayList<Member>();

    public Team(final String name){
        this.name = name;
    }
}
