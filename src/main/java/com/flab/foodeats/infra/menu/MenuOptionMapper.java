package com.flab.foodeats.infra.menu;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.flab.foodeats.domain.menu.MenuOption;

@Mapper
public interface MenuOptionMapper {

	void registerOption(@Param("menuOption") MenuOption menuOption);

	List<MenuOption> searchMenuOption(long menuId);

	MenuOption searchMenuOptionByOptionId(long menuOptionId);

	void updateMenuOption(@Param("menuOption") MenuOption menuOption);

	void deleteMenuOption(long menuOptionId);
}
