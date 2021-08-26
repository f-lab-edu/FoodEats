package com.flab.foodeats.application.menu.adapter;

import java.util.List;

import org.springframework.stereotype.Service;

import com.flab.foodeats.application.menu.OptionTarget;
import com.flab.foodeats.application.menu.port.MenuOptionService;

@Service
public class MenuOptionServiceImpl implements MenuOptionService {

	private final MenuOptionMapper menuOptionMapper;

	public MenuOptionServiceImpl(MenuOptionMapper menuOptionMapper) {
		this.menuOptionMapper = menuOptionMapper;
	}

	@Override
	public void registerMenuOption(int menuId, OptionTarget target) {
		List<MenuOption> menuOptions = target.getMenuOptions();
		menuOptions.stream()
			.forEach(option ->
				menuOptionMapper.registerOption(menuId, option.getMenuOptionName(), option.getMenuOptionPrice()));
	}

	@Override
	public List<MenuOption> searchMenuOption(int menuId) {
		return menuOptionMapper.searchMenuOption(menuId);
	}

	@Override
	public void updateMenuOption(int menuOptionId, OptionTarget target) {
		List<MenuOption> menuOptions = target.getMenuOptions();
		for (MenuOption menuOption : menuOptions) {
			String menuOptionName = menuOption.getMenuOptionName();
			int menuOptionPrice = menuOption.getMenuOptionPrice();
			menuOptionMapper.updateMenuOption(menuOptionId, menuOptionName, menuOptionPrice);
		}
	}

	@Override
	public void deleteMenuOption(int menuOptionId) {
		menuOptionMapper.deleteMenuOption(menuOptionId);
	}
}
