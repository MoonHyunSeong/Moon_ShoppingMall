package moon.moonshop.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import moon.moonshop.domain.member.Member;
import moon.moonshop.dto.LoginDto;
import moon.moonshop.service.LoginService;
import moon.moonshop.web.SessionConst;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Slf4j
@Controller
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @GetMapping("/login")
    public String loginForm(@ModelAttribute("loginDto") LoginDto form) {
        return "/members/login";
    }

    @PostMapping("/login")
    public String login(@Validated @ModelAttribute("loginDto") LoginDto form, BindingResult result,
                        HttpServletRequest request) {
        //로그인 실패 시 로그인 폼으로 돌아가기.
        if (result.hasErrors()) {
            return "members/login";
        }

        // 로그인 서비스에서 로그인 체크하기. form에서 넘어온 비번과 리포지토리에서 꺼내온 비번과 같은지 비교한다.
        // 디비 연동이 안된 현재 상태에서는 init 된 유저 값이랑만 비교중이다.
        Member loginMember = loginService.login(form.getUserId(), form.getPassword());
        log.info("login? {}", loginMember);

        if (loginMember == null) {
            result.reject("loginFail", "아이디 또는 비밀번호가 일치하지 않습니다.");
            return "members/login";
        }

        //login 성공 처리

        //세션이 있으면 있는 세션 반환, 없으면 신규 세션 생성
        HttpSession session = request.getSession();
        //세션에 로그인 회원 정보 보관
        session.setAttribute(SessionConst.LOGIN_MEMBER, loginMember);

        return "redirect:/";
    }


    @PostMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return "redirect:/";
    }
}
