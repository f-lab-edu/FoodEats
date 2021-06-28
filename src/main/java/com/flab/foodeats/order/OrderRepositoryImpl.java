package com.flab.foodeats.order;

import java.util.HashMap;
import java.util.Map;

public class OrderRepositoryImpl implements OrderRepository{

    private static Map<String, Integer> storeOrder = new HashMap<>();
    private final OrderGrade orderGrade = new OrderGradeImpl();

    @Override
    public Order OrderSave(Order order) {
        storeOrder.put(order.getMember().getId(),storeOrder.getOrDefault(order.getMember().getId(),0) + order.getItemPrice());
        int sum = storeOrder.get(order.getMember().getId());
        System.out.println("누적된 금액 : " + sum);



        Order orderReSet = orderGrade.gradeSet(order,sum);
        System.out.println("등급 조정 후  " + orderReSet);

        return orderReSet;
    }




}
