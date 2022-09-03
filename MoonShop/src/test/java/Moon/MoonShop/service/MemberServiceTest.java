package moon.moonshop.service;

import moon.moonshop.domain.member.Member;
import moon.moonshop.repository.member.MemberRepositoryV2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

//@SpringBootTest
//@Transactional
//class MemberServiceTest {
//
//    @Autowired
//    private MemberService memberService;
//    @Autowired
//    private MemberRepositoryV2 memberRepository;
//
//    @Test
//    public void join() throws Exception {
//        //given
//        Member member = new Member("moon4", "123", "moonhs", 50000, "seoul", "maebong-gil", "04731");
//        //when
//
//        //then
//        assertThat(memberService.join(member)).isNull();
//
//     }
//
//     @Test
//     public void login() throws Exception {
//         //given
//         Member moon4 = memberService.login("moon3", "123");
//         //when
//         assertThat(moon4).isNotNull();
//         //then
//
//      }
//
//      @Test
//      public void updatePw() throws Exception {
//          //given
//          Member member = new Member("moon3", "123", "moonhs", 50000, "seoul", "maebong-gil", "04731");
//
//
//          //when
//          memberService.updatePassword(member, "456");
//          Optional<Member> moon3 = memberRepository.findByUserId("moon3");
//          //then
//          assertThat(moon3.get().getPassword()).isEqualTo("456");
//
//       }
//
//}