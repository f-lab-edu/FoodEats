package com.flab.foodeats.login.Controller;


import com.flab.foodeats.login.mapper.UserProfileMapper;
import com.flab.foodeats.login.model.UserProfile;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
public class UserProfileController {

    //Mapper 인터페이스
    private UserProfileMapper mapper;

    //Mapper 인터페이스 생성자
    public UserProfileController(UserProfileMapper mapper) {
        this.mapper = mapper;
    }

    // id를 이용한 조회
    @GetMapping("/user/{id}")
    public UserProfile getUserProfile(@PathVariable("id") String id) {
        return mapper.getUserProfile(id);
    }

    // 전체 조회
    @GetMapping("/user/all")
    public List<UserProfile> getUserProfileList() {
        return mapper.getUserProfileList();
    }

    //로그인
    @GetMapping("/user/login")
    public void login(@RequestParam("userId") String userId, @RequestParam("userName") String userName, HttpServletRequest request, HttpServletResponse response) {

        //login lo = new login;
        //lo.test(userId, userName);

        //requset의 getSession()메서드는 서버에 생성된 세션이 있다면 세션을 반환하고, 없다면 세 세션을 생성하여 반환한다.
        HttpSession session = request.getSession();

        //ID가 있다면
        try{
            // ID와 비밀번호 일치하는지
            if(userName.equals(mapper.getUserProfile(userId).getName())){
                System.out.println("로그인 성공!");
                session.setAttribute(userId,mapper.getUserProfile(userId).getId());

            }
            // ID와 비밀번호 일치X
            else{
                System.out.println("로그인 실패!");
            }
        }
        // ID없음
        catch (Exception e){
            System.out.println("id 없음!");
        }
        finally {
            System.out.println("session" + session.getAttribute(userId));
            System.out.println("session" + session.getAttribute(userName));
        }

    }

    //회원가입
    @PutMapping("/user/{id}")
    public void putUserProfile(@PathVariable("id") String id, @RequestParam("name") String name, @RequestParam("phone") String phone, @RequestParam("address") String address) {
        mapper.insertUserProfile(id, name, phone, address);
    }

    // 회원 수정
    @PostMapping("/user/{id}")
    public void postUserProfile(@PathVariable("id") String id, @RequestParam("name") String name, @RequestParam("phone") String phone, @RequestParam("address") String address) {
        mapper.updateUserProfile(id, name, phone, address);
    }

    // 회원 삭제
    @DeleteMapping("/user/{id}")
    public void deleteUserProfile(@PathVariable("id") String id) {
        mapper.deleteUserProfile(id);
    }





}