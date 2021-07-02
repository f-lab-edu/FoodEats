package com.flab.foodeats.temorary;

import com.flab.foodeats.temorary.member.Member;
import com.flab.foodeats.temorary.member.MemberService;
import com.flab.foodeats.temorary.member.grade.Grade;
import com.flab.foodeats.temorary.order.Order;
import com.flab.foodeats.temorary.order.OrderService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

/**
 * 자바 기반 코드 수정 (리뷰 반영)
 * @author eunsoo
 */
@Component
public class OrderApp {
    //psvm
    public static void main(String[] args) {

        //MemberService memberService = new MemberServiceImpl();
        //OrderService orderService = new OrderServiceImpl();


        // IoC를 구현하기 위해
        /*AppConfig appConfig = new AppConfig();
        MemberService memberService = appConfig.memberService();
        OrderService orderService = appConfig.orderService();*/

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AutoAppConfig.class);
        MemberService memberService = applicationContext.getBean( MemberService.class);
        OrderService orderService = applicationContext.getBean( OrderService.class);
        //(이름 , 반환타입)


        // 회원가입
        Member member1 = new Member("jin", "passJin", Grade.BASIC);
        memberService.join(member1);

        Member member2 = new Member("yousock", "passYou", Grade.BASIC);
        memberService.join(member2);


        // jin 로그인 Login
        String jin_id  = "jin";
        String jin_pass  = "passJin";

        String loginStatus = memberService.login(jin_id, jin_pass);
        System.out.println("로그인 성공 : " + loginStatus);



        // 주문 Order
        // jin의 주문 입력
        Order order1 = new Order("jin", "BBQ","치킨" ,25000);
        Order order2 = new Order("jin", "BBQ","콜라" , 20000);
        Order order3 = new Order("jin", "BBQ","무" , 30000);
        Order order4 = new Order("jin", "BBQ","젓가락" , 30000);


        // 주문 생성
        System.out.println();
        orderService.createOrder(order1);
        System.out.println();
        System.out.println();
        orderService.createOrder(order2);
        System.out.println();
        System.out.println();
        orderService.createOrder(order3);
        System.out.println();
        System.out.println();
        orderService.createOrder(order4);
        System.out.println();

        System.out.println();





    }
}
