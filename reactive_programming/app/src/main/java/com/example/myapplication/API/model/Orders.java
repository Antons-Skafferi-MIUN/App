package com.example.myapplication.API.model;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.ArrayList;

@Root(name = "orderss")
public class Orders {

    @ElementList(inline = true)
    private ArrayList<Order> orders;

    public ArrayList<Order> getOrders() {
        return orders;
    }

    public void setOrders(ArrayList<Order> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "ClassPojo [orders = " + orders + "]";
    }
}
