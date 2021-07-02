package com.flab.foodeats.temorary.order;

public class Order {

    private String id;
    private String storeBrand;
    private String itemName;
    private int itemPrice;

    @Override
    public String toString() {
        return "Order{" +
                "id='" + id + '\'' +
                ", storeBrand='" + storeBrand + '\'' +
                ", itemName='" + itemName + '\'' +
                ", itemPrice=" + itemPrice +
                '}';
    }

    public Order(String id, String storeBrand, String itemName, int itemPrice) {
        this.id = id;
        this.storeBrand = storeBrand;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStoreBrand() {
        return storeBrand;
    }

    public void setStoreBrand(String storeBrand) {
        this.storeBrand = storeBrand;
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
