package moon.moonshop.repository;

import moon.moonshop.domain.member.Member;
import moon.moonshop.repository.member.MemberRepositoryV2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberRepositoryV2Test {

    @Autowired
    public MemberRepositoryV2 memberRepository;

    @Test
    @Rollback(value = false)
    public void save() throws Exception {

        //given
//        Member member = new Member("moon", "123", "moonhs", 50000, "seoul", "maebong-gil", "04731");
//
//        //when
//        Member savedMember = memberRepository.save(member);
//
//        //then
//        assertThat(member.getUserId()).isEqualTo(savedMember.getUserId());

    }

    @Test
    @Rollback(value = false)
    public void remove() throws Exception {
        //given
        Optional<Member> moon = memberRepository.findByUserId("moon");

        //when
        memberRepository.removeId(moon.get().getUserId());
        //then

     }
     
    @Test
    public void findMember() throws Exception {
        //given
        Member member = new Member();

        member.setUserId("moonv2");
        member.setPassword("123");
        member.setUserId("Moonhs");

        memberRepository.save(member);
        //when

        Optional<Member> findMember =
             memberRepository.findByUserId("moonv2");
        //then

        assertThat(findMember.get().getUserId()).isEqualTo(member.getUserId());

    }


    @Test
    public void updatePw() throws Exception {
        //given
        Optional<Member> moon = memberRepository.findByUserId("moon");
        System.out.println("moon = " + moon);

        //when
        memberRepository.updatePw(moon.get().getUserId(), "111");
        Optional<Member> moonNew = memberRepository.findByUserId("moon");
        System.out.println("moonNew = " + moonNew);
        //then

        assertThat(moonNew.get().getPassword()).isEqualTo("111");
    }
    @Test
    public void paySample() throws Exception {
        //given
        Optional<Member> moon = memberRepository.findByUserId("moon");
        System.out.println("moon = " + moon);
        Integer price1 = 30000;
        Integer price2 = 60000;
        //when

        Optional<Member> success = memberRepository.pay(moon.get().getUserId(), price1);
        System.out.println("success = " + success);
        Optional<Member> fail = memberRepository.pay(moon.get().getUserId(), price2);
        System.out.println("fail = " + fail);

        //then

        assertThat(success.get().getPoint()).isEqualTo(20000);
        assertThat(fail.isEmpty());
     }


}