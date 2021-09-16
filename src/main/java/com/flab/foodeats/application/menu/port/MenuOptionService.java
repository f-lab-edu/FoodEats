package com.flab.foodeats.application.menu.port;

import java.util.List;

import com.flab.foodeats.api.menu.OptionRequest;

public interface MenuOptionService {

	void registerMenuOption(Long shopId, int menuId, List<OptionRequest> optionRequests, String userId);

	void updateMenuOption(Long shopId, OptionTarget optionTarget, String userId);

	void deleteMenuOption(Long shopId, int menuOptionId, String userId);
}
