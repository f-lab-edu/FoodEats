package com.flab.foodeats.domain.order;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.flab.foodeats.api.order.RegisterOrderRequest;
import com.flab.foodeats.domain.user.Consumer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Table(name = "order_menu")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderMenu {

	@Id
	@Column(name = "order_menu_no")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long menuOrderNo;

	@Column(name = "menu_no")
	private long menuNo;
	@Column(name = "menu_name")
	private String menuName;
	@Column(name = "menu_price")
	private int menuPrice;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "order_no")
	private Order order;

	@OneToMany(mappedBy = "orderMenu")
	private List<OrderMenuOption> orderMenuOptionList = new ArrayList<>();

	public void setOrder(Order order) {
		this.order = order;
	}
}