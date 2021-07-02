package com.flab.foodeats.temorary.member.price;

import com.flab.foodeats.temorary.order.Order;

/**
 * 자바 기반 코드 수정 (리뷰 반영)
 * @author eunsoo
 */

public interface MemberUseTotalPrice {
    //사용자 총 사용금액(주문x) DB
    Order UsePriceSave(Order order);
}
