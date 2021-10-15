package com.flab.foodeats.application.user.adapter;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.flab.foodeats.application.user.DeleteUserTarget;
import com.flab.foodeats.application.user.LoginUserResponse;
import com.flab.foodeats.application.user.LoginUserTarget;
import com.flab.foodeats.application.user.ModifyUserTarget;
import com.flab.foodeats.application.user.RegisterUserTarget;
import com.flab.foodeats.application.user.port.UserService;
import com.flab.foodeats.common.auth.AuthInfo;
import com.flab.foodeats.common.util.token.TokenUtils;
import com.flab.foodeats.domain.user.User;
import com.flab.foodeats.infra.user.UserMapper;

@Service
@Transactional
@Qualifier("merchantService")
public class MerchantServiceImpl implements UserService {

	private UserMapper userMapper;
	private ErrorCheck errorCheck;
	private final TokenUtils tokenUtils;

	public MerchantServiceImpl(UserMapper userMapper, ErrorCheck errorCheck,
		TokenUtils tokenUtils) {
		this.userMapper = userMapper;
		this.errorCheck = errorCheck;
		this.tokenUtils = tokenUtils;
	}

	@Override
	public void registerUserInfo(RegisterUserTarget dto) {
		User user = dto.toEntity();
		errorCheck.alreadyExistUserInfo(getUserInfo(user.getUserId()));
		userMapper.saveMerchant(user);
	}

	@Override
	public LoginUserResponse login(LoginUserTarget target) {
		User merchantInfo = getUserInfo(target.getUserId());
		errorCheck.notExistUserInfo(merchantInfo);
		errorCheck.validateLoginInfo(merchantInfo.getPassword(), target.toEntity().getPassword());
		String token = tokenUtils.createToken(AuthInfo.merchantOf(merchantInfo.getId(), merchantInfo.getUserId()));
		return LoginUserResponse.of(merchantInfo, token);
	}

	@Override
	public void modifyUserInfo(ModifyUserTarget target) {
		User user = target.toEntity();
		userMapper.modifyMerchantById(user);
	}

	@Override
	public void deleteUserInfo(DeleteUserTarget target) {
		User user = getUserInfo(target.getUserId());
		errorCheck.validateLoginInfo(user.getPassword(), target.getPassword());
		userMapper.deleteMerchantById(user.getUserId());
	}

	private User getUserInfo(String userId) {
		return userMapper.findMerchantByUserId(userId);
	}
}
