package com.flab.foodeats.search.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import com.flab.foodeats.search.model.SearchShopResultDTO;

@Mapper
public interface ShopSearchMapper {

	List<SearchShopResultDTO> findShopByCategory(String category);

	List<SearchShopResultDTO> findShopByBrand(String brand);
}
