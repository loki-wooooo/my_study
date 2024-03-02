package study.datajpa.contoller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import study.datajpa.dto.MemberDto;
import study.datajpa.entity.Member;
import study.datajpa.repository.MemberRepository;

import javax.annotation.PostConstruct;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberRepository memberRepository;

    @GetMapping("/members/{id}")
    public String findMember(@PathVariable("id") Long id) throws Exception{
        Member member = memberRepository.findById(id).get();
        return member.getUsername();
    }

    // 도메인 클래스 컨버터
    @GetMapping("/members/{id}")
    public String findMember2(@PathVariable("id") Member member) throws Exception{
        return member.getUsername();
    }

    // rest api return entity -> dto
    // Page 객체는 그대로 반환해도 괜찮음
    @GetMapping("/members")
    public Page<MemberDto> list(@PageableDefault(size = 5) final Pageable pageable) throws Exception{
        Page<Member> members = memberRepository.findAll(pageable);
        Page<MemberDto> memberDtos = members.map(member -> new MemberDto(member.getId(), member.getUsername(), member.getTeam().getName()));
        return memberDtos;
    }

    // TEST Data 생성함
    @PostConstruct
    public void init() {
        //memberRepository.save(new Member("userA"));
        for (int i=0; i < 100; i++){
            memberRepository.save(new Member("user" + i, i));
        }
    }

}
