package com.flab.foodeats.user.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.flab.foodeats.user.interceptor.auth.Auth;
import com.flab.foodeats.user.model.InsertFormDTO;
import com.flab.foodeats.user.model.LoginFormDTO;
import com.flab.foodeats.user.model.UpdateFormDTO;
import com.flab.foodeats.user.model.code.ErrorUserCode;
import com.flab.foodeats.user.model.code.SuccessUserCode;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest
@Transactional
class ConsumerUserControllerImplTest {

	@Autowired
	protected MockMvc mockMvc;
	@Autowired
	protected ObjectMapper objectMapper;

	protected MockHttpSession session;

	@BeforeEach
	public void setUp() {
		LoginFormDTO loginFormDTO = new LoginFormDTO("dudntn","711");
		session = new MockHttpSession();
		session.setAttribute(Auth.CUNSUMER_KEY, loginFormDTO.getId());
	}

	@AfterEach
	public void clean(){
		session.clearAttributes();
	}

	@Test
	@DisplayName("회원가입 테스트")
	public void consumerRegisterTest() throws Exception {
		InsertFormDTO member = new InsertFormDTO("newMember1","123","123");
		String json = objectMapper.writeValueAsString(member);

		mockMvc.perform(post("/user/consumer/register")
			.contentType(MediaType.APPLICATION_JSON)
			.content(json))
			.andDo(print())
			.andExpect(jsonPath("message").value(SuccessUserCode.USER_INSERT_SUCCESS.getMessage()));
	}

	@Test
	@DisplayName("회원가입 실패 - ID 중복")
	public void consumerRegisterAlreadyExistTest() throws Exception {
		InsertFormDTO member = new InsertFormDTO("dudtn","711","123");
		String json = objectMapper.writeValueAsString(member);

		mockMvc.perform(post("/user/consumer/register")
			.contentType(MediaType.APPLICATION_JSON)
			.content(json))
			.andDo(print())
			.andExpect(status().isBadRequest())
			.andExpect(jsonPath("message").value(ErrorUserCode.ID_EXIST.getMessage()));
	}

	@Test
	@DisplayName("로그인 테스트")
	void consumerLoginSuccessTest() throws Exception {
		LoginFormDTO loginForm = new LoginFormDTO("dudtn","711");
		String json = objectMapper.writeValueAsString(loginForm);

		mockMvc.perform(post("/user/consumer/login")
			.contentType(MediaType.APPLICATION_JSON)
			.content(json))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(jsonPath("message").value(SuccessUserCode.USER_LOGIN_SUCCESS.getMessage()));
	}

	@Test
	@DisplayName("로그인 테스트 - 비밀번호 오류")
	void consumerLoginFailTest() throws Exception {
		LoginFormDTO loginForm = new LoginFormDTO("dudtn","7");
		String json = objectMapper.writeValueAsString(loginForm);

		mockMvc.perform(post("/user/consumer/login")
			.contentType(MediaType.APPLICATION_JSON)
			.content(json))
			.andDo(print())
			.andExpect(status().isBadRequest())
			.andExpect(jsonPath("message").value(ErrorUserCode.PASSWORD_NOT_MATCH.getMessage()));
	}

	@Test
	@DisplayName("로그인 테스트 - id 존재 x")
	void consumerLoginIDIsNotExistTest() throws Exception {
		LoginFormDTO loginForm = new LoginFormDTO("testx","test!");
		String json = objectMapper.writeValueAsString(loginForm);

		 mockMvc.perform(post("/user/consumer/login")
			.contentType(MediaType.APPLICATION_JSON)
			.content(json))
			.andDo(print())
			.andExpect(status().isBadRequest())
			.andExpect(jsonPath("message").value(ErrorUserCode.ID_NOT_EXIST.getMessage()));
	}

	@Test
	@DisplayName("회원정보 수정 성공")
	void UpdateConsumerInfoTest() throws Exception {
		UpdateFormDTO updateFormDTO = new UpdateFormDTO("123","123");
		String json = objectMapper.writeValueAsString(updateFormDTO);

		mockMvc.perform(put("/user/consumer/update")
			.session(session)
			.contentType(MediaType.APPLICATION_JSON)
			.content(json))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(jsonPath("message").value(SuccessUserCode.USER_UPDATE_SUCCESS.getMessage()));
	}


	@Test
	@DisplayName("회원정보 삭제 성공")
	void deleteConsumerInfoTest() throws Exception {
		mockMvc.perform(delete("/user/consumer/delete")
			.session(session)
			.contentType(MediaType.APPLICATION_JSON))
			.andDo(print())
			.andExpect(status().isOk());
	}
}
