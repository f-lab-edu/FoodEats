package com.flab.foodeats.application.menu.port;

import java.util.List;

import com.flab.foodeats.api.menu.OptionRequest;
import com.flab.foodeats.application.menu.OptionTarget;

public interface MenuOptionService {

	void registerMenuOption(long shopId, long menuId, List<OptionRequest> optionRequests, String userId);

	void updateMenuOption(long shopId, OptionTarget optionTarget, String userId);

	void deleteMenuOption(long shopId, long menuOptionId, String userId);
}
