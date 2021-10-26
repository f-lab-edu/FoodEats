package com.flab.foodeats.domain.menu;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.flab.foodeats.domain.shop.Shop;
import com.flab.foodeats.domain.user.Merchant;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Table(name = "menu")
@Entity
//@ToString
public class Menu {

	@Id
	@Column(name = "menu_no")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long menuId;

	@Column(name = "shop_no", nullable = false)
	private Long shopId;

	@Column(name = "menu_name", nullable = false)
	private String menuName;

	@Column(name = "menu_price", nullable = false)
	private int menuPrice;

	@Column(name = "menu_configuration", nullable = false)
	private String menuConfiguration;

	@Column(name = "menu_explation", nullable = false)
	private String menuExplanation;

	@Column(name = "menu_group", nullable = false)
	private String menuGroup;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "shop")
	private Shop shop;

	@OneToMany(mappedBy = "menu")
	private List<MenuOption> menuOptions = new ArrayList<>();

	public Menu() {
	}

	@Builder
	public Menu(Long menuId, Long shopId, String menuName, int menuPrice, String menuConfiguration,
		String menuExplanation, String menuGroup, Shop shop) {
		this.menuId = menuId;
		this.shopId = shopId;
		this.menuName = menuName;
		this.menuPrice = menuPrice;
		this.menuConfiguration = menuConfiguration;
		this.menuExplanation = menuExplanation;
		this.menuGroup = menuGroup;
		this.shop = shop;
	}

	public void setShop(Shop shop) {
		this.shop = shop;
	}
}
