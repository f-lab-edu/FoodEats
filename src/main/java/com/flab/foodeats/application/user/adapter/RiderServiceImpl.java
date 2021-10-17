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
import com.flab.foodeats.domain.user.Merchant;
import com.flab.foodeats.domain.user.Rider;
import com.flab.foodeats.domain.user.User;
import com.flab.foodeats.infra.user.MerchantRepository;
import com.flab.foodeats.infra.user.RiderRepository;
import com.flab.foodeats.infra.user.UserMapper;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@Qualifier("riderService")
@RequiredArgsConstructor
public class RiderServiceImpl implements UserService {

	private final RiderRepository riderRepository;
	private final TokenUtils tokenUtils;


	@Override
	public void registerUserInfo(RegisterUserTarget dto) {
		Rider rider = dto.toRider();
		if (existsCheckByUserId(rider.getUserId()) == true) {
			throw new NullPointerException(ErrorUserCode.ID_EXIST.getMessage());
		}

		riderRepository.save(rider);
	}

	@Override
	public LoginUserResponse login(LoginUserTarget target) {
		Rider riderInfo = findByUserId(target.getUserId());
		validateLoginInfo(riderInfo.getPassword(), target.toEntity().getPassword());

		String token = tokenUtils.createToken(AuthInfo.merchantOf(riderInfo.getId(), riderInfo.getUserId()));
		return LoginUserResponse.ofRider(riderInfo, token);
	}

	@Override
	public void modifyUserInfo(ModifyUserTarget target) {
		Rider rider = target.toRider();
		riderRepository.save(rider);
	}

	@Override
	public void deleteUserInfo(DeleteUserTarget target) {
		Rider rider = findByUserId(target.getUserId());
		validateLoginInfo(rider.getPassword(), target.getPassword());
		riderRepository.deleteById(rider.getId());
	}

	private Rider findByUserId(String userId) {
		return riderRepository.findByUserId(userId)
			.orElseThrow(() -> new NullPointerException(ErrorUserCode.ID_NOT_EXIST.getMessage()));
	}

	private boolean existsCheckByUserId(String userId) {
		return riderRepository.existsByUserId(userId);
	}

	public void validateLoginInfo(String getPasswordInDatabase, String getPasswordInEnteredInfo) {
		if (!getPasswordInDatabase.equals(getPasswordInEnteredInfo)) {
			throw new IllegalArgumentException(ErrorUserCode.PASSWORD_NOT_MATCH.getMessage());
		}
	}

}
