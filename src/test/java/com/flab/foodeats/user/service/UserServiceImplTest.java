package com.flab.foodeats.user.service;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.times;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DuplicateKeyException;

import com.flab.foodeats.user.mapper.MemberMapper;
import com.flab.foodeats.user.model.DeleteFormDTO;
import com.flab.foodeats.user.model.InsertFormDTO;
import com.flab.foodeats.user.model.LoginFormDTO;
import com.flab.foodeats.user.model.UpdateFormDTO;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

	@InjectMocks
	UserServiceImpl userService;

	@Mock
	MemberMapper memberMapper;

	@DisplayName("회원가입 성공")
	@Test
	void insertTest() {
		// given
		InsertFormDTO insertFormDTO = new InsertFormDTO("test", "123", "123");
		given(memberMapper.findMemberById(any())).willReturn(null);

		// when
		Object result = userService.insertUserInfo(insertFormDTO);

		//then
		then(memberMapper).should(times(1)).save(any(), any(), any());
		assertThat(result).isEqualTo("회원가입 성공");
	}

	@DisplayName("회원가입 실패 - 중복 id")
	@Test
	void insertTestFail() {
		// given
		InsertFormDTO insertFormDTO = new InsertFormDTO("test", "123", "123");
		given(memberMapper.findMemberById(any())).willReturn(insertFormDTO);

		//then
		assertThrows(DuplicateKeyException.class, () -> userService.insertUserInfo(insertFormDTO));
	}

	@DisplayName("로그인 성공")
	@Test
	void loginTest() {
		// given
		LoginFormDTO loginFormDTO = new LoginFormDTO("test", "123");
		InsertFormDTO insertFormDTO = new InsertFormDTO("test", "123", "123");

		given(memberMapper.findMemberById(any())).willReturn(insertFormDTO);
		given(memberMapper.findPassword(any())).willReturn("123");

		// when
		Object result = userService.login(loginFormDTO);

		// then
		assertThat(result).isEqualTo("로그인 성공");
	}

	@DisplayName("로그인 실패 - id 존재 x")
	@Test
	void loginTestFail() {
		// given
		LoginFormDTO loginFormDTO = new LoginFormDTO("test", "123");
		given(memberMapper.findMemberById(any())).willReturn(null);

		// then
		assertThrows(NullPointerException.class, () -> userService.login(loginFormDTO));
	}

	@DisplayName("로그인 실패 - 비밀번호 오류")
	@Test
	void loginTestFail2() {
		// given
		LoginFormDTO loginFormDTO = new LoginFormDTO("test", "123");
		InsertFormDTO insertFormDTO = new InsertFormDTO("test", "123", "123");

		given(memberMapper.findMemberById(any())).willReturn(insertFormDTO);
		given(memberMapper.findPassword(any())).willReturn("123123");

		// then
		assertThrows(IllegalArgumentException.class, () -> userService.login(loginFormDTO));
	}

	@DisplayName("회원정보 수정 성공")
	@Test
	void updateTest() {
		// given
		LoginFormDTO loginFormDTO = new LoginFormDTO("test", "123");
		UpdateFormDTO updateFormDTO = new UpdateFormDTO("test", "123", "123");

		// when
		userService.updateUserInfo(loginFormDTO, updateFormDTO);

		// then
		then(memberMapper).should(times(1)).updateInfo(any(), any(), any());
	}

	@DisplayName("회원정보 수정 실패 - id 불일치")
	@Test
	void updateTestFail() {
		// given
		LoginFormDTO loginFormDTO = new LoginFormDTO("test", "123");
		UpdateFormDTO updateFormDTO = new UpdateFormDTO("test2", "123", "123");

		// then
		assertThrows(IllegalArgumentException.class, () -> userService.updateUserInfo(loginFormDTO, updateFormDTO));
	}

	@DisplayName("회원정보 삭제 성공")
	@Test
	void deleteTest() {
		// given
		LoginFormDTO loginFormDTO = new LoginFormDTO("test", "123");
		DeleteFormDTO deleteFormDTO = new DeleteFormDTO("test", "123");

		// when
		userService.deleteUserInfo(loginFormDTO,deleteFormDTO);

		//then
		then(memberMapper).should(only()).deleteUserInfo(any());
	}

	@DisplayName("회원정보 삭제 실패 - id 불일치")
	@Test
	void deleteTestFail() {
		// given
		LoginFormDTO loginFormDTO = new LoginFormDTO("test", "123");
		DeleteFormDTO deleteFormDTO = new DeleteFormDTO("test2", "123");

		//then
		assertThrows(IllegalArgumentException.class, () -> userService.deleteUserInfo(loginFormDTO,deleteFormDTO));
	}
}