package com.flab.foodeats.application.user.adapter;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.flab.foodeats.application.user.DeleteUserTarget;
import com.flab.foodeats.application.user.LoginUserTarget;
import com.flab.foodeats.application.user.ModifyUserTarget;
import com.flab.foodeats.application.user.RegisterUserTarget;
import com.flab.foodeats.application.user.port.UserService;
import com.flab.foodeats.domain.user.User;
import com.flab.foodeats.infra.user.UserMapper;

@Service
@Transactional
@Qualifier("riderService")
public class RiderServiceImpl implements UserService {

	private UserMapper userMapper;
	private ErrorCheck errorCheck;

	public RiderServiceImpl(UserMapper userMapper, ErrorCheck errorCheck) {
		this.userMapper = userMapper;
		this.errorCheck = errorCheck;
	}

	@Override
	public void registerUserInfo(RegisterUserTarget dto) {
		User user = dto.toEntity();
		errorCheck.alreadyExistUserInfo(getUserInfo(user.getUserId()));
		userMapper.saveRider(user);
	}

	@Override
	public long login(LoginUserTarget target) {
		User riderInfo = getUserInfo(target.getUserId());
		errorCheck.notExistUserInfo(riderInfo);
		errorCheck.validateLoginInfo(riderInfo.getPassword(), target.toEntity().getPassword());
		return riderInfo.getId();
	}

	@Override
	public void modifyUserInfo(ModifyUserTarget target) {
		User user = target.toEntity();
		userMapper.modifyRiderById(user);
	}

	@Override
	public void deleteUserInfo(DeleteUserTarget target) {
		User user = getUserInfo(target.getUserId());
		errorCheck.validateLoginInfo(user.getPassword(),target.getPassword());
		userMapper.deleteRiderById(user.getUserId());
	}

	private User getUserInfo(String userId){
		return userMapper.findRiderByUserId(userId);
	}

}
