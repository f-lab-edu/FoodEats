package com.flab.foodeats.application.menu.port;

import java.awt.*;
import java.util.List;

public interface MenuService {

	void registerMenu(int shopId, EssentialMenuTarget essentialMenuTarget);

	List<Menu> searchMenu(int shopId);

	void modifyMenu(int menuId, EssentialMenuTarget essentialMenuTarget);

	void deleteMenu(int menuId);
}
