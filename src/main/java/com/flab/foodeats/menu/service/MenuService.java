package com.flab.foodeats.menu.service;

import com.flab.foodeats.global.ApiResponse;
import com.flab.foodeats.menu.model.MenuRegisterParam;

public interface MenuService {

	ApiResponse registerMenu(int shopId, MenuRegisterParam menuRegisterParam);

	ApiResponse searchMenu(int shopId);

	ApiResponse searchMenuOption(int menuId);
}
