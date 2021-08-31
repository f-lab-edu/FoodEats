package com.flab.foodeats.infra.menu;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.flab.foodeats.domain.menu.MenuOption;

@Mapper
public interface MenuOptionMapper {

	void registerOption(@Param("menuId") int menuId,
		@Param("menuOptionName") String menuOptionName,
		@Param("menuOptionPrice") int menuOptionPrice);

	List<MenuOption> searchMenuOption(int menuId);

	void updateMenuOption(@Param("menuOptionId") int menuOptionId,
		@Param("menuOptionName") String menuOptionName,
		@Param("menuOptionPrice") int menuOptionPrice);

	void deleteMenuOption(int menuOptionId);
}
