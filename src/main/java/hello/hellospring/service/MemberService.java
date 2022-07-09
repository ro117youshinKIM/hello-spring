package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
// Service는 Business에 의존적으로 설계한다.
// ctrl + shift + t : jUnit Test 파일을 만든다.
public class MemberService {
//    private final MemberRepository memberRepository = new MemoryMemberRepository();
    // new 해서 생성하는 것이 아니라 외부에서 넣어주도록 constructor를 생성해라.
    private final MemberRepository memberRepository;
    // MemberService 입장에서 new 해서 사용하지 않고 외부에서 memberRepository를 넣어준다.
    // 이것이 DI.
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /*
    *  회원가입
    */
    public Long join(Member member) {
        // 같은 이름이 있는 중복 회원은 X
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }
    // ctrl + alt + m : 자동 method 추가
    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    /*
    * 전체 회원 조회
    * */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
