package com.driver;

public class Order {

    private String id;
    private int deliveryTime;

//    public Order() {
//
//        // The deliveryTime has to converted from string to int and then stored in the attribute
//        //deliveryTime  = HH*60 + MM
//    }

    public Order(String id, String deliveryTime) {
        this.id = id;
        String time[] = deliveryTime.split(":");
        String hour = time[0];
        String minute = time[1];
        this.deliveryTime = Integer.parseInt(hour) * 60 + Integer.parseInt(minute);
    }


    public String getId() {
        return id;
    }

    public int getDeliveryTime() {
        return deliveryTime;
    }
}
