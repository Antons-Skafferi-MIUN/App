package com.example.myapplication.API.model;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.ArrayList;

@Root(name = "orderRowss")
public class OrderRows {

    @ElementList(inline = true)
    private ArrayList<OrderRow> orderRows;

    public ArrayList<OrderRow> getOrderRows() {
        return orderRows;
    }

    public void setOrderRows(ArrayList<OrderRow> orderRows) {
        this.orderRows = orderRows;
    }

    @Override
    public String toString() {
        return "ClassPojo [orderRows = " + orderRows + "]";
    }
}
