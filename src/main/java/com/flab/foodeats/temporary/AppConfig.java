package com.flab.foodeats.temporary;


import com.flab.foodeats.temporary.discount.DiscountPolicy;
import com.flab.foodeats.temporary.discount.RateDiscountPolicy;
import com.flab.foodeats.temporary.member.MemberRepository;
import com.flab.foodeats.temporary.member.MemberService;
import com.flab.foodeats.temporary.member.MemberServiceImpl;
import com.flab.foodeats.temporary.member.MemoryMemberRepository;
import com.flab.foodeats.temporary.order.OrderRepository;
import com.flab.foodeats.temporary.order.OrderRepositoryImpl;
import com.flab.foodeats.temporary.order.OrderService;
import com.flab.foodeats.temporary.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
@Configuration
public class AppConfig {

    // AppConfig  --->  memberService ---> MemverRepository
    // 현재 new를 통한 객체에서 반복이 있다.
    // new MemoryMemberRepository()
    // 역할이 잘 드러나지 않는다.
    // 정답 : 리팩토링! 메소드 추출!

    /*
    public MemberService memberService(){
        return new MemberServiceImpl(new MemoryMemberRepository());
    }

    public OrderService orderService(){
        return new OrderServiceImpl(
                new MemoryMemberRepository(),
                new RateDiscountPolicy(),
                new OrderRepositoryImpl()
        );
    }
*/

    // IoC를 구하기 위한 코드
    // 생성자  //의존성 역전
    // 외부 주입

    @Bean
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


    // 리팩토링을 위한 코드
    @Bean
    public MemberRepository memberRepository(){
        return new MemoryMemberRepository();
    }
    @Bean
    public DiscountPolicy discountPolicy(){
        return new RateDiscountPolicy();
    }
    @Bean
    public OrderRepository orderRepository(){
        return new OrderRepositoryImpl();
    }

}
