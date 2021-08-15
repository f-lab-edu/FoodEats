package com.flab.foodeats.search.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.flab.foodeats.search.model.SearchShopResultDTO;
import com.flab.foodeats.search.model.code.SearchErrorCode;

@Component
public class ShopSearchErrorCheck {
	public void resultEmptyCheck (List<SearchShopResultDTO> result) {
		if (result.isEmpty()) {
			throw new NullPointerException(SearchErrorCode.NOT_FOUND_SEARCH_RESULT.getMessage());
		}
	}
}
