package com.flab.foodeats.application.menu.port;

import java.util.List;

import com.flab.foodeats.api.menu.OptionRequest;
import com.flab.foodeats.application.menu.OptionTarget;

public interface MenuOptionService {

	void registerMenuOption(Long shopId, Long menuId, List<OptionRequest> optionRequests, String userId);

	void updateMenuOption(Long shopId, OptionTarget optionTarget, String userId);

	void deleteMenuOption(Long shopId, Long menuOptionId, String userId);
}
