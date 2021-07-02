package com.flab.foodeats.temporary.order;


import com.flab.foodeats.temporary.member.Member;

public class Order {

    private Member member;
    private String itemName;
    private int itemPrice;

    @Override
    public String toString() {
        return "Order{" +
                "member=" + member +
                ", itemName='" + itemName + '\'' +
                ", itemPrice=" + itemPrice +
                '}';
    }

    public Order(Member member, String itemName, int itemPrice) {
        this.member = member;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(int itemPrice) {
        this.itemPrice = itemPrice;
    }
}
