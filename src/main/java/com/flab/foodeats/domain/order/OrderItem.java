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

import com.flab.foodeats.domain.shop.Shop;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@Table(name = "order_item")
@Entity
@NoArgsConstructor
@ToString
@AllArgsConstructor
@Builder
public class OrderItem {

	@Id
	@Column(name = "order_item_no")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long orderItemNo;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "order_no")
	private Order order;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "shop_no")
	private Shop shopInfo;


	@Column(name = "total_price")
	private int totalPrice;




}
