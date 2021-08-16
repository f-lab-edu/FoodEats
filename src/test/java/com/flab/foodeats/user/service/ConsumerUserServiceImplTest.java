
package com.flab.foodeats.user.service;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DuplicateKeyException;

import com.flab.foodeats.user.mapper.UserMapper;
import com.flab.foodeats.user.model.InsertFormDTO;
import com.flab.foodeats.user.model.LoginFormDTO;
import com.flab.foodeats.user.model.UpdateFormDTO;
import com.flab.foodeats.user.user.consumer.ConsumerUserServiceImpl;

@ExtendWith(MockitoExtension.class)
class ConsumerUserServiceImplTest {

	@InjectMocks
	ConsumerUserServiceImpl consumerUserService;

	@Mock
	InsertErrorCheck insertErrorCheck;
	@Mock
	LoginErrorCheck loginErrorCheck;;

	@Mock
	UserMapper userMapper;


	@DisplayName("회원가입 성공")
	@Test
	void registerConsumerUserInfoSuccessTest() {
		// given
		InsertFormDTO insertFormDTO = new InsertFormDTO("test", "123", "123");
		given(userMapper.findConsumerUserById(any())).willReturn(null);

		// when
		consumerUserService.registerConsumerUser(insertFormDTO);

		//then
		then(userMapper).should(times(1)).registerConsumerUser(any());
		//assertThat(result).isEqualTo("회원가입 성공");
	}

	/**
	 * todo
	 */
	@DisplayName("회원가입 실패 - 중복 id")
	@Test
	void registerConsumerUserInfoButAlreadyExistTest() {
		// given
		InsertFormDTO insertFormDTO = new InsertFormDTO("dudtn", "711", "dDdd11d");
		given(userMapper.findConsumerUserById(any())).willReturn(insertFormDTO);

		//then
		assertThrows(DuplicateKeyException.class, () -> consumerUserService.registerConsumerUser(insertFormDTO));
	}

	@DisplayName("로그인 성공")
	@Test
	void consumerUserLoginSuccessTest() {
		// given
		LoginFormDTO loginFormDTO = new LoginFormDTO("test", "123");
		given(userMapper.findConsumerUserPassword(any())).willReturn("123");

		// when
		Object result = consumerUserService.loginConsumerUser(loginFormDTO).getMessage();

		// then
		assertThat(result).isEqualTo("Login Success");
	}

	@DisplayName("로그인 실패 - id 존재 x")
	@Test
	void consumerUserLoginNotExistInfoTest() {
		// given
		LoginFormDTO loginFormDTO = new LoginFormDTO("test", "123");
		given(userMapper.findConsumerUserPassword(any())).willReturn(null);

		// then
		assertThrows(NullPointerException.class, () -> consumerUserService.loginConsumerUser(loginFormDTO));
	}

	/**
	 * todo
	 */
	@DisplayName("로그인 실패 - 비밀번호 오류")
	@Test
	void consumerUserLoginNotValidTest() {
		// given
		LoginFormDTO loginFormDTO = new LoginFormDTO("dudtn", "dd");
		given(userMapper.findConsumerUserPassword(any())).willReturn("dd");


		// then
		assertThrows(IllegalArgumentException.class, () ->  consumerUserService.loginConsumerUser(loginFormDTO));
	}


	@DisplayName("회원정보 수정 성공")
	@Test
	void updateConsumerInfoSuccessTest() {
		// given
		LoginFormDTO loginFormDTO = new LoginFormDTO("test", "123");
		UpdateFormDTO updateFormDTO = new UpdateFormDTO( "123", "123");

		// when
		consumerUserService.updateConsumerUser(updateFormDTO, loginFormDTO.getId());

		// then
		then(userMapper).should(times(1)).updateConsumerUserInfo(any(), any());
	}


	@DisplayName("회원정보 삭제 성공")
	@Test
	void deleteConsuerInfoSuccessTest() {
		// given
		LoginFormDTO loginFormDTO = new LoginFormDTO("test", "123");

		// when
		consumerUserService.deleteConsumerUser(loginFormDTO.getId());

		//then
		then(userMapper).should(only()).deleteConsumerUserInfo(any());
	}

}

