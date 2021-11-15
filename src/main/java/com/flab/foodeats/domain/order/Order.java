package com.flab.foodeats.domain.order;

import java.security.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;

import com.flab.foodeats.api.order.RegisterOrderRequest;
import com.flab.foodeats.domain.menu.Menu;
import com.flab.foodeats.domain.shop.Shop;
import com.flab.foodeats.domain.user.Consumer;
import com.flab.foodeats.domain.user.Merchant;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
@Getter
@Table(name = "orders")
@Entity
@NoArgsConstructor
@ToString
// @AllArgsConstructor
public class Order{

	@Id
	@Column(name = "order_no")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long orderNo;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "consumer_info")
	private Consumer consumerInfo;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "shop_no")
	private Shop shopInfo;

	@OneToMany(mappedBy = "order")
	private List<OrderMenu> orderMenuList = new ArrayList<>();

	@Column(name = "order_time", nullable = false, updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	public Timestamp orderTime;


	@Column(name = "total_price")
	private int totalPrice;

	@Column(name = "order_status")
	private Boolean orderStatus;

	@Builder
	public Order(Long orderNo, Consumer consumerInfo, Shop shopInfo,
		List<OrderMenu> orderMenuList, Timestamp orderTime, int totalPrice, Boolean orderStatus) {

		this.orderNo = orderNo;
		this.consumerInfo = consumerInfo;
		this.shopInfo = shopInfo;
		this.orderMenuList = orderMenuList;
		this.orderTime = orderTime;
		this.totalPrice = totalPrice;
		this.orderStatus = orderStatus == null ? true : orderStatus;
	}

	// @DynamicInsert vs @PrePersist
	// https://dotoridev.tistory.com/6
	// @PrePersist
	// public void prePersist() {
	// 	orderStatus = orderStatus == null ? "true" : orderStatus;
	// }

	public void setOrderMenuList(List<OrderMenu> orderMenu) {
		this.orderMenuList = orderMenu;
	}

	public void changeTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

	// public void setOrderStatus(Boolean orderStatus) {
	// 	this.orderStatus = orderStatus;
	// }

	public void setOrderStatus(Boolean orderStatus) {
		this.orderStatus = orderStatus;
	}

	public void setConsumerInfo(Consumer consumerInfo) {
		this.consumerInfo = consumerInfo;
	}

	public void setShopInfo(Shop shopInfo) {
		this.shopInfo = shopInfo;
	}
}
