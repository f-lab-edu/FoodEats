package com.flab.foodeats.user.mapper;

import com.flab.foodeats.user.model.InsertFormDTO;
import com.flab.foodeats.user.model.UpdateFormDTO;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper {

	// 회원가입 - 소비자
	void registerConsumerUser(@Param("insertFormDTO") InsertFormDTO insertFormDTO);

	// 로그인 - 소비자
	String findConsumerUserPassword(String id);

	// 회원 수정 - 소비자
	void updateConsumerUserInfo(@Param("id") String id, @Param("updateFormDTO") UpdateFormDTO updateFormDTO);

	// 회원 탈퇴 - 소비자
	void deleteConsumerUserInfo(String id);

	// 단일 회원 조회 - 소비자
	InsertFormDTO findConsumerUserById(String id);
}
