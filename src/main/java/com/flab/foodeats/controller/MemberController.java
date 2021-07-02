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
        if(bindingResult.hasErrors()){ // 넘어온 값이 member 객체에 제대로 바인딩이 이루어지지 않는다면
            response.getWriter().write("error"); // 해당 메시지가 http body에 들어간다
            // (추후 DB에 동일한 Id 값이 있을 때 생성되는 에러 만들어주기)
        }else {
            memberMapper.save(member.getMemberId(),member.getPassword(),member.getName());
            response.getWriter().write("success");
        }
    }

    // 로그인
    @PostMapping("/login")
    public int login(@Valid @ModelAttribute("loginForm") LoginForm loginForm, BindingResult bindingResult,
                     HttpServletRequest request, HttpServletResponse response) throws IOException{
        String dbPassword;
        response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8");
        if(bindingResult.hasErrors()) { // 제대로 된 바인딩이 안 이루어졌을 때
            response.getWriter().write("error"); // HTTP의 body 부에 들어간다.
        }
        try {
            // 입력값으로 들어온 id의 DB에 저장된 비밀번호
            // loginForm.getMemberId()에 해당하는 값이 db에 없을 경우 NullPointerException이 발생 --> try,catch
            // 입력값으로 넘어온 password: loginForm.getPassword()
            // 입력값으로 넘어온 id의 db에 저장된 비밀번호: memberMapper.findPassword(loginForm.getMemberId())
            if((memberMapper.findPassword(loginForm.getMemberId())).equals(loginForm.getPassword())){
                HttpSession session = request.getSession();
                session.setAttribute(loginForm.getMemberId(),memberMapper.findIdPass(loginForm.getMemberId()));
                return 0;
            }
        }catch (Exception e){
            e.printStackTrace();
            return 2;
        }
        return -1;
    }

    // 후에 인터셉터 처리를 통해 로그인 한 사용자만이 해당 요청을 정상적으로 수행하게끔 수정 예정
    @PutMapping("/update")
    public void update(@Valid @ModelAttribute("member") Member member, BindingResult bindingResult,
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
            response.getWriter().write("0"); // 성공
        }
        response.getWriter().write("1"); // 에러
    }

    @PostMapping("/logout")
    public void logout(HttpServletRequest request, HttpServletResponse response)throws IOException{
        HttpSession session = request.getSession(false);
        response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8");
        if(session != null){
            // 세션종료
            session.invalidate();
            response.getWriter().write("0"); // 성공
        }
        response.getWriter().write("1"); // 에러
    }
}
