package com.flab.foodeats.application.shop.adapter;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.flab.foodeats.application.shop.DeleteEssentialTarget;
import com.flab.foodeats.application.shop.EssentialTarget;
import com.flab.foodeats.application.shop.StatusTarget;
import com.flab.foodeats.application.shop.port.ShopService;
import com.flab.foodeats.common.response.ErrorUserCode;
import com.flab.foodeats.domain.shop.Essential;
import com.flab.foodeats.infra.shop.ShopMapper;

@Service
@Transactional
public class ShopServiceImpl implements ShopService {

	private ShopMapper shopMapper;

	public ShopServiceImpl(ShopMapper shopMapper) {
		this.shopMapper = shopMapper;
	}

	// 가맹점 등록 (기본정보)
	public void registerEssentialShopInfo(EssentialTarget target) {
		Essential essential = shopMapper.findEssentialByShopId(target.getShopId());
		shopInfoAlreadyExist(essential);
		shopMapper.registerEssentialInfo(target.toEntity());
	}

	// 가맹점 수정 (기본정보)
	public void updateEssentialShopInfo(EssentialTarget target) {
		shopMapper.updateEssentialInfo(target.toEntity());
	}

	// 가맹점 삭제 (기본정보)
	public void deleteEssentialShopInfo(DeleteEssentialTarget target) {
		shopMapper.deleteEssentialInfo(target.getUserId());
	}

	// 가맹점 등록 (상태정보)
	public void registerStatusShopInfo(StatusTarget target) {
		shopInfoAlreadyExist(shopMapper.findStatusByShopId(target.getShopId()));
		shopEssentialInfoNotExist(shopMapper.findEssentialByShopId(target.getShopId()));
		shopMapper.registerStatusInfo(target.toEntity());
	}


	public <T> void shopInfoAlreadyExist(T entity) {
		if (entity != null) {
			throw new DuplicateKeyException(ErrorUserCode.SHOP_EXIST.getMessage());
		}
	}

	public void shopEssentialInfoNotExist(Essential essential) {
		if (essential == null) {
			throw new DuplicateKeyException(ErrorUserCode.ESSENTIAL_INFO_NOT_EXIST.getMessage());
		}
	}
}
