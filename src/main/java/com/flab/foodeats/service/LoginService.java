package com.flab.foodeats.service;


import com.flab.foodeats.SessionConst;
import com.flab.foodeats.mapper.MemberMapper;
import com.flab.foodeats.model.LoginForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;


@Slf4j
@Service
public class LoginService {

    private final MemberMapper memberMapper;

    public LoginService(MemberMapper memberMapper) {
        this.memberMapper = memberMapper;
    }

    /**
     *
     * @param loginForm
     * @return
     */
    public ResponseEntity<?> login(LoginForm loginForm) {


        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();

        try {

            if ((memberMapper.findPassword(loginForm.getId())).equals(loginForm.getPassword())) {

                log.info("로그인 성공");
                HttpSession session = request.getSession();
                session.setAttribute(SessionConst.LOGIN, loginForm);
                return new ResponseEntity<>(loginForm, HttpStatus.OK);

            } else {

                log.info("로그인 실패 : 비밀번호 오류");
                Map<String, Object> result = new HashMap<>();
                result.put("code", "BAD");
                result.put("message", "비밀번호 오류입니다.");
                return new ResponseEntity(result,HttpStatus.BAD_REQUEST);

            }
        } catch (NullPointerException e) {

            log.info("로그인 실패 : nullpont 아이디 오류");
            throw new NullPointerException("no Id.");
        }
    }

}
