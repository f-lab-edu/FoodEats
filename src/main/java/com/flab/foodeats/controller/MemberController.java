package com.flab.foodeats.controller;

import com.flab.foodeats.aop.AopAuth;
import com.flab.foodeats.interceptor.CustomAopIncterceptor;
import com.flab.foodeats.mapper.MemberMapper;
import com.flab.foodeats.model.LoginForm;
import com.flab.foodeats.model.Member;
import com.flab.foodeats.service.DeleteService;
import com.flab.foodeats.service.InsertService;
import com.flab.foodeats.service.LoginService;
import com.flab.foodeats.service.UpdateService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

/**
 * 로그인 기반 코드
 * @author yusok
 */

@RestController
@RequestMapping("/user")
public class MemberController {

  private LoginService loginService;
  private InsertService insertService;
  private UpdateService updateService;
  private DeleteService deleteService;
  private MemberMapper memberMapper;

  public MemberController(LoginService loginService, InsertService insertService, UpdateService updateService, DeleteService deleteService, MemberMapper memberMapper) {
    this.loginService = loginService;
    this.insertService = insertService;
    this.updateService = updateService;
    this.deleteService = deleteService;
    this.memberMapper = memberMapper;
  }

  /**
   * 필터 / 인터셉터 적용 x
   * 회원가입
   * 로그인
   * 로그아웃
   * 전체 사용자 조회
   */


  // 회원가입
  @PostMapping("/insert")
  public ResponseEntity<?> insertUser(@Valid @RequestBody Member member, BindingResult bindingResult) {
    inputValid(bindingResult);
    return insertService.insert(member);
  }


  // 로그인
  @PostMapping("/login")
  public ResponseEntity<?> login (@Valid @RequestBody LoginForm loginForm, BindingResult bindingResult) {
    inputValid(bindingResult);
    return loginService.login(loginForm);
  }


  // 로그아웃
  @PostMapping("/logout")
  public ResponseEntity<Void> logout(HttpServletRequest request) {

    HttpSession session = request.getSession(false);
    if(session != null) {
      session.invalidate();
      return new ResponseEntity<>(HttpStatus.OK);
    }

    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

  }

  // 전체 조회
  @GetMapping("/all")
  public List<Member> findAll() {

    return memberMapper.findAll();

  }

  // 단일 회원 조회 (by ID)
  @GetMapping("/findinfo")
  public Member findMemberById(@RequestBody Member member){

    return memberMapper.findMemberById(member.getId());

  }




  /**
   * 필터 / 입터셉터 적용 메소드
   * 회원수정 (update)
   * 회원탈퇴 (delete)
   */

  // 회원정보 수정
  //@AopAuth
  @CustomAopIncterceptor // 어노테이션 적용
  @PutMapping("/update")
  public ResponseEntity<?> update (@Valid @RequestBody Member member, BindingResult bindingResult) {

    inputValid(bindingResult);
    return updateService.update(member);

  }

  // 회원 삭제
  //@AopAuth
  @CustomAopIncterceptor
  @DeleteMapping("/delete")
  public ResponseEntity<?> deleteUser(@Valid @RequestBody Member member, BindingResult bindingResult) {

    inputValid(bindingResult);
    return deleteService.delete(member.getId());

  }


  private void inputValid(BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      throw new IllegalArgumentException("잘못된 입력 값");
    }
  }




}
