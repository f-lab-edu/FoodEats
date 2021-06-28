package com.flab.foodeats.member;

public class Member {
    private String id;
    private String pass;
    private Grade grade;


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
