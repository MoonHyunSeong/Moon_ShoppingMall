package moon.moonshop.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import moon.moonshop.domain.member.Member;
import moon.moonshop.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.SQLException;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    /**
     * 회원가입
     */
    @GetMapping("/join")
    public String joinForm(@ModelAttribute("member") Member member) {
        return "/members/join";
    }

    @PostMapping("/join")
    public String join(@Validated @ModelAttribute Member member, BindingResult result) throws SQLException {
        if (result.hasErrors()) {
            return "/members/join";
        }

        Member joinMember = memberService.join(member);
        log.info("join Valid {} -> null 이면 가입가능", joinMember);

        // 회원가입 시 검증이 필요함. 중복 아이디면 중복을 알려야한다.
        if (joinMember != null){
            //service에서 다른 멤버가 있으면 기존 멤버를 리턴하기 때문에 null 일때만 가입가능.
            return "/members/join";
        }
        return "redirect:/";
    }


}
