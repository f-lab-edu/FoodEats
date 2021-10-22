package com.flab.foodeats.application.menu.adapter;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.flab.foodeats.api.menu.MenuCode;
import com.flab.foodeats.api.menu.OptionRequest;
import com.flab.foodeats.application.menu.OptionTarget;
import com.flab.foodeats.application.menu.port.MenuOptionService;
import org.springframework.transaction.annotation.Transactional;

import com.flab.foodeats.common.auth.AuthInfo;
import com.flab.foodeats.common.error.exception.UnauthorizedException;
import com.flab.foodeats.common.response.ErrorUserCode;
import com.flab.foodeats.domain.menu.Menu;
import com.flab.foodeats.domain.menu.MenuOption;
import com.flab.foodeats.domain.shop.Shop;
import com.flab.foodeats.infra.menu.MenuOptionRepository;
import com.flab.foodeats.infra.menu.MenuRepository;
import com.flab.foodeats.infra.shop.ShopRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class MenuOptionServiceImpl implements MenuOptionService {

	private final MenuOptionRepository menuOptionRepository;
	private final MenuRepository menuRepository;
	private final ShopRepository shopRepository;

	@Override
	public void registerMenuOption(long shopId, long menuId, OptionRequest optionRequests, AuthInfo authInfo) {
		// 권한검사
		Shop shop = shopRepository.findById(shopId)
			.orElseThrow(() -> new NullPointerException(ErrorUserCode.SHOP_INFO_NOT_EXIST.getMessage()));
		checkIsAuthorizedUser(shop.getMerchant().getId(), authInfo.getId());

		List<MenuOption> menuOptions = menuOptionRepository.findAllByMenuId(shopId);
		checkIsExistOptionMenu(menuOptions, optionRequests);

		// menu 객체 매핑
		Menu menu = menuRepository.findById(shopId)
			.orElseThrow(() -> new NullPointerException(ErrorUserCode.SHOP_INFO_NOT_EXIST.getMessage()));
		MenuOption menuOption = optionRequests.toEntity(menuId);
		menuOption.setMenu(menu);

		menuOptionRepository.save(menuOption);
	}

	@Override
	public void updateMenuOption(long shopId, OptionTarget optionTarget,AuthInfo authInfo) {
		// 권한검사
		Shop shop = shopRepository.findById(shopId)
			.orElseThrow(() -> new NullPointerException(ErrorUserCode.SHOP_INFO_NOT_EXIST.getMessage()));
		checkIsAuthorizedUser(shop.getMerchant().getId(), authInfo.getId());

		List<MenuOption> menuOptions = menuOptionRepository.findAllByMenuId(optionTarget.getMenuId());
		checkIsExistUpdateOptionMenu(menuOptions, optionTarget);

		MenuOption menuOption = menuOptionRepository.getById(optionTarget.getMenuOptionId());
		menuOption.setMenuOptionName(optionTarget.getMenuOptionName());
		menuOption.setMenuOptionPrice(optionTarget.getMenuOptionPrice());

		menuOptionRepository.save(menuOption);
	}

	@Override
	public void deleteMenuOption(long shopId, long menuOptionId,AuthInfo authInfo) {
		// 권한검사
		Shop shop = shopRepository.findById(shopId)
			.orElseThrow(() -> new NullPointerException(ErrorUserCode.SHOP_INFO_NOT_EXIST.getMessage()));
		checkIsAuthorizedUser(shop.getMerchant().getId(), authInfo.getId());

		menuOptionRepository.deleteById(menuOptionId);
	}

	private void checkIsAuthorizedUser(long requestedId, long userId) {
		if (requestedId != userId) {
			throw new UnauthorizedException();
		}
	}

	private void checkIsExistOptionMenu(List<MenuOption> menuOptions, OptionRequest optionRequests) {
		Optional<MenuOption> existMenu = menuOptions.stream()
			.filter(m -> m.getMenuOptionName().equals(optionRequests.getMenuOptionName()))
			.findAny();

		if (existMenu.isPresent()) {
			throw new IllegalArgumentException(MenuCode.ALEADY_EXIST_MENU.getMessage());
		}
	}

	private void checkIsExistUpdateOptionMenu(List<MenuOption> menuOptions, OptionTarget optionTarget) {
		Optional<MenuOption> existMenu = menuOptions.stream()
			.filter(m -> m.getMenuOptionName().equals(optionTarget.getMenuOptionName()))
			.findAny();

		if (existMenu.isPresent()) {
			throw new IllegalArgumentException(MenuCode.ALEADY_EXIST_MENU.getMessage());
		}
	}
}
