package com.driver;

public class Order {

    private String id;
    private int deliveryTime;

    public Order() {

        // The deliveryTime has to converted from string to int and then stored in the attribute
        //deliveryTime  = HH*60 + MM
    }

    public Order(String id, String deliveryTime) {
        this.id = id;
        this.deliveryTime = convert(deliveryTime);
    }

    private int convert(String deliveryTime) {
        String hh = deliveryTime.substring(0 , 2);
        String mm = deliveryTime.substring(2 , 4);
        return Integer.parseInt(hh) * 60 + Integer.parseInt(mm);
    }


    public String getId() {
        return id;
    }

    public int getDeliveryTime() {return deliveryTime;}
}
