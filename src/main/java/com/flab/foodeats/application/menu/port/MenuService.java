package com.flab.foodeats.application.menu.port;

import com.flab.foodeats.application.menu.EssentialMenuTarget;

public interface MenuService {

	void registerMenu(EssentialMenuTarget essentialMenuTarget, String userId);

	void modifyMenu(EssentialMenuTarget essentialMenuTarget, String userId);

	void deleteMenu(Long shopId, Long menuId, String UserId);
}
