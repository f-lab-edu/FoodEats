package com.flab.foodeats.application.menu.adapter;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.flab.foodeats.api.menu.OptionRequest;
import com.flab.foodeats.application.menu.port.MenuOptionService;
import com.flab.foodeats.domain.menu.MenuOption;
import com.flab.foodeats.infra.menu.MenuOptionMapper;
import com.flab.foodeats.infra.shop.ShopMapper;

@Service
public class MenuOptionServiceImpl implements MenuOptionService {

	private final MenuOptionMapper menuOptionMapper;
	private final UserMapper userMapper;

	public MenuOptionServiceImpl(MenuOptionMapper menuOptionMapper, UserMapper userMapper) {
		this.menuOptionMapper = menuOptionMapper;
		this.userMapper = userMapper;
	}

	@Override
	public void registerMenuOption(Long shopId, int menuId, List<OptionRequest> optionRequests, String userId) {
		String requestedOwnerId = userMapper.findMerchantByShopId(shopId).getUserId();
		checkIsAuthorizedUser(requestedOwnerId, authInfo.getUserId());

		List<MenuOption> menuOptions = menuOptionMapper.searchMenuOption(menuId);
		checkIsExistOption(optionRequests, menuOptions);

		optionRequests.stream()
			.forEach(optionRequest -> menuOptionMapper.registerOption(optionRequest.toEntity(menuId)));
	}

	@Override
	public void updateMenuOption(Long shopId, OptionTarget optionTarget, AuthInfo authInfo) {
		String requestedOwnerId = userMapper.findMerchantByShopId(shopId).getUserId();
		checkIsAuthorizedUser(requestedOwnerId, authInfo.getUserId());
		
		MenuOption option = menuOptionMapper.searchMenuOptionByOptionId(optionTarget.getMenuOptionId());
		option.updateOption(optionTarget.getMenuOptionName(), optionTarget.getMenuOptionPrice());
		menuOptionMapper.updateMenuOption(option);
	}

	@Override
	public void deleteMenuOption(Long shopId, int menuOptionId, String userId) {
		checkIsAuthorizedUser(shopId, userId);
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
