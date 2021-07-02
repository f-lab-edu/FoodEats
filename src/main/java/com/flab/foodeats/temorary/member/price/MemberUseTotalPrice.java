package com.flab.foodeats.temorary.member.price;

import com.flab.foodeats.temorary.order.Order;

public interface MemberUseTotalPrice {
    //사용자 총 사용금액(주문x) DB
    Order UsePriceSave(Order order);
}
