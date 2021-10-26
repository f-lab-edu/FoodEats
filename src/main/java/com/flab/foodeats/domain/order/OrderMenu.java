package com.flab.foodeats.domain.order;

import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode  // 임베디드 값 타입 , 반드시 추가, 불변
@Embeddable
public class OrderMenu {

	private long menuNo;
	private String menuName;
	private int menuPrice;

	private long menuOptionNo1;
	private String menuOptionName1;
	private int menuOptionPrice1;

	private long menuOptionNo2;
	private String menuOptionName2;
	private int menuOptionPrice2;
}
