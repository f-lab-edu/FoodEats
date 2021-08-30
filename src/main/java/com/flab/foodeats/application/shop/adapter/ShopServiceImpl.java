package com.flab.foodeats.application.shop.adapter;

import java.util.List;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.flab.foodeats.application.shop.DeleteEssentialTarget;
import com.flab.foodeats.application.shop.ConvenientTarget;
import com.flab.foodeats.application.shop.EssentialTarget;
import com.flab.foodeats.application.shop.StatusTarget;
import com.flab.foodeats.application.shop.autostatus.ShopAutomaticStatus;
import com.flab.foodeats.application.shop.holiday.PublicHoliday;
import com.flab.foodeats.application.shop.holiday.Weekend;
import com.flab.foodeats.application.shop.port.ShopService;

import com.flab.foodeats.common.response.ErrorUserCode;
import com.flab.foodeats.domain.shop.Essential;
import com.flab.foodeats.domain.shop.Status;
import com.flab.foodeats.infra.shop.ShopMapper;

@Service
@Transactional
public class ShopServiceImpl implements ShopService {

	private ShopMapper shopMapper;
	private ShopAutomaticStatus shopAutomaticStatus;

	public ShopServiceImpl(ShopMapper shopMapper, ShopAutomaticStatus shopAutomaticStatus) {
		this.shopMapper = shopMapper;
		this.shopAutomaticStatus = shopAutomaticStatus;
	}

	// 가맹점 등록 (기본정보)
	public void registerEssentialShopInfo(EssentialTarget target) {
		Essential essential = shopEssentialInfo(target.getUserId());
		shopInfoAlreadyExist(essential);
		shopMapper.registerEssentialInfo(target.changeToEntityForRegister());
	}

	// 가맹점 수정 (기본정보)
	public void updateEssentialShopInfo(EssentialTarget target) {
		Essential getEssentialInfo = shopEssentialInfo(target.getUserId());
		shopEntityInfoNotExist(getEssentialInfo);
		Essential changeEssentialInfo = target.changeToEntityForUpdate(getEssentialInfo.getShopId());
		shopMapper.updateEssentialInfo(changeEssentialInfo);
	}

	// 가맹점 삭제 (기본정보)
	public void deleteEssentialShopInfo(DeleteEssentialTarget target) {
		Essential essential = shopEssentialInfo(target.getUserId());
		shopEntityInfoNotExist(essential);
		shopMapper.deleteEssentialInfo(essential.getShopId());
		shopMapper.deleteStatusInfo(essential.getShopId());
		shopMapper.deleteConvenienceInfo(essential.getShopId());
	}

	// 가맹점 등록 (상태정보)
	public void registerStatusShopInfo(StatusTarget target, String userId) {
		Essential essential = shopEssentialInfo(userId);
		shopInfoAlreadyExist(shopMapper.findStatusByShopId(userId));
		shopEessentialInfoNotExist(essential);
		shopMapper.registerStatusInfo(target.toEntity(essential.getShopId()));
	}

	// 가맹점 수정 (상태정보)
	public void updateStatusShopInfo(StatusTarget target, String userId) {
		Essential essential = shopEssentialInfo(userId);
		shopEntityInfoNotExist(shopMapper.findStatusByShopId(userId));
		shopMapper.updateStatusInfo(target.toEntity(essential.getShopId()));
	}

	// 가맹점 등록 (편리정보)
	public void registerConvenienceShopInfo(ConvenientTarget target, String userId) {
		Essential essential = shopEssentialInfo(userId);
		shopInfoAlreadyExist(shopMapper.findConvenienceByShopId(userId));
		shopEessentialInfoNotExist(essential);
		shopMapper.registerConvenienceInfo(target.toEntity(essential.getShopId()));
	}

	// 가맹점 수정 (편리정보)
	public void updateConvenienceShopInfo(ConvenientTarget target, String userId) {
		Essential essential = shopEssentialInfo(userId);
		shopEntityInfoNotExist(shopMapper.findConvenienceByShopId(userId));
		shopMapper.updateConvenienceInfo(target.toEntity(essential.getShopId()));
	}

	/**
	 * 추후 삭제
	 * 동작을 확인하기 위한 조회입니다.
	 * 추후 삭제할 부분 입니다!
	 */

	public List<Essential> searchShopAllInfo(StatusTarget target, String userId) {

		// 주말 체크
		if (new Weekend().weekendCheck()) {
			System.out.println("주말");
		} else {
			System.out.println("주말x");
		}

		// 공휴일 체크
		if (new PublicHoliday().calculator()) {
			System.out.println("공휴일");
		} else {
			System.out.println("공휴일x");
		}

		// 가맹점 자동상태 변환
		Status status = shopMapper.findStatusByShopId(userId);
		if (shopAutomaticStatus.changeShopStatusAuto(status)) {
			shopMapper.startShop(status);
		} else {
			shopMapper.closeShop(status);
		}

		return shopMapper.shopListAllInfo();
	}

	private Essential shopEssentialInfo(String userId) {
		return shopMapper.findEssentialByShopId(userId);
	}

	private <T> void shopInfoAlreadyExist(T entity) {
		if (entity != null) {
			throw new DuplicateKeyException(ErrorUserCode.SHOP_EXIST.getMessage());
		}
	}

	private <T> void shopEntityInfoNotExist(T entity) {
		if (entity == null) {
			throw new DuplicateKeyException(ErrorUserCode.ENTITY_INFO_NOT_EXIST.getMessage());
		}
	}

	private void shopEessentialInfoNotExist(Essential essential) {
		if (essential == null) {
			throw new DuplicateKeyException(ErrorUserCode.ESSENTIAL_INFO_NOT_EXIST.getMessage());
		}
	}
}
