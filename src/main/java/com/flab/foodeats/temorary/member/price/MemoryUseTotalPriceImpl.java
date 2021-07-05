package com.flab.foodeats.temorary.member.price;

import com.flab.foodeats.temorary.member.MemberInfo;
import com.flab.foodeats.temorary.member.grade.SetGrade;
import com.flab.foodeats.temorary.order.Order;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * 자바 기반 코드 수정 (리뷰 반영)
 * @author eunsoo
 */

@Component
public class MemoryUseTotalPriceImpl implements MemberUseTotalPrice {
    private static Map<String, Integer> storeOrder = new HashMap<>();
    private final SetGrade orderGrade;
    private final MemberInfo memberRepository;

    public MemoryUseTotalPriceImpl(SetGrade orderGrade, MemberInfo memberRepository) {
        this.orderGrade = orderGrade;
        this.memberRepository = memberRepository;
    }

    @Override
    public Order UsePriceSave(Order order) {

        // 총 주문가격 구현 // hashMap으로 구현
        storeOrder.put(order.getId(),storeOrder.getOrDefault(order.getId(),0) + order.getItemPrice());

        int usetotalPrice = storeOrder.get(order.getId());
        System.out.println("누적된 금액 : " + usetotalPrice);

        // 주문가격에 따른 등급 조정
        orderGrade.gradeSet(memberRepository.find(order.getId()), usetotalPrice);
        System.out.println("등급 조정 후  " + memberRepository.find(order.getId() ).getGrade());

        return order;
    }

}
