package com.flab.foodeats.temorary.order;

import com.flab.foodeats.temorary.discount.DiscountPolicy;
import com.flab.foodeats.temorary.member.MemberInfo;
import com.flab.foodeats.temorary.member.price.MemberUseTotalPrice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * 자바 기반 코드 수정 (리뷰 반영)
 * @author eunsoo
 */

@Component
public class OrderServiceImpl implements OrderService {

    private final MemberInfo memberRepository;
    private final DiscountPolicy discountPolicy;
    private final MemberUseTotalPrice orderRepository;

    @Autowired
    public OrderServiceImpl(MemberInfo memberRepository, @Qualifier("mainDiscountPolicy") DiscountPolicy discountPolicy, MemberUseTotalPrice orderRepository) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
        this.orderRepository = orderRepository;
    }

    // IoC 구현


    @Override
    public Order createOrder(Order order) {

        System.out.println("등급 조정 전  " + memberRepository.find(order.getId()).getGrade());

        // 할인 정책
        System.out.println("할인 안된 금액 :" + order.getItemPrice());

        order.setItemPrice(discountPolicy.discount(memberRepository.find(order.getId()), order.getItemPrice()));
        System.out.println("할인된 금액 :" + order.getItemPrice());

        order = orderRepository.UsePriceSave(order);

        return order;
    }
}
