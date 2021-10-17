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
import com.flab.foodeats.common.response.ErrorUserCode;
import com.flab.foodeats.common.util.token.TokenUtils;
import com.flab.foodeats.domain.user.Consumer;
import com.flab.foodeats.domain.user.User;
import com.flab.foodeats.infra.user.ConsumerRepository;

@Service
@Transactional
@Qualifier("consumerService")
public class ConsumerServiceImpl implements UserService {


	private final TokenUtils tokenUtils;
	private final ConsumerRepository consumerRepository;

	public ConsumerServiceImpl(TokenUtils tokenUtils, ConsumerRepository consumerRepository) {
		this.tokenUtils = tokenUtils;
		this.consumerRepository = consumerRepository;
	}

	@Override
	public void registerUserInfo(RegisterUserTarget dto) {
		Consumer consuemr = dto.toConsumer();
		if (existsCheckByUserId(consuemr.getUserId()) == true) {
			throw new NullPointerException(ErrorUserCode.ID_EXIST.getMessage());
		}

		consumerRepository.save(consuemr);
	}

	@Override
	public LoginUserResponse login(LoginUserTarget target) {
		Consumer consumerInfo = findByUserId(target.getUserId());
		validateLoginInfo(consumerInfo.getPassword(), target.toEntity().getPassword());

		String token = tokenUtils.createToken(AuthInfo.merchantOf(consumerInfo.getId(), consumerInfo.getUserId()));
		return LoginUserResponse.ofConsumer(consumerInfo, token);
	}

	@Override
	public void modifyUserInfo(ModifyUserTarget target) {
		// 로그인한 유저의 NO와 ID를 가져옴
		// no으로 바꿔야 할듯
		// 한번에 일괄적 변경

		Consumer consumer = target.toConsumer();
		consumerRepository.save(consumer);
	}

	@Override
	public void deleteUserInfo(DeleteUserTarget target) {
		Consumer consumer = findByUserId(target.getUserId());
		validateLoginInfo(consumer.getPassword(), target.getPassword());
		consumerRepository.deleteById(consumer.getId());
	}

	private Consumer findByUserId(String userId) {
		return consumerRepository.findByUserId(userId)
			.orElseThrow(() -> new NullPointerException(ErrorUserCode.ID_NOT_EXIST.getMessage()));
	}

	private boolean existsCheckByUserId(String userId) {
		return consumerRepository.existsByUserId(userId);
	}

	public void validateLoginInfo(String getPasswordInDatabase, String getPasswordInEnteredInfo) {
		if (!getPasswordInDatabase.equals(getPasswordInEnteredInfo)) {
			throw new IllegalArgumentException(ErrorUserCode.PASSWORD_NOT_MATCH.getMessage());
		}
	}
}

