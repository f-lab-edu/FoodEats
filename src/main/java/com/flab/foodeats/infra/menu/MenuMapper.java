package com.flab.foodeats.infra.menu;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.flab.foodeats.domain.menu.Menu;

@Mapper
public interface MenuMapper {

	void registerMenu(@Param("menu") Menu menu);

	List<Menu> searchMenu(int shopId);

	void modifyMenu(@Param("menu")Menu menu);

	void deleteMenu(int shopId, int menuId);
}

