package com.flab.foodeats.application.menu.adapter;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.flab.foodeats.api.menu.MenuCode;
import com.flab.foodeats.api.menu.OptionRequest;
import com.flab.foodeats.application.menu.OptionTarget;
import com.flab.foodeats.application.menu.port.MenuOptionService;
import org.springframework.transaction.annotation.Transactional;

import com.flab.foodeats.common.error.exception.UnauthorizedException;
import com.flab.foodeats.domain.menu.MenuOption;
import com.flab.foodeats.infra.menu.MenuOptionMapper;
import com.flab.foodeats.infra.shop.ShopMapper;
import com.flab.foodeats.infra.user.UserMapper;

@Service
@Transactional
public class MenuOptionServiceImpl implements MenuOptionService {

	private final MenuOptionMapper menuOptionMapper;
	private final UserMapper userMapper;

	public MenuOptionServiceImpl(MenuOptionMapper menuOptionMapper, UserMapper userMapper) {
		this.menuOptionMapper = menuOptionMapper;
		this.userMapper = userMapper;
	}

	@Override
	public void registerMenuOption(Long shopId, Long menuId, List<OptionRequest> optionRequests, String userId) {
		String requestedOwnerId = userMapper.findMerchantByShopId(shopId).getUserId();
		checkIsAuthorizedUser(requestedOwnerId, userId);

		List<MenuOption> menuOptions = menuOptionMapper.searchMenuOption(menuId);
		checkIsExistOption(optionRequests, menuOptions);

		optionRequests.stream()
			.forEach(optionRequest -> menuOptionMapper.registerOption(optionRequest.toEntity(menuId)));
	}

	@Override
	public void updateMenuOption(Long shopId, OptionTarget optionTarget, String userId) {
		String requestedOwnerId = userMapper.findMerchantByShopId(shopId).getUserId();
		checkIsAuthorizedUser(requestedOwnerId, userId);
		
		MenuOption option = menuOptionMapper.searchMenuOptionByOptionId(optionTarget.getMenuOptionId());
		option.updateOption(optionTarget.getMenuOptionName(), optionTarget.getMenuOptionPrice());
		menuOptionMapper.updateMenuOption(option);
	}

	@Override
	public void deleteMenuOption(Long shopId, Long menuOptionId, String userId) {
		String requestedOwnerId = userMapper.findMerchantByShopId(shopId).getUserId();
		checkIsAuthorizedUser(requestedOwnerId, userId);
		menuOptionMapper.deleteMenuOption(menuOptionId);
	}

	private void checkIsAuthorizedUser(String requestedId, String userId) {
		if (!requestedId.equals(userId)) {
			throw new UnauthorizedException();
		}
	}

	private void checkIsExistOption(List<OptionRequest> optionRequests, List<MenuOption> menuOptions) {
		for (OptionRequest optionRequest : optionRequests) {
			Optional<MenuOption> existOption = menuOptions.stream()
				.filter(m -> m.getMenuOptionName().equals(optionRequest.getMenuOptionName()))
				.findAny();
			if (existOption.isPresent()) {
				throw new IllegalArgumentException(MenuCode.ALEADY_EXIST_MENU.getMessage());
			}
		}
	}
}
