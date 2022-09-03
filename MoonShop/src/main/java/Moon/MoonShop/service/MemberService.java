package moon.moonshop.service;


import lombok.RequiredArgsConstructor;
import moon.moonshop.domain.member.Member;
import moon.moonshop.repository.member.MemberRepositoryV2;
import moon.moonshop.repository.member.MemberRepositoryV3;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepositoryV3 memberRepository;

    /**
     * @return null이면 중복 회원 존재
     * save까지 처리.
     */
    @Transactional
    public Member join(Member member) throws SQLException {
        Member memberValid = memberRepository.findByUserId(member.getUserId())
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
        return memberRepository.findByUserId(loginId)
                .filter(member -> member.getPassword().equals(password))
                .orElse(null);
    }

    @Transactional
    public void updatePassword(Member member, String newPw) {
        memberRepository.updatePw(member.getUserId(),newPw);
    }

}
