package com.flab.foodeats.service;


import com.flab.foodeats.SessionConst;
import com.flab.foodeats.mapper.MemberMapper;
import com.flab.foodeats.model.LoginForm;
import com.flab.foodeats.model.Member;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Service
public class UpdateService {

    private MemberMapper memberMapper;

    public UpdateService(MemberMapper memberMapper) {
        this.memberMapper = memberMapper;
    }

    public ResponseEntity<?> update(Member member) {

        //session
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        HttpSession session = request.getSession(false);
        LoginForm memberSession = (LoginForm) session.getAttribute(SessionConst.LOGIN);

        // 사용자 인증
        if ((memberSession.getId()).equals(member.getId())) {

            memberMapper.updateInfo(member.getId(), member.getPassword(), member.getName());
            return new ResponseEntity<>(member, HttpStatus.OK);

        } else {

            Map<String, Object> result = new HashMap<>();
            result.put("code", "BAD");
            result.put("message", "id가 일치하지 않습니다.");
            return new ResponseEntity(result,HttpStatus.BAD_REQUEST);

        }
    }
}
