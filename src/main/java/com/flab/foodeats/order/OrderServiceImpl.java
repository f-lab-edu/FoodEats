package com.flab.foodeats.order;


import com.flab.foodeats.discount.DiscountPolicy;
import com.flab.foodeats.member.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceImpl implements OrderService {

    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;
    private final OrderRepository orderRepository;

    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy, OrderRepository orderRepository) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
        this.orderRepository = orderRepository;
    }

    // IoC 구현


    @Override
    public Order createOrder(Order order) {

        System.out.println("등급 조정 전  " + order);

        // 할인 정책
        System.out.println("할인 안된 금액 :" + order.getItemPrice());
        order.setItemPrice(discountPolicy.discount(order.getMember(), order.getItemPrice()));
        System.out.println("할인된 금액 :" + order.getItemPrice());

        order = orderRepository.OrderSave(order);


        return order;
    }





}
