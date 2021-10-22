package com.flab.foodeats.application.menu.adapter;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.flab.foodeats.api.menu.MenuCode;
import com.flab.foodeats.application.menu.EssentialMenuTarget;
import com.flab.foodeats.application.menu.port.MenuService;
import com.flab.foodeats.common.auth.AuthInfo;
import com.flab.foodeats.common.error.exception.UnauthorizedException;
import com.flab.foodeats.common.response.ErrorUserCode;
import com.flab.foodeats.domain.menu.Menu;
import com.flab.foodeats.domain.shop.Shop;
import com.flab.foodeats.infra.menu.MenuMapper;
import com.flab.foodeats.infra.menu.MenuRepository;
import com.flab.foodeats.infra.shop.ShopMapper;
import com.flab.foodeats.infra.shop.ShopRepository;
import com.flab.foodeats.infra.user.UserMapper;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class MenuServiceImpl implements MenuService {

	private final MenuRepository menuRepository;
	private final ShopRepository shopRepository;

	@Override
	public void registerMenu(EssentialMenuTarget target, AuthInfo authInfo) {

		// 권한검사
		Shop shop = shopRepository.findById(target.getShopId())
			.orElseThrow(() -> new NullPointerException(ErrorUserCode.SHOP_INFO_NOT_EXIST.getMessage()));
		checkIsAuthorizedUser(shop.getMerchant().getId(), authInfo.getId());

		// 메뉴가 있는지 검사
		List<Menu> menus = menuRepository.findAllByShopId(target.getShopId());
		checkIsExistMenu(menus, target);

		// shop 객체 매핑
		Menu menu = target.toEntity();
		menu.setShop(shop);
		shop.setMenuList(menus);

		menuRepository.save(menu);
	}

	@Override
	public void modifyMenu(EssentialMenuTarget target, AuthInfo authInfo) {
		// 권한검사
		Shop shop = shopRepository.findById(target.getShopId())
			.orElseThrow(() -> new NullPointerException(ErrorUserCode.SHOP_INFO_NOT_EXIST.getMessage()));
		checkIsAuthorizedUser(shop.getMerchant().getId(), authInfo.getId());

		// 메뉴가 있는지 중복 검사
		List<Menu> menus = menuRepository.findAllByShopId(target.getShopId());
		checkIsExistMenu(menus, target);

		menuRepository.save(target.toEntity());
	}

	@Override
	public void deleteMenu(long shopId, long menuId, AuthInfo authInfo) {
		// 권한검사
		Shop shop = shopRepository.findById(shopId)
			.orElseThrow(() -> new NullPointerException(ErrorUserCode.SHOP_INFO_NOT_EXIST.getMessage()));
		checkIsAuthorizedUser(shop.getMerchant().getId(), authInfo.getId());

		menuRepository.deleteById(menuId);
	}

	private void checkIsExistMenu(List<Menu> menus, EssentialMenuTarget target) {
		Optional<Menu> existMenu = menus.stream()
			.filter(m -> m.getMenuName().equals(target.getMenuName()))
			.findAny();

		if (existMenu.isPresent()) {
			throw new IllegalArgumentException(MenuCode.ALEADY_EXIST_MENU.getMessage());
		}
	}

	private void checkIsAuthorizedUser(long requestedId, long userId) {
		if (requestedId != userId) {
			throw new UnauthorizedException();
		}
	}
}
