package com.flab.foodeats.domain.order;

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
@AllArgsConstructor
@Builder
public class Order {

	@Id
	@Column(name = "order_no")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long orderNo;

	// EAGER 오류
	// https://www.google.com/search?q=Type+definition+error%3A+%5Bsimple+type%2C+class+org.hibernate.proxy.pojo.bytebuddy.ByteBuddyInterceptor%5D%3B+nested+exception+is+com.fasterxml.jackson.databind.exc.InvalidDefinitionException%3A+No+serializer+found+for+class+org.hibernate.proxy.pojo.bytebuddy.ByteBuddyInterceptor+and+no+properties+discovered+to+create+BeanSerializer+(to+avoid+exception%2C+disable+SerializationFeature.FAIL_ON_EMPTY_BEANS)+(through+reference+chain%3A+com.flab.foodeats.common.response.ApiResponse%5B%5C%22data%5C%22%5D-%3Ecom.flab.foodeats.api.order.FindAllOrderResponse%5B%5C%22orderList%5C%22%5D-%3Ejava.util.ArrayList%5B0%5D-%3Ecom.flab.foodeats.domain.order.Order%5B%5C%22merchantInfo%5C%22%5D-%3Ecom.flab.foodeats.domain.user.Merchant%24HibernateProxy%24ukqfm0Y8%5B%5C%22hibernateLazyInitializer%5C%22%5D)&oq=Type+definition+error%3A+%5Bsimple+type%2C+class+org.hibernate.proxy.pojo.bytebuddy.ByteBuddyInterceptor%5D%3B+nested+exception+is+com.fasterxml.jackson.databind.exc.InvalidDefinitionException%3A+No+serializer+found+for+class+org.hibernate.proxy.pojo.bytebuddy.ByteBuddyInterceptor+and+no+properties+discovered+to+create+BeanSerializer+(to+avoid+exception%2C+disable+SerializationFeature.FAIL_ON_EMPTY_BEANS)+(through+reference+chain%3A+com.flab.foodeats.common.response.ApiResponse%5B%5C%22data%5C%22%5D-%3Ecom.flab.foodeats.api.order.FindAllOrderResponse%5B%5C%22orderList%5C%22%5D-%3Ejava.util.ArrayList%5B0%5D-%3Ecom.flab.foodeats.domain.order.Order%5B%5C%22merchantInfo%5C%22%5D-%3Ecom.flab.foodeats.domain.user.Merchant%24HibernateProxy%24ukqfm0Y8%5B%5C%22hibernateLazyInitializer%5C%22%5D)&aqs=chrome..69i57.3604j0j4&sourceid=chrome&ie=UTF-8


	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "consumer_info")
	private Consumer consumerInfo;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "shop_no")
	private Shop shopInfo;

	@Embedded
	@AttributeOverride(name = "menuNo", column = @Column(name = "first_munu_no", nullable = false))
	@AttributeOverride(name = "menuName", column = @Column(name = "first_munu_name", nullable = false))
	@AttributeOverride(name = "menuPrice", column = @Column(name = "first_munu_price", nullable = false))
	@AttributeOverride(name = "menuOptionNo1", column = @Column(name = "first_option_no1"))
	@AttributeOverride(name = "menuOptionName1", column = @Column(name = "first_option_name1"))
	@AttributeOverride(name = "menuOptionPrice1", column = @Column(name = "first_option_price1"))
	@AttributeOverride(name = "menuOptionNo2", column = @Column(name = "first_option_no2"))
	@AttributeOverride(name = "menuOptionName2", column = @Column(name = "first_option_name2"))
	@AttributeOverride(name = "menuOptionPrice2", column = @Column(name = "first_option_price2"))
	private OrderMenu orderMenu;

	@Embedded
	@AttributeOverride(name = "menuNo", column = @Column(name = "second_munu_no"))
	@AttributeOverride(name = "menuName", column = @Column(name = "second_munu_name"))
	@AttributeOverride(name = "menuPrice", column = @Column(name = "second_munu_price"))
	@AttributeOverride(name = "menuOptionNo1", column = @Column(name = "second_option_no1"))
	@AttributeOverride(name = "menuOptionName1", column = @Column(name = "second_option_name1"))
	@AttributeOverride(name = "menuOptionPrice1", column = @Column(name = "second_option_price1"))
	@AttributeOverride(name = "menuOptionNo2", column = @Column(name = "second_option_no2"))
	@AttributeOverride(name = "menuOptionName2", column = @Column(name = "second_option_name2"))
	@AttributeOverride(name = "menuOptionPrice2", column = @Column(name = "second_option_price2"))
	private OrderMenu orderMenu1;

	@Column(name = "total_price")
	private int totalPrice;

	@Column(name = "order_status")
	@ColumnDefault("true")
	private String orderStatus;

	// @DynamicInsert vs @PrePersist
	// https://dotoridev.tistory.com/6
	@PrePersist
	public void prePersist() {
		orderStatus = orderStatus == null ? "true" : orderStatus;
	}


	public void changeOrderMenu(OrderMenu orderMenu) {
		this.orderMenu = orderMenu;
	}

	public void changeOrderMenu1(OrderMenu orderMenu1) {
		this.orderMenu1 = orderMenu1;
	}

	public void changeTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public void setConsumerInfo(Consumer consumerInfo) {
		this.consumerInfo = consumerInfo;
	}

	public void setShopInfo(Shop shopInfo) {
		this.shopInfo = shopInfo;
	}
}
