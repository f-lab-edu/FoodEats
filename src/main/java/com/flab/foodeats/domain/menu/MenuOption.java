package com.flab.foodeats.domain.menu;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.flab.foodeats.domain.user.Merchant;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Table(name = "menu_option")
@Entity
@ToString
public class MenuOption {

	@Id
	@Column(name = "menu_option_no")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long menuOptionNo;

	@Column(name = "menu_id", nullable = false)
	private Long menuId;

	@Column(name = "menu_option_name", nullable = false)
	private String menuOptionName;

	@Column(name = "menu_option_price", nullable = false)
	private Integer menuOptionPrice;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "menu")
	private Menu menu;

	public MenuOption() {
	}

	@Builder
	public MenuOption(Long menuOptionNo, long menuId, String menuOptionName, int menuOptionPrice,
		Menu menu) {
		this.menuOptionNo = menuOptionNo;
		this.menuId = menuId;
		this.menuOptionName = menuOptionName;
		this.menuOptionPrice = menuOptionPrice;
		this.menu = menu;
	}

	public void setMenuOptionName(String menuOptionName) {
		this.menuOptionName = menuOptionName;
	}

	public void setMenuOptionPrice(Integer menuOptionPrice) {
		this.menuOptionPrice = menuOptionPrice;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}
}
