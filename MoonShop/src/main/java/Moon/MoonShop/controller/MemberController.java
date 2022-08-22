package moon.moonshop.controller;

import lombok.RequiredArgsConstructor;
import moon.moonshop.domain.member.Account;
import moon.moonshop.domain.member.Address;
import moon.moonshop.domain.member.Member;
import moon.moonshop.dto.LoginDto;
import moon.moonshop.repository.MemberRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.PostConstruct;

@Controller
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberRepository memberRepository;

    /**
     * 회원가입
     */
    @GetMapping("/join")
    public String joinForm(@ModelAttribute("member") Member member) {
        return "/members/join";
    }

    @PostMapping("/join")
    public String join(@ModelAttribute Member member, BindingResult result) {
        if (result.hasErrors()) {
            return "/members/join";
        }

        memberRepository.save(member);
        return "redirect:/";
    }


    /**
     * test 용 유저 데이터 추가
     */
    @PostConstruct
    public void init() {
        memberRepository.save(new Member(
                "gustjd617",
                "123",
                "moon",
                "gustjd617@gmail.com",
                new Address("seoul", "maebonggil", 50),
                "01012345678",
                new Account("123123", 0)
        ));
    }

}
