package com.flab.foodeats.application.menu.adapter;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.flab.foodeats.application.menu.EssentialMenuTarget;
import com.flab.foodeats.application.menu.port.MenuService;

@Service
public class MenuServiceImpl implements MenuService {

	private final MenuMapper menuMapper;

	public MenuServiceImpl(MenuMapper menuMapper) {
		this.menuMapper = menuMapper;
	}

	@Override
	public void registerMenu(int shopId, EssentialMenuTarget target) {
		List<Menu> menus = menuMapper.searchMenu(shopId);
		checkIsDuplicateMenu(menus, target);
		menuMapper.registerMenu(shopId, target.toEntity());
	}

	@Override
	public List<Menu> searchMenu(int shopId) {
		List<Menu> menu = menuMapper.searchMenu(shopId).stream()
			.sorted(Comparator.comparing(Menu::getMenuGroup))
			.collect(Collectors.toList());
		return menu;
	}

	@Override
	public void modifyMenu(int menuId, EssentialMenuTarget target) {
		menuMapper.modifyMenu(menuId, target.toEntity());
	}

	@Override
	public void deleteMenu(int menuId) {
		menuMapper.deleteMenu(menuId);
	}
	
	private void checkIsDuplicateMenu(List<Menu> menus, EssentialMenuTarget target) {
		Optional<Menu> existMenu = menus.stream()
			.filter(m -> m.getMenuName().equals(target.getMenuName()))
			.findAny();

		if (existMenu.isPresent()) {
			throw new IllegalArgumentException("이미 등록되어 있는 메뉴");
		}
	}
}
