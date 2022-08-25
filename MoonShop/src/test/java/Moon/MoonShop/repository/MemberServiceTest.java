package moon.moonshop.repository;

import moon.moonshop.domain.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired
    public MemberRepository memberRepository;

    @Test
    public void update() throws Exception {
        //given
        Member member = new Member();

        member.setUserId("moon");
        member.setPassword("123");
        member.setUserName("moon good");

        Member savedMember = memberRepository.save(member);
        //when

        memberRepository.updatePassword(savedMember, "456");
        Optional<Member> validMember = memberRepository.findByLoginId(savedMember.getUserId());

        //then

        Assertions.assertThat(validMember.get().getPassword()).isEqualTo("456");

     }

}