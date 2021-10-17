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

import com.flab.foodeats.domain.user.User;
import com.flab.foodeats.infra.user.MerchantRepository;
import com.flab.foodeats.infra.user.UserMapper;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@Qualifier("merchantService")
@RequiredArgsConstructor
public class MerchantServiceImpl implements UserService {


	private final MerchantRepository merchantRepository;
	private final TokenUtils tokenUtils;

	@Override
	public void registerUserInfo(RegisterUserTarget dto) {
		Merchant merchant = dto.toMerchant();
		if (existsCheckByUserId(merchant.getUserId()) == true) {
			throw new NullPointerException(ErrorUserCode.ID_EXIST.getMessage());
		}

		merchantRepository.save(merchant);
	}

	@Override
	public LoginUserResponse login(LoginUserTarget target) {

		Merchant merchantInfo = findByUserId(target.getUserId());
		validateLoginInfo(merchantInfo.getPassword(), target.toEntity().getPassword());

		String token = tokenUtils.createToken(AuthInfo.merchantOf(merchantInfo.getId(), merchantInfo.getUserId()));
		return LoginUserResponse.ofMerchant(merchantInfo, token);

	}

	@Override
	public void modifyUserInfo(ModifyUserTarget target) {
		Merchant merchant = target.toMerchant();
		merchantRepository.save(merchant);
	}

	@Override
	public void deleteUserInfo(DeleteUserTarget target) {
		Merchant merchant = findByUserId(target.getUserId());
		validateLoginInfo(merchant.getPassword(), target.getPassword());
		merchantRepository.deleteById(merchant.getId());
	}

	private Merchant findByUserId(String userId) {
		return merchantRepository.findByUserId(userId)
			.orElseThrow(() -> new NullPointerException(ErrorUserCode.ID_NOT_EXIST.getMessage()));
	}

	private boolean existsCheckByUserId(String userId) {
		return merchantRepository.existsByUserId(userId);
	}

	public void validateLoginInfo(String getPasswordInDatabase, String getPasswordInEnteredInfo) {
		if (!getPasswordInDatabase.equals(getPasswordInEnteredInfo)) {
			throw new IllegalArgumentException(ErrorUserCode.PASSWORD_NOT_MATCH.getMessage());
		}

	}
}
