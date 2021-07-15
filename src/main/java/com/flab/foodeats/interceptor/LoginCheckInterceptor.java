package com.flab.foodeats.interceptor;

import com.flab.foodeats.SessionConst;
import com.flab.foodeats.aop.AopAuth;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Slf4j
@RequiredArgsConstructor
@Component
public class LoginCheckInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String requestURI = request.getRequestURI();
        log.info("인증 체크 인터셉터 실행 {}", requestURI);
        HttpSession session = request.getSession(false);
        HandlerMethod handlerMethod = (HandlerMethod) handler;


        // 어노테이션 적용 부분
        CustomAopIncterceptor filter = handlerMethod.getMethod().getAnnotation(CustomAopIncterceptor.class);
        CustomAopIncterceptor filter2 = handlerMethod.getMethodAnnotation(CustomAopIncterceptor.class);
        //System.out.println(filter);


        // 어노테이션 정보가 없다면 작동 x
        if (filter != null) {

            if (session == null || session.getAttribute(SessionConst.LOGIN) == null) {

                log.info("미인증 사용자 요청");
                System.out.println("로그인 하고 오세요");
                return false; //false 동작안함
            }
            return true;
        }
        return false;
    }

}
