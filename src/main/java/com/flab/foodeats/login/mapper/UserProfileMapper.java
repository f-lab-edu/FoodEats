package com.flab.foodeats.login.mapper;

import com.flab.foodeats.login.model.UserProfile;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserProfileMapper {

    // ID를 이용한 조회 / 로그인
    @Select("SELECT * FROM UserProfile WHERE id=#{id}")
    UserProfile getUserProfile(@Param("id") String id);

    // 전체 조회
    @Select("SELECT * FROM UserProfile")
    List<UserProfile> getUserProfileList();

    // 회원가입
    @Insert("INSERT INTO UserProfile VALUES(#{id}, #{name}, #{phone}, #{address})")
    int insertUserProfile(@Param("id") String id, @Param("name") String name, @Param("phone") String phone, @Param("address") String address);

    // 회원수정
    @Update("UPDATE UserProfile SET name=#{name}, phone=#{phone}, address=#{address} WHERE id=#{id}")
    int updateUserProfile(@Param("id") String id, @Param("name") String name, @Param("phone") String phone, @Param("address") String address);

    // 회원삭제
    @Delete("DELETE FROM UserProfile WHERE id=#{id}")
    int deleteUserProfile(@Param("id") String id);
}
