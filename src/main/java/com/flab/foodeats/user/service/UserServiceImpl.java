package com.flab.foodeats.user.service;

import java.util.List;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.flab.foodeats.user.mapper.MemberMapper;
import com.flab.foodeats.user.model.DeleteFormDTO;
import com.flab.foodeats.user.model.InsertFormDTO;
import com.flab.foodeats.user.model.LoginFormDTO;
import com.flab.foodeats.user.model.UpdateFormDTO;

@Service
public class UserServiceImpl implements UserService {

	private MemberMapper memberMapper;

	public UserServiceImpl(MemberMapper memberMapper) {
		this.memberMapper = memberMapper;
	}

	@Override
	public Object insertUserInfo(InsertFormDTO insertFormDTO) {
		if (memberMapper.findMemberById(insertFormDTO.getId()) != null) {
			throw new DuplicateKeyException("이미 가입된 id 입니다");
		}
		memberMapper.save(insertFormDTO.getId(), insertFormDTO.getPassword(), insertFormDTO.getName());
		return "회원가입 성공";
	}

	@Override
	public Object login(LoginFormDTO loginFormDTO) {
		if (memberMapper.findMemberById(loginFormDTO.getId()) == null) {
			throw new NullPointerException("해당 id가 존재하지 않습니다.");
		} else if ((memberMapper.findPassword(loginFormDTO.getId())).equals(loginFormDTO.getPassword())) {
			return "로그인 성공";
		}
		throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
	}

	@Override
	public Object updateUserInfo(LoginFormDTO loginFormDTO, UpdateFormDTO updateFormDTO) {
		if ((loginFormDTO.getId()).equals(updateFormDTO.getId())) {
			memberMapper.updateInfo(updateFormDTO.getId(), updateFormDTO.getPassword(), updateFormDTO.getName());
			return "정보수정 성공";
		}
		throw new IllegalArgumentException("id 오류");
	}

	@Override
	public Object deleteUserInfo(LoginFormDTO loginFormDTO, DeleteFormDTO deleteFormDTO) {
		if ((loginFormDTO.getId()).equals(deleteFormDTO.getId())) {
			memberMapper.deleteUserInfo(deleteFormDTO.getId());
			return "회원삭제 성공";
		}
		throw new IllegalArgumentException("id 오류");
	}

	@Override
	public List<InsertFormDTO> findAllUser() {
		return memberMapper.findAll();
	}
}
