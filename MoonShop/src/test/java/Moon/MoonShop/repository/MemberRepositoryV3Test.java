package moon.moonshop.repository;

import moon.moonshop.domain.member.Member;
import moon.moonshop.repository.member.MemberRepositoryV2;
import moon.moonshop.repository.member.MemberRepositoryV3;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class MemberRepositoryV3Test {

    @Autowired
    public MemberRepositoryV3 memberRepository;

    @Test
    public void save() throws Exception {

        //given
        Member member = new Member();
        member.setUserId("moon2");
        member.setPassword("456");
        member.setPoint(50000);
        member.setCity("seoul");
        member.setStreet("maebong-gil");
        member.setZipcode("04731");

        //when
        Member savedMember = memberRepository.save(member);

        //then
        assertThat(savedMember.getUserId()).isEqualTo("moon2");

    }

    @Test
    public void findByUser() throws Exception {
        //given
        String userId = "moon1";
        //when
        Optional<Member> findUser = memberRepository.findByUserId(userId);

        //then
        assertThat(findUser.get().getUserId()).isEqualTo(userId);
    }


    @Test
    public void removeUser() throws Exception {
        //given
        Optional<Member> findUser = memberRepository.findByUserId("moon2");
        //when
        memberRepository.removeId(findUser.get().getUserId());
        //then

        Optional<Member> afterRemove = memberRepository.findByUserId("moon2");
        assertThat(afterRemove).isEmpty();
    }

    @Test
    public void updatePW() throws Exception {
        //given
        Optional<Member> findUser = memberRepository.findByUserId("moon1");
        //when
        memberRepository.updatePw(findUser.get().getUserId(), "123");
        //then
        Optional<Member> afterUpdate = memberRepository.findByUserId("moon1");
        assertThat(afterUpdate.get().getPassword()).isEqualTo("123");

    }

}