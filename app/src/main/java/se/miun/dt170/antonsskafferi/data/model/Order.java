package se.miun.dt170.antonsskafferi.data.model;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "orders")
public class Order {

    @Element(name = "orderTime")
    private String orderTime;

    @Element(name = "orderId", required = false)
    private String orderId; // auto-increment

    @Element(name = "tableId")
    private RestaurantTable tableId;

    public Order() {
    }

    public Order(String orderTime, RestaurantTable tableId) {
        this.orderTime = orderTime;
        this.tableId = tableId;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public RestaurantTable getTableId() {
        return tableId;
    }

    public void setTableId(RestaurantTable tableId) {
        this.tableId = tableId;
    }

    @Override
    public String toString() {
        return "ClassPojo [orderTime = " + orderTime + ", orderId = " + orderId + ", tableId = " + tableId + "]";
    }
}
