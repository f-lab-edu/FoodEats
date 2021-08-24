package com.flab.foodeats.application.user.adapter;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	public void registerUserInfo(RegisterUserTarget registerUserTarget) {
		errorCheck.alreadyExistUserInfo(userMapper.findRiderInfoById(registerUserTarget.getUserId()));
		userMapper.registerRider(registerUserTarget);
	}

	@Override
	public void loginUserInfo(LoginUserTarget loginUserTarget) {
		User consumerInfo = userMapper.findRiderInfoById(loginUserTarget.getUserId());
		errorCheck.notExistUserInfo(consumerInfo);
		errorCheck.validateLoginInfo(consumerInfo.getPassword(), loginUserTarget.getPassword());
	}

	@Override
	public void modifyUserInfo(ModifyUserTarget modifyUserTarget) {
		userMapper.modifyRiderInfoById(modifyUserTarget);
	}
}
