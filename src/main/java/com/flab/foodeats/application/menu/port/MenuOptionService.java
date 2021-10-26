package com.flab.foodeats.application.menu.port;

import java.util.List;

import com.flab.foodeats.api.menu.OptionRequest;
import com.flab.foodeats.application.menu.OptionTarget;
import com.flab.foodeats.common.auth.AuthInfo;

public interface MenuOptionService {

	void registerMenuOption(long shopId, long menuId, OptionRequest optionRequests, AuthInfo authInfo);

	void updateMenuOption(long shopId, OptionTarget optionTarget,AuthInfo authInfo);

	void deleteMenuOption(long shopId, long menuOptionId,AuthInfo authInfo);
}
