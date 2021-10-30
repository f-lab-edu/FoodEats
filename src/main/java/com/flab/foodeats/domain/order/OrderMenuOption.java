package com.flab.foodeats.domain.order;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Table(name = "order_menu_option")
@Entity
@NoArgsConstructor
// @ToString
@AllArgsConstructor
@Builder
public class OrderMenuOption {

	@Id
	@Column(name = "order_menu_option_no")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long menuOptionOrderNo;

	@Column(name = "menu_option_no")
	private long menuOptionNo;
	@Column(name = "menu_option_name")
	private String menuOptionName;
	@Column(name = "menu_option_price")
	private int menuOptionPrice;


	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "orderMenu_no")
	private OrderMenu orderMenu;

	public void setOrderMenu(OrderMenu orderMenu) {
		this.orderMenu = orderMenu;
	}
}
