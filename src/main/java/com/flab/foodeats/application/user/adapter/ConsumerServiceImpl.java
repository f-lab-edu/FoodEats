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
@Qualifier("consumerService")
public class ConsumerServiceImpl implements UserService {

	private final UserMapper userMapper;
	private final ErrorCheck errorCheck;
	private final TokenUtils tokenUtils;

	public ConsumerServiceImpl(UserMapper userMapper, ErrorCheck errorCheck,
		TokenUtils tokenUtils) {
		this.userMapper = userMapper;
		this.errorCheck = errorCheck;
		this.tokenUtils = tokenUtils;
	}

	@Override
	public void registerUserInfo(RegisterUserTarget dto) {
		User user = dto.toEntity();
		errorCheck.alreadyExistUserInfo(getUserInfo(user.getUserId()));
		userMapper.saveConsumer(user);
	}

	@Override
	public LoginUserResponse login(LoginUserTarget target) {
		User consumerInfo = getUserInfo(target.getUserId());
		errorCheck.notExistUserInfo(consumerInfo);
		errorCheck.validateLoginInfo(consumerInfo.getPassword(), target.toEntity().getPassword());
		String token = tokenUtils.createToken(AuthInfo.merchantOf(consumerInfo.getId(), consumerInfo.getUserId()));
		return LoginUserResponse.of(consumerInfo, token);
	}

	@Override
	public void modifyUserInfo(ModifyUserTarget target) {
		User user = target.toEntity();
		userMapper.modifyConsumerById(user);
	}

	@Override
	public void deleteUserInfo(DeleteUserTarget target) {
		User user = getUserInfo(target.getUserId());
		errorCheck.validateLoginInfo(user.getPassword(), target.getPassword());
		userMapper.deleteConsumerById(user.getUserId());
	}

	private User getUserInfo(String userId) {
		return userMapper.findConsumerByUserId(userId);
	}
}

