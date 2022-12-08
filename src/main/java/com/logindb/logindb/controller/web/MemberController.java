package com.logindb.logindb.controller.web;

import com.logindb.logindb.controller.web.form.LoginForm;
import com.logindb.logindb.controller.web.form.RegisterForm;
import com.logindb.logindb.domain.Member;
import com.logindb.logindb.dto.LoginDto;
import com.logindb.logindb.dto.MemberInsertDto;
import com.logindb.logindb.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
@Controller
public class MemberController {

    private final MemberService memberService;

    @GetMapping("login")
    public String loginForm(Model model){
        LoginForm loginForm = new LoginForm();
        model.addAttribute("form", loginForm);
        return "login/login";
    }

    @PostMapping("login")
    public String login(@ModelAttribute LoginForm loginForm, HttpServletResponse response, HttpSession session) throws IOException {

        LoginDto loginDto = new LoginDto();
        loginDto.setId(loginForm.getId());
        loginDto.setPwd(loginForm.getPwd());

        Member findMember = memberService.login(loginDto);
        if(findMember == null) return "redirect:/";


        session.setAttribute("member", findMember);

        return "redirect:/success";
    }

    @GetMapping("success")
    public String success(HttpSession session, Model model) throws IOException {

        if(session.getAttribute("member") == null) return "redirect:/";

        Member sessionMember = (Member) session.getAttribute("member");
        log.debug("sessionMember: {}", sessionMember);

        model.addAttribute("member", sessionMember);
        return "success";
    }

    @GetMapping("logout")
    public void logout(HttpServletResponse response, HttpSession session) throws IOException {
        session.invalidate();
        response.sendRedirect("/");
    }


    @GetMapping("register")
    public String registerForm(Model model){
        RegisterForm registerForm = new RegisterForm();
        model.addAttribute("form", registerForm);
        return "login/register";
    }

    @PostMapping("register")
    public String register(Model model, RegisterForm form){
        MemberInsertDto memberInsertDto = new MemberInsertDto();

        memberInsertDto.setId(form.getId());
        memberInsertDto.setPwd(form.getPwd());
        memberService.createMember(memberInsertDto);

        return "redirect:/login";
    }
}
