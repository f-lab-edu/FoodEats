package com.flab.foodeats.user.filter;

import com.flab.foodeats.user.util.SessionConst;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.PatternMatchUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Slf4j
public class FilterAuth implements Filter {

  private static final String[] whitelist = {"/", "/user/login", "/user/insert", "/logout"};

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {
    HttpServletRequest httpRequest = (HttpServletRequest) request;
    String requestURI = httpRequest.getRequestURI();
    HttpServletResponse httpResponse = (HttpServletResponse) response;
    try {
      log.info("인증 체크 필터 시작 {}", requestURI);
      if (isLoginCheckPath(requestURI)) {
        log.info("인증 체크 로직 실행 {}", requestURI);
        HttpSession session = httpRequest.getSession(false);
        System.out.println("분기점 (다른 사람 정보 수정 체크 )");
        System.out.println(session);
        System.out.println(session.getAttribute(SessionConst.LOGIN));
        if (session == null || session.getAttribute(SessionConst.LOGIN) == null) {
          log.info("미인증 사용자 요청 {}", requestURI);
          //로그인으로 redirect
          //httpResponse.sendRedirect("/login?redirectURL=" + requestURI);
          System.out.println("로그인 하고 오세요");

          return; //여기가 중요, 미인증 사용자는 다음으로 진행하지 않고 끝! //뒤에 오는 인터셉터랑 돌아오는 필터도 실행x
        }
      }
      chain.doFilter(request, response);
    } catch (Exception e) {
      throw e; //예외 로깅 가능 하지만, 톰캣까지 예외를 보내주어야 함
    } finally {
      log.info("인증 체크 필터 종료 {}", requestURI);
    }
  }


  private boolean isLoginCheckPath(String requestURI) {
    return !PatternMatchUtils.simpleMatch(whitelist, requestURI);
  }
}
