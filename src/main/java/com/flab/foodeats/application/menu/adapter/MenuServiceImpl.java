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
import com.flab.foodeats.infra.user.UserMapper;

@Service
public class MenuServiceImpl implements MenuService {

	private final MenuMapper menuMapper;
	private final ShopMapper shopMapper;
	private final UserMapper userMapper;

	public MenuServiceImpl(MenuMapper menuMapper, ShopMapper shopMapper, UserMapper userMapper) {
		this.menuMapper = menuMapper;
		this.shopMapper = shopMapper;
		this.userMapper = userMapper;
	}

	@Override
	public void registerMenu(EssentialMenuTarget target, String userId) {
		Long ownerShopId = target.getShopId();
		String requestedOwnerId = userMapper.findMerchantByShopId(ownerShopId).getUserId();
		checkIsAuthorizedUser(requestedOwnerId, userId);

		List<Menu> menus = menuMapper.searchMenu(target.getShopId());
		checkIsExistMenu(menus, target);

		menuMapper.registerMenu(target.toEntity());
	}

	@Override
	public void modifyMenu(EssentialMenuTarget target, String userId) {
		Long ownerShopId = target.getShopId();
		String requestedOwnerId = userMapper.findMerchantByShopId(ownerShopId).getUserId();
		checkIsAuthorizedUser(requestedOwnerId, userId);
		menuMapper.modifyMenu(target.toEntity());
	}

	@Override
	public void deleteMenu(Long shopId, Long menuId, String userId) {
		String requestedOwnerId = userMapper.findMerchantByShopId(shopId).getUserId();
		checkIsAuthorizedUser(requestedOwnerId, userId);
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

	private void checkIsAuthorizedUser(String requestedId, String userId) {
		if (!requestedId.equals(userId)) {
			throw new UnauthorizedException();
		}
	}
}
