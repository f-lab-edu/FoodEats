package com.flab.foodeats.menu.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.flab.foodeats.menu.model.MenuEssentialParam;
import com.flab.foodeats.menu.model.MenuOptionParam;
import com.flab.foodeats.menu.model.MenuRegisterParam;

@Mapper
public interface MenuMapper {

	// 가맹점 메뉴 등록
	void registerMenu(@Param("shopId") int shopId, @Param("menuRegisterParam") MenuRegisterParam menuRegisterParam);

	// 가맹점 옵션 등록
	void registerOption(
		@Param("shopId") int shopId,
		@Param("menuId") int menuId,
		@Param("menuOptionParam") MenuOptionParam menuOptionParam);

	// 가맹점 메뉴 검색
	List<MenuEssentialParam> searchMenu(int shopId);

	// 메뉴별 옵션 검색
	List<MenuOptionParam> searchMenuOption(int menuId);

}
