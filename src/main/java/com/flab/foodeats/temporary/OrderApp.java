package com.flab.foodeats.temporary;


import com.flab.foodeats.temporary.member.Grade;
import com.flab.foodeats.temporary.member.Member;
import com.flab.foodeats.temporary.member.MemberService;
import com.flab.foodeats.temporary.order.Order;
import com.flab.foodeats.temporary.order.OrderService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

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

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);
        OrderService orderService = applicationContext.getBean("orderService", OrderService.class);
        //(이름 , 반환타입)


        // 회원가입
        Member member1 = new Member("jin", "passJin", Grade.BASIC);
        memberService.join(member1);

        Member member2 = new Member("yousock", "passYou", Grade.BASIC);
        memberService.join(member2);


        // jin 로그인 Login
        String jin_id  = "jin";
        String jin_pass  = "passJin";

        Member jin_login_member = memberService.login(jin_id, jin_pass);
        System.out.println("로그인 성공 : " + jin_login_member);


        // YOU 로그인 Login
       /* String you_id  = "you";
        String you_pass  = "passYou";

        Member you_login_member = memberService.login(you_id, you_pass);
        System.out.println("로그인 성공 : " + you_login_member);*/



        // 주문 Order
        // jin의 주문 입력
        Order order1 = new Order(jin_login_member, "BBQ", 25000);
        Order order2 = new Order(jin_login_member, "교촌", 20000);
        Order order3 = new Order(jin_login_member, "굽네", 30000);
        Order order4 = new Order(jin_login_member, "굽네", 30000);


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


        /*
        Order order1 = orderService.createOrder(jin_login_member, "BBQ", 40000,1000);
        Order order2 = orderService.createOrder(jin_login_member, "교촌", 40000,1000);
        Order order3 = orderService.createOrder(jin_login_member, "지코바", 40000,1000);*/

        /*// yousock의 주문
        Order order4 = orderService.createOrder(you_login_member, "BBQ", 10000,1000);
        Order order5 = orderService.createOrder(you_login_member, "교촌", 10000,1000);
        Order order6 = orderService.createOrder(you_login_member, "지코바", 10000,1000);
*/
        //System.out.println(order3);


    }
}
