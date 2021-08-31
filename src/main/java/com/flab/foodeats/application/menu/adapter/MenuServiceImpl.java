package com.flab.foodeats.application.menu.adapter;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.flab.foodeats.api.menu.MenuCode;
import com.flab.foodeats.application.menu.EssentialMenuTarget;
import com.flab.foodeats.application.menu.port.MenuService;
import com.flab.foodeats.common.error.exception.UnauthorizedException;
import com.flab.foodeats.domain.menu.Menu;
import com.flab.foodeats.infra.menu.MenuMapper;
import com.flab.foodeats.infra.shop.ShopMapper;

@Service
public class MenuServiceImpl implements MenuService {

	private final MenuMapper menuMapper;
	private final ShopMapper shopMapper;

	public MenuServiceImpl(MenuMapper menuMapper, ShopMapper shopMapper) {
		this.menuMapper = menuMapper;
		this.shopMapper = shopMapper;
	}

	@Override
	public void registerMenu(EssentialMenuTarget target, String userId) {
		checkIsAuthorizedUser(target.getShopId(), userId);

		List<Menu> menus = menuMapper.searchMenu(target.getShopId());
		checkIsExistMenu(menus, target);

		menuMapper.registerMenu(target.toEntity());
	}

	@Override
	public void modifyMenu(EssentialMenuTarget target, String userId) {
		checkIsAuthorizedUser(target.getShopId(), userId);
		menuMapper.modifyMenu(target.toEntity());
	}

	@Override
	public void deleteMenu(int shopId, int menuId, String userId) {
		checkIsAuthorizedUser(shopId, userId);
		menuMapper.deleteMenu(shopId, menuId);
	}
	
	private void checkIsExistMenu(List<Menu> menus, EssentialMenuTarget target) {
		Optional<Menu> existMenu = menus.stream()
			.filter(m -> m.getMenuName().equals(target.getMenuName()))
			.findAny();

		if (existMenu.isPresent()) {
			throw new IllegalArgumentException(MenuCode.ALEADY_EXIST_MENU.getMessage());
		}
	}

	private void checkIsAuthorizedUser(int shopId, String userId) {
		int shopIdByMerchantId = shopMapper.findShopIdByMerchantId(userId);
		if (shopId != shopIdByMerchantId) {
			throw new UnauthorizedException();
		}
	}
}
