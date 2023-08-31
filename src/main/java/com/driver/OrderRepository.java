package com.driver;
import java.util.*;

import io.swagger.models.auth.In;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public class OrderRepository {
    HashMap<String , Order> orderMap;
    HashMap<String , DeliveryPartner> partnerMap;
    HashMap<String , List<String>> partnerOrderMap;
    HashMap<String , String> orderPartnerMap;

    public OrderRepository() {
         orderMap = new HashMap<>();
         partnerMap = new HashMap<>();
         partnerOrderMap = new HashMap<>();
         orderPartnerMap = new HashMap<>();
    }


    public void addOrder(Order order) {
        orderMap.put(order.getId() , order);
    }

    public Order getOrderId(String orderId) {
        if(orderMap.containsKey(orderId))return orderMap.get(orderId);
        return null;
    }

    public void addPartner(String partnerId) {
        DeliveryPartner deliveryPartner = new DeliveryPartner(partnerId);
        partnerMap.put(deliveryPartner.getId() , deliveryPartner);
    }

    public void addOrderPartnerPair(String orderId, String partnerId) {
        DeliveryPartner deliveryPartner = partnerMap.get(partnerId);

        if(partnerOrderMap.containsKey(partnerId)){
            List<String> orders = partnerOrderMap.get(partnerId);
            orders.add(orderId);
            deliveryPartner.setNumberOfOrders(orders.size());
        }else{
            List<String> orders = new ArrayList<>();
            partnerOrderMap.put(partnerId , orders);
            deliveryPartner.setNumberOfOrders(1);
        }
        orderPartnerMap.put(orderId , partnerId);
    }

    public DeliveryPartner getPartnerById(String partnerId) {
        if(partnerMap.containsKey(partnerId))return partnerMap.get(partnerId);
        return null;
    }

    public Integer getOrderCountByPartnerId(String partnerId) {
        if(partnerOrderMap.containsKey(partnerId))return partnerOrderMap.get(partnerId).size();
        return 0;
    }

    public List<String> getOrdersByPartnerId(String partnerId) {
        if(partnerOrderMap.containsKey(partnerId))return partnerOrderMap.get(partnerId);
        return null;
    }

    public List<String> getAllOrders() {
        List<String> list = new ArrayList<>();
        for(String key : orderMap.keySet()){
            list.add(key);
        }
        return list;
    }


    public Integer getCountOfUnassignedOrders() {
        return orderMap.size() - orderPartnerMap.size();
    }

    public Integer getOrdersLeftAfterGivenTimeByPartnerId(String time, String partnerId) {
        int i = 0;
        for(String oId : partnerOrderMap.get(partnerId)){
            int t = orderMap.get(oId).getDeliveryTime();
            if(t >= Integer.parseInt(time))i++;
        }
        return i;
    }


    public String getLastDeliveryTimeByPartnerId(String partnerId) {
        int max = 0;
        for(String oId : partnerOrderMap.get(partnerId)){
            max = Math.max(orderMap.get(oId).getDeliveryTime() , max);
        }
        return Integer.toString(max);
    }


    public void deletePartnerById(String partnerId) {
        partnerMap.remove(partnerId);
        if(partnerOrderMap.containsKey(partnerId))partnerOrderMap.remove(partnerId);
        for(String oId : orderPartnerMap.keySet()){
            if(orderPartnerMap.get(oId).equals(partnerId))orderPartnerMap.remove(oId);
        }
    }


    public void deleteOrderById(String orderId) {
        orderMap.remove(orderId);
        orderPartnerMap.remove(orderId);
        for(String pId : partnerOrderMap.keySet()){
            List<String> list = partnerOrderMap.get(pId);
            for(int i = 0 ; i < list.size() ; i++){
                if(list.get(i).equals(orderId)){
                    list.remove(i);
                    break;
                }
            }
        }
    }
}
