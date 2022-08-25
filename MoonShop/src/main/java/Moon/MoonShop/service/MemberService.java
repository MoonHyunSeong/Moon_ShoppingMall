package moon.moonshop.service;


import lombok.RequiredArgsConstructor;
import moon.moonshop.domain.member.Member;
import moon.moonshop.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    /**
     * @return null이면 중복 회원 존재
     * save까지 처리.
     */
    public Member join(Member member) throws SQLException {
        Member memberValid = memberRepository.findByLoginId(member.getUserId())
                .orElse(null);

        if (memberValid != null) {
            return memberValid;
        }

        memberRepository.save(member);
        return null;
    }

    /**
     * @Return null이면 로그인 실패
     */
    public Member login(String loginId, String password) {
        return memberRepository.findByLoginId(loginId)
                .filter(member -> member.getPassword().equals(password))
                .orElse(null);
    }

    public void updatePassword(Member member, String newPw) {
        memberRepository.updatePassword(member,newPw);
    }

}
