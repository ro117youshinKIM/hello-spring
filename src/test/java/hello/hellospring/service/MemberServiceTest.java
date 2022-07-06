package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


public class MemberServiceTest {
//    MemberService memberService = new MemberService();
//    MemoryMemberRepository memberRepository = new MemoryMemberRepository();
    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }
    @AfterEach
    public void afterEach() {
        memberRepository.clearStore();
    }
    // Test코드는 Build될 때 실제 코드에 포함되지 않기 때문에 직관적으로 짜도 괜찮다.
    // 그래서 메소드명을 한글로 해도 무방
    @Test
    public void 회원가입() throws Exception {
        //given
        Member member = new Member();
        member.setName("hello");

        //when
        Long saveId = memberService.join(member);

        //then
        Member findMember = memberRepository.findById(saveId).get();
        assertEquals(member.getName(), findMember.getName());

    }
    //
    @Test
    public void 중복_회원_예외() {
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");
        //when
        memberService.join(member1);
        // try catch보다 효율적인 방법
        // assertThrows, 람다식이 실행이 되면 앞의 예외가 터져야 한다.
        // ctrl + alt + v : 필요한 문법을 자동 완성
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다");
//        try {
//            memberService.join(member2);
//            fail();
//        } catch (IllegalStateException e) {
//            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다."); // 성공
//            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.111"); // 실패
//        }
        //then

    }
}
