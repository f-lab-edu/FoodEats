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
@Qualifier("merchantService")
public class MerchantServiceImpl implements UserService {

	private UserMapper userMapper;
	private ErrorCheck errorCheck;

	public MerchantServiceImpl(UserMapper userMapper, ErrorCheck errorCheck) {
		this.userMapper = userMapper;
		this.errorCheck = errorCheck;
	}

	@Override
	public void registerUserInfo(RegisterUserTarget registerUserTarget) {
		errorCheck.alreadyExistUserInfo(userMapper.findMerchantInfoById(registerUserTarget.getUserId()));
		userMapper.registerMerchant(registerUserTarget);
	}

	@Override
	public void loginUserInfo(LoginUserTarget loginUserTarget) {
		User merchantInfo = userMapper.findMerchantInfoById(loginUserTarget.getUserId());
		errorCheck.notExistUserInfo(merchantInfo);
		errorCheck.validateLoginInfo(merchantInfo.getPassword(), loginUserTarget.getPassword());
	}

	@Override
	public void modifyUserInfo(ModifyUserTarget modifyUserTarget) {
		userMapper.modifyMerchantInfoById(modifyUserTarget);
	}
}
