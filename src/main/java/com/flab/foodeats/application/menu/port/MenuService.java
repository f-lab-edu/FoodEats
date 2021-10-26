package com.flab.foodeats.application.menu.port;

import com.flab.foodeats.application.menu.EssentialMenuTarget;
import com.flab.foodeats.common.auth.AuthInfo;

public interface MenuService {

	void registerMenu(EssentialMenuTarget essentialMenuTarget, AuthInfo authInfo);

	void modifyMenu(EssentialMenuTarget essentialMenuTarget,AuthInfo authInfo);

	void deleteMenu(long shopId, long menuId,  AuthInfo authInfo);
}
