package com.flab.foodeats.user.mapper;

import com.flab.foodeats.user.model.InsertFormDTO;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MemberMapper {

	// 회원가입
	void save(@Param("id") String id, @Param("password") String password, @Param("name") String name);

	// 로그인 (=비밀번호 찾기)
	String findPassword(String id);

	// 회원정보 수정
	int updateInfo(@Param("id") String id, @Param("password") String password, @Param("name") String name);

	// 회원 탈퇴
	int deleteUserInfo(@Param("id") String id);

	// 모든회원 조회
	List<InsertFormDTO> findAll();

	// 단일회원 조회
	InsertFormDTO findMemberById(String id);

}
