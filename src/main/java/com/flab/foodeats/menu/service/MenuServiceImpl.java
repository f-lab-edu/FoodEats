package com.flab.foodeats.menu.service;

import java.util.List;

import com.flab.foodeats.global.ApiResponse;
import com.flab.foodeats.global.StatusCode;
import com.flab.foodeats.menu.mapper.MenuMapper;
import com.flab.foodeats.menu.model.MenuEssentialParam;
import com.flab.foodeats.menu.model.MenuOptionParam;
import com.flab.foodeats.menu.model.MenuRegisterParam;
import com.flab.foodeats.menu.model.code.SuccessMenuCode;

public class MenuServiceImpl implements MenuService{
	private MenuMapper menuMapper;

	public MenuServiceImpl(MenuMapper menuMapper) {
		this.menuMapper = menuMapper;
	}

	@Override
	public ApiResponse registerMenu(int shopId, MenuRegisterParam menuRegisterParam) {
		menuMapper.registerMenu(shopId, menuRegisterParam);

		List<MenuOptionParam> menuOptionParams = menuRegisterParam.getMenuOptionParams();
		for (MenuOptionParam menuOptionParam : menuOptionParams) {
			menuMapper.registerOption(shopId, menuRegisterParam.getMenuId(), menuOptionParam);
		}
		ApiResponse apiResponse = new ApiResponse(StatusCode.SUCCESS, SuccessMenuCode.MENU_REGISTER_SUCCESS);
		return apiResponse;
	}

	@Override
	public ApiResponse searchMenu(int shopId) {
		List<MenuEssentialParam> menu = menuMapper.searchMenu(shopId);
		ApiResponse apiResponse = new ApiResponse(StatusCode.SUCCESS, menu);
		return apiResponse;
	}

	@Override
	public ApiResponse searchMenuOption(int menuId) {
		List<MenuOptionParam> menuOption = menuMapper.searchMenuOption(menuId);
		ApiResponse apiResponse = new ApiResponse(StatusCode.SUCCESS, menuOption);
		return apiResponse;
	}

	@Override
	public ApiResponse updateMenu(int menuId, MenuEssentialParam menuEssentialParam) {
		menuMapper.updateMenu(menuId, menuEssentialParam);
		ApiResponse apiResponse = new ApiResponse(StatusCode.SUCCESS, SuccessMenuCode.MENU_UPDATE_SUCCESS);
		return apiResponse;
	}

	@Override
	public ApiResponse deleteMenu(int menuId) {
		menuMapper.deleteMenu(menuId);
		ApiResponse apiResponse = new ApiResponse(StatusCode.SUCCESS, SuccessMenuCode.MENU_DELETE_SUCCESS);
		return apiResponse;
	}

	@Override
	public ApiResponse updateMenuOption(int menuOptionId, MenuOptionParam menuOptionParam) {
		menuMapper.updateMenuOption(menuOptionId, menuOptionParam);
		ApiResponse apiResponse = new ApiResponse(StatusCode.SUCCESS,SuccessMenuCode.OPTION_UPDATE_SUCCESS);
		return apiResponse;
	}

	@Override
	public ApiResponse deleteMenuOption(int menuOptionId) {
		menuMapper.deleteMenuOption(menuOptionId);
		ApiResponse apiResponse = new ApiResponse(StatusCode.SUCCESS,SuccessMenuCode.OPTION_DELETE_SUCCESS);
		return apiResponse;
	}
}
