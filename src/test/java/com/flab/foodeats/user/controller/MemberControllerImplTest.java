package com.flab.foodeats.user.controller;

import static org.junit.jupiter.api.Assertions.*;
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
import com.flab.foodeats.user.model.DeleteFormDTO;
import com.flab.foodeats.user.model.InsertFormDTO;
import com.flab.foodeats.user.model.LoginFormDTO;
import com.flab.foodeats.user.model.UpdateFormDTO;
import com.flab.foodeats.user.util.SessionConst;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest
@Transactional
class MemberControllerImplTest {

	@Autowired
	protected MockMvc mockMvc;
	@Autowired
	protected ObjectMapper objectMapper;

	protected MockHttpSession session;

	@BeforeEach
	public void setUp() {
		LoginFormDTO loginForm = new LoginFormDTO("test","test!");

		session = new MockHttpSession();
		session.setAttribute(SessionConst.LOGIN, loginForm);
	}

	@AfterEach
	public void clean(){
		session.clearAttributes();
	}

	@Test
	@DisplayName("회원가입 테스트")
	public void test() throws Exception {
		InsertFormDTO member = new InsertFormDTO("newMember1","123","123");
		String json = objectMapper.writeValueAsString(member);

		mockMvc.perform(post("/user/insert")
			.contentType(MediaType.APPLICATION_JSON)
			.content(json))
			.andDo(print())
			.andExpect(status().isCreated());
	}

	@Test
	@DisplayName("회원가입 실패 - ID 중복")
	public void insertFail() throws Exception {
		InsertFormDTO member = new InsertFormDTO("test","123","123");
		String json = objectMapper.writeValueAsString(member);

		mockMvc.perform(post("/user/insert")
			.contentType(MediaType.APPLICATION_JSON)
			.content(json))
			.andDo(print())
			.andExpect(status().isBadRequest())
			.andExpect(jsonPath("message").value("이미 가입된 id 입니다"));
	}

	@Test
	@DisplayName("로그인 테스트")
	void login() throws Exception {
		LoginFormDTO loginForm = new LoginFormDTO("test","test!");
		String json = objectMapper.writeValueAsString(loginForm);

		mockMvc.perform(post("/user/login")
			.contentType(MediaType.APPLICATION_JSON)
			.content(json))
			.andDo(print())
			.andExpect(status().isOk());
	}

	@Test
	@DisplayName("로그인 테스트 - 비밀번호 오류")
	void loginFail() throws Exception {
		LoginFormDTO loginForm = new LoginFormDTO("test","test!1");
		String json = objectMapper.writeValueAsString(loginForm);

		// then
		mockMvc.perform(post("/user/login")
			.contentType(MediaType.APPLICATION_JSON)
			.content(json))
			.andDo(print())
			.andExpect(status().isBadRequest())
			.andExpect(jsonPath("message").value("비밀번호가 일치하지 않습니다."));
	}

	@Test
	@DisplayName("로그인 테스트 - id 존재 x")
	void loginFail2() throws Exception {
		LoginFormDTO loginForm = new LoginFormDTO("testx","test!");
		String json = objectMapper.writeValueAsString(loginForm);

		mockMvc.perform(post("/user/login")
			.contentType(MediaType.APPLICATION_JSON)
			.content(json))
			.andDo(print())
			.andExpect(status().isBadRequest())
			.andExpect(jsonPath("message").value("해당 id가 존재하지 않습니다."));
	}

	@Test
	@DisplayName("회원정보 수정 성공")
	void updateTest() throws Exception {
		UpdateFormDTO updateFormDTO = new UpdateFormDTO("test","123","123");
		String json = objectMapper.writeValueAsString(updateFormDTO);

		mockMvc.perform(put("/user/update")
			.session(session)
			.contentType(MediaType.APPLICATION_JSON)
			.content(json))
			.andDo(print())
			.andExpect(status().isOk());
	}

	@Test
	@DisplayName("회원정보 수정 실패 - id 불일치")
	void updateTestFail() throws Exception {
		UpdateFormDTO updateFormDTO = new UpdateFormDTO("test2","123","123");
		String json = objectMapper.writeValueAsString(updateFormDTO);

		mockMvc.perform(put("/user/update")
			.session(session)
			.contentType(MediaType.APPLICATION_JSON)
			.content(json))
			.andDo(print())
			.andExpect(status().isBadRequest());
	}

	@Test
	@DisplayName("회원정보 삭제 성공")
	void deleteTest() throws Exception {
		DeleteFormDTO deleteFormDTO = new DeleteFormDTO("test","123");
		String json = objectMapper.writeValueAsString(deleteFormDTO);

		mockMvc.perform(delete("/user/delete")
			.session(session)
			.contentType(MediaType.APPLICATION_JSON)
			.content(json))
			.andDo(print())
			.andExpect(status().isOk());
	}

	@Test
	@DisplayName("회원정보 삭제 실패 - id 불일치")
	void deleteTestFail() throws Exception {
		DeleteFormDTO deleteFormDTO = new DeleteFormDTO("test2","123");
		String json = objectMapper.writeValueAsString(deleteFormDTO);

		mockMvc.perform(delete("/user/delete")
			.session(session)
			.contentType(MediaType.APPLICATION_JSON)
			.content(json))
			.andDo(print())
			.andExpect(status().isBadRequest());
	}
}