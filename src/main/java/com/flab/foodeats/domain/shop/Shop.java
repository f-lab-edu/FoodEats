package com.flab.foodeats.domain.shop;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.flab.foodeats.domain.menu.Menu;
import com.flab.foodeats.domain.user.Merchant;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Table(name = "shop")
@Entity
@ToString
public class Shop {

	@Id
	@Column(name = "shop_no")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long shopId;

	@Column(name = "shop_category", nullable = false)
	private String category;

	@Column(name = "shop_brand", nullable = false)
	private String brand;

	@Column(name = "shop_location", nullable = false)
	private String location;

	@Column(name = "shop_phone", nullable = false)
	private String phone;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "merchant")
	private Merchant merchant;


	@OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@JoinColumn(name = "businessHour")
	private BusinessHour businessHour;

	@OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@JoinColumn(name = "shopDelivery")
	private ShopDelivery shopDelivery;

	@OneToMany(mappedBy = "shop")
	private List<Menu> menuList = new ArrayList<>();

	// @OneToOne(mappedBy = "businessHour")
	// private BusinessHour businessHour;
	//
	// @OneToOne(mappedBy = "shopDelivery")
	// private ShopDelivery shopDelivery;

	public Shop() {
	}

	@Builder
	public Shop(Long shopId, String category, String brand, String location, String phone,
		Merchant merchant, BusinessHour businessHour, ShopDelivery shopDelivery,
		List<Menu> menuList) {
		this.shopId = shopId;
		this.category = category;
		this.brand = brand;
		this.location = location;
		this.phone = phone;
		this.merchant = merchant;
		this.businessHour = businessHour;
		this.shopDelivery = shopDelivery;
		this.menuList = menuList;
	}

	public void setMerchant(Merchant merchant) {
		this.merchant = merchant;
	}

	public void setBusinessHour(BusinessHour businessHour) {
		this.businessHour = businessHour;
	}

	public void setShopDelivery(ShopDelivery shopDelivery) {
		this.shopDelivery = shopDelivery;
	}

	public void setMenuList(List<Menu> menuList) {
		this.menuList = menuList;
	}
}
