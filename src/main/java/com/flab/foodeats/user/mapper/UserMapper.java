package com.flab.foodeats.user.mapper;

import com.flab.foodeats.user.model.InsertFormDTO;
import com.flab.foodeats.user.model.UpdateFormDTO;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserMapper {

	// 회원가입
	void save(@Param("insertFormDTO") InsertFormDTO insertFormDTO);

	// 로그인
	String findPassword(String id);

	// 모든 회원 조회
	List<InsertFormDTO> findAll();

	// 단일 회원 조회
	InsertFormDTO findMemberById(String id);

	// 회원 수정
	void updateInfo(@Param("id") String id, @Param("updateFormDTO") UpdateFormDTO updateFormDTO);

	// 회원 탈퇴
	void deleteUserInfo(String id);
}
