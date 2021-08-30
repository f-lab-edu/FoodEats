package com.flab.foodeats.application.shop.adapter;

import java.util.List;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.flab.foodeats.application.shop.ConvenientTarget;
import com.flab.foodeats.application.shop.DeleteEssentialTarget;
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

	// 가맹점 수정 (상태정보)
	public void updateStatusShopInfo(StatusTarget target) {
		shopMapper.updateStatusInfo(target.toEntity());
	}

	// 가맹점 등록 (편리정보)
	public void registerConvenienceShopInfo(ConvenientTarget target) {
		shopInfoAlreadyExist(shopMapper.findConvenienceByShopId(target.getShopId()));
		shopEssentialInfoNotExist(shopMapper.findEssentialByShopId(target.getShopId()));
		shopMapper.registerConvenienceInfo(target.toEntity());
	}

	/**
	 * 추후 삭제
	 * 동작을 확인하기 위한 조회입니다.
	 * 추후 삭제할 부분 입니다!
	 */

	public List<Essential> searchShopAllInfo(StatusTarget target) {

		// 주말 체크
		if(new Weekend().weekendCheck()){
			System.out.println("주말");
		}
		else{
			System.out.println("주말x");
		}

		// 공휴일 체크
		if(new PublicHoliday().calculator()){
			System.out.println("공휴일");
		}
		else{
			System.out.println("공휴일x");
		}

		/**
		 * todo
		 * null 체크 / 데이터 있는지
		 */
		// 가맹점 자동상태 변환
		Status status = shopMapper.findStatusByShopId(target.getShopId());
		if(new ShopAutomaticStatus().changeShopStatusAuto(status)){
			shopMapper.startShop(status);
		}
		else{
			shopMapper.closeShop(status);
		}


		return shopMapper.shopListAllInfo();
	}

	// 가맹점 수정 (편리정보)
	public void updateConvenienceShopInfo(ConvenientTarget target) {
		shopMapper.updateConvenienceInfo(target.toEntity());
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
