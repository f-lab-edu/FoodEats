package com.flab.foodeats.controller;

import com.flab.foodeats.mapper.MemberMapper;
import com.flab.foodeats.model.LoginForm;
import com.flab.foodeats.model.Member;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequestMapping("/user")
public class MemberController {

    private MemberMapper memberMapper;

    public MemberController(MemberMapper memberMapper){
        this.memberMapper = memberMapper;
    }

    // 회원가입
    @PostMapping("/insert")
    public void insertUser(@Valid @ModelAttribute("member") Member member, BindingResult bindingResult,
                           HttpServletResponse response) throws IOException {
        response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8");
        if(bindingResult.hasErrors()){
            response.getWriter().write("error"); // body에 들어간다
        }else {
            memberMapper.save(member.getMemberId(),member.getPassword(),member.getName());
            response.getWriter().write("success");
        }
    }

    // 로그인
    @PostMapping("/login")
    public int login(@Valid @ModelAttribute("loginForm") LoginForm loginForm, BindingResult bindingResult,
                     HttpServletRequest request, HttpServletResponse response) throws IOException{
        response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8");
        if(bindingResult.hasErrors()) { // 제대로 된 바인딩이 안 이루어졌을 때
            response.getWriter().write("error"); // HTTP의 body 부에 들어간다.
        }
        // 입력한 id의 비밀번호 받아오기 (DB에 저장된 비밀번호)
        String dbPassword = memberMapper.findPassword(loginForm.getMemberId());
        if(dbPassword.equals(loginForm.getPassword())){
            HttpSession session = request.getSession();
            // 후에 수정할 부분 --> 세션의 값에 꼭 필요한 값만 집어넣도록
            session.setAttribute(loginForm.getMemberId(),memberMapper.findIdPassword(loginForm.getMemberId()));
            return 0;
        }else {
            return 1;
        }
    }

    // 후에 인터셉터 처리를 통해 로그인 한 사용자만이 해당 요청을 정상적으로 수행하게끔 수정 예정
    @PutMapping("/update")
    public int update(@Valid @ModelAttribute("member") Member member, BindingResult bindingResult,
                      HttpServletRequest request,HttpServletResponse response) throws IOException{
        response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8");
        if(bindingResult.hasErrors()) { // 제대로 된 바인딩이 안 이루어졌을 때
            response.getWriter().write("error"); // HTTP의 body 부에 들어간다.
        }

        HttpSession session = request.getSession(false);
        // 세션에 저장되어있는 값 가져오기
        LoginForm memberSession = (LoginForm) session.getAttribute(member.getMemberId());
        // 현재 세션에 저장되어 있는 id값과 사용자가 입력한 id가 일치한다면 입력한 정보 수정
        if((memberSession.getMemberId()).equals(member.getMemberId())){
            memberMapper.updateInfo(member.getPassword(),member.getName(),member.getMemberId());
            return 0;
        }
        return 1; //error
    }

    @PostMapping("/logout")
    public int logout(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if(session != null){
            // 세션종료
            session.invalidate();
            return 0;
        }
        return 1;
    }
}
