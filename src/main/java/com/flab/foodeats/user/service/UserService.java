package com.flab.foodeats.user.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.flab.foodeats.user.model.DeleteFormDTO;
import com.flab.foodeats.user.model.InsertFormDTO;
import com.flab.foodeats.user.model.LoginFormDTO;
import com.flab.foodeats.user.model.UpdateFormDTO;

@Service
public interface UserService {

	/**
	 * 회원가입 메소드
	 *
	 * @param insertFormDTO 회원가입 시 입력하는 정보
	 * @return
	 */
	Object insertUserInfo(InsertFormDTO insertFormDTO);

	/**
	 * 로그인 메소드
	 *
	 * @param loginFormDTO 로그인 시 입력하는 정보
	 * @return
	 */
	Object login(LoginFormDTO loginFormDTO);

	/**
	 * 회원정보 수정 메소드
	 *
	 * @param loginFormDTO 현재 로그인 된 사용자의 정보를 받는 객체
	 * @param updateFormDTO 수정할 정보를 받는 객체
	 * @return
	 */
	Object updateUserInfo(LoginFormDTO loginFormDTO, UpdateFormDTO updateFormDTO);

	/**
	 * 회원정보 삭제 메소드
	 *
	 * @param loginFormDTO 현재 로그인 된 사용자의 정보를 받는 객체
	 * @param deleteFormDTO 삭제할 id와 password를 받는 객체
	 * @return
	 */
	Object deleteUserInfo(LoginFormDTO loginFormDTO, DeleteFormDTO deleteFormDTO);

	/**
	 * 모든 회원정보 반환 메소드
	 *
	 * @return
	 */
	List<InsertFormDTO> findAllUser();
}
