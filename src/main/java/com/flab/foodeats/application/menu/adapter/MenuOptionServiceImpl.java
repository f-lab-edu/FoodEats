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
	private final ShopMapper shopMapper;

	public MenuOptionServiceImpl(MenuOptionMapper menuOptionMapper, ShopMapper shopMapper) {
		this.menuOptionMapper = menuOptionMapper;
		this.shopMapper = shopMapper;
	}

	@Override
	public void registerMenuOption(int shopId, int menuId, List<OptionRequest> optionRequests, String userId) {
		checkIsAuthorizedUser(shopId, userId);

		List<MenuOption> menuOptions = menuOptionMapper.searchMenuOption(menuId);
		checkIsExistOption(optionRequests, menuOptions);

		optionRequests.stream()
			.forEach(optionRequest -> menuOptionMapper.registerOption(optionRequest.toEntity(menuId)));
	}

	@Override
	public void updateMenuOption(int shopId, int menuOptionId, List<OptionRequest> optionRequests, String userId) {
		checkIsAuthorizedUser(shopId, userId);
		MenuOption option = menuOptionMapper.searchMenuOptionByOptionId(menuOptionId);
		for (OptionRequest optionRequest : optionRequests) {
			option.updateOption(optionRequest.getMenuOptionName(),optionRequest.getMenuOptionPrice());
			menuOptionMapper.updateMenuOption(option);
		}
	}

	@Override
	public void deleteMenuOption(int shopId, int menuOptionId, String userId) {
		checkIsAuthorizedUser(shopId, userId);
		menuOptionMapper.deleteMenuOption(menuOptionId);
	}

	private void checkIsAuthorizedUser(int shopId, String userId) {
		if (shopId != shopMapper.findShopIdByMerchantId(userId)) {
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
