package moon.moonshop.repository;

import moon.moonshop.domain.member.Member;
import moon.moonshop.repository.member.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class MemberRepositoryTest {

//    @Autowired
//    public MemberRepository memberRepository;
//
//    @Test
//    @Rollback(value = false)
//    public void save() throws Exception {
//        //given
//        Member member = new Member();
//
//        member.setUserId("moon");
//        member.setPassword("123");
////        member.setUserName("moon good");
//
//        Member savedMember = memberRepository.save(member);
//        //when
//        Assertions.assertThat(member.getUserId()).isEqualTo(savedMember.getUserId());
//        //then
//
//     }
//
//    @Test
//    public void updatePw() throws Exception {
//        //given
//        Member savedMember = memberRepository.findByLoginId("moon").get();
//
//        //when
//
//        memberRepository.updatePassword(savedMember, "456");
//
//        Member validMember = memberRepository.findByLoginId("moon").get();
//        System.out.println("validMember = " + validMember);
//
//        //then
//
//        Assertions.assertThat(validMember.getPassword()).isEqualTo("456");
//
//     }

}