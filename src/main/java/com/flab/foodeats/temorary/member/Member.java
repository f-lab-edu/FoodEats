package com.flab.foodeats.temorary.member;

import com.flab.foodeats.temorary.member.grade.Grade;

/**
 * 자바 기반 코드 수정 (리뷰 반영)
 * @author eunsoo
 */

public class Member {
    private String id;
    private String pass;
    private Grade grade;
    private int totalPrice;

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Member(String id, String pass, Grade grade) {
        this.id = id;
        this.pass = pass;
        this.grade = grade;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "Member{" +
                "id='" + id + '\'' +
                ", pass='" + pass + '\'' +
                ", grade=" + grade +
                '}';
    }
}
