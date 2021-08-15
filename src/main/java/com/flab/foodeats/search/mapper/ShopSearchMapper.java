package com.flab.foodeats.search.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.flab.foodeats.search.model.SearchShopResultDTO;

@Mapper
public interface ShopSearchMapper {

	List<SearchShopResultDTO> findShopByCategory(@Param("address") String address, @Param("category") String category);

	List<SearchShopResultDTO> findShopByBrand(@Param("address") String address, @Param("brand") String brand);
}
