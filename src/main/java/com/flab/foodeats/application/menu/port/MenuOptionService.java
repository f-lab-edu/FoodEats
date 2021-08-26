package com.flab.foodeats.application.menu.port;

import java.util.List;

public interface MenuOptionService {

	void registerMenuOption(int menuId, OptionTarget target);

	List<MenuOption> searchMenuOption(int menuId);

	void updateMenuOption(int menuOptionId, OptionTarget target);

	void deleteMenuOption(int menuOptionId);
}
