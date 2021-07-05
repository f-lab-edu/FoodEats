package com.flab.foodeats.temorary;


// 자바를 이용해서 빈을 등록하고 싶을때! 사용하는 설정 클래스
//@Configuration
//@Component
public class AppConfig {

   /* @Bean
    public MemberService memberService(){
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public OrderService orderService(){
        return new OrderServiceImpl(
                memberRepository(),
                discountPolicy(),
                orderRepository()
        );
    }


    // member 패키지 클래스 (자바로 빈 등록)
    @Bean
    public MemberRepository memberRepository(){
        return new MemoryMemberRepository();
    }

    // discount 패키지 클래스 (자바로 빈 등록)
    @Bean
    public DiscountPolicy discountPolicy(){
        return new RateDiscountPolicy();
    }

    // order 패키지 클래스 (자바로 빈 등록)
    @Bean
    public OrderRepository orderRepository(){
        return new OrderRepositoryImpl(orderGrade());
    }
    @Bean
    public OrderGrade orderGrade(){
        return new OrderGradeImpl();
    }*/

}
