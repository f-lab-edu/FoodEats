package com.flab.foodeats.application.menu.adapter;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.times;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.flab.foodeats.application.menu.EssentialMenuTarget;
import com.flab.foodeats.application.menu.port.MenuService;
import com.flab.foodeats.domain.menu.Menu;
import com.flab.foodeats.infra.menu.MenuMapper;
import com.flab.foodeats.infra.shop.ShopMapper;

@ExtendWith(MockitoExtension.class)
class MenuServiceImplTest {

	private MenuService menuService;

	@Mock
	private MenuMapper menuMapper;
	@Mock
	private ShopMapper shopMapper;

	EssentialMenuTarget target;

	Menu firstMenu;
	Menu secondMenu;
	Menu thirdMenu;
	List<Menu> menus = new ArrayList<>();

	String userId;
	int shopId;
	int menuId;

	@BeforeEach
	void setUp() {

		menuService = new MenuServiceImpl(menuMapper, shopMapper);

		target = new EssentialMenuTarget(1, "치킨", 15000, "치킨+콜라", "후라이드", "대표메뉴");

		firstMenu = target.toEntity();
		secondMenu = new Menu(1, 2, "콜라", 2000, "콜라 단품", "코카콜라", "음료수");
		thirdMenu = new Menu(1, 3, "사이다", 2000, "사이다 단품", "스프라이트", "음료수");

		userId = "yusok";
		shopId = 1;
		menuId = 1;
	}

	@Test
	@DisplayName("메뉴 등록 성공")
	void registerMenuTest() {

		//given
		BDDMockito.given(shopMapper.findShopIdByMerchantId(any())).willReturn(target.getShopId());
		menus.add(secondMenu);
		menus.add(thirdMenu);
		BDDMockito.given(menuMapper.searchMenu(target.getShopId())).willReturn(menus);

		//when
		menuService.registerMenu(target, userId);

		//then
		then(menuMapper).should(times(1)).registerMenu(any());
	}

	@Test
	@DisplayName("메뉴 등록 실패 - 중복 메뉴 존재")
	void registerMenuFailTest() {

		//given
		BDDMockito.given(shopMapper.findShopIdByMerchantId(any())).willReturn(target.getShopId());
		menus.add(firstMenu); // firstMenu == target.toEntity()
		menus.add(secondMenu);
		BDDMockito.given(menuMapper.searchMenu(target.getShopId())).willReturn(menus);

		//when
		assertThrows(IllegalArgumentException.class, () -> menuService.registerMenu(target, userId));

	}

	@Test
	@DisplayName("메뉴 수정 성공 - 본인가게")
	void modifyMenuTest() {

		// given
		BDDMockito.given(shopMapper.findShopIdByMerchantId(any())).willReturn(target.getShopId());
		// when
		menuService.modifyMenu(target, userId);
		//then
		then(menuMapper).should(only()).modifyMenu(any());
	}

	@Test
	@DisplayName("메뉴 수정 실패 - 본인가게X")
	void modifyMenuFailTest() {

		// given
		BDDMockito.given(shopMapper.findShopIdByMerchantId(any())).willReturn(123456);
		// then
		assertThrows(UnauthorizedException.class, () -> menuService.modifyMenu(target, userId));
	}

	@Test
	@DisplayName("메뉴 삭제 성공")
	void deleteMenuTest() {

		// given
		BDDMockito.given(shopMapper.findShopIdByMerchantId(any())).willReturn(shopId);
		// when
		menuService.deleteMenu(shopId, menuId, userId);
		// then
		then(menuMapper).should(times(1)).deleteMenu(shopId, menuId);
	}

	@Test
	@DisplayName("메뉴 삭제 실패 - 본인 가게X")
	void deleteMenuFailTest() {

		// given
		BDDMockito.given(shopMapper.findShopIdByMerchantId(any())).willReturn(123456);
		// then
		assertThrows(UnauthorizedException.class, () -> menuService.deleteMenu(shopId, menuId, userId));
	}

}