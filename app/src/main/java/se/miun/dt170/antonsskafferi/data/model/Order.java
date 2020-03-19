package se.miun.dt170.antonsskafferi.data.model;

import org.jetbrains.annotations.NotNull;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import se.miun.dt170.antonsskafferi.data.utility.DateConverter;

@Root(name = "orders")
public class Order {

    @Element(name = "orderId", required = false)
    private String orderId; // auto-increment, don't specify this in constructor!

    @Element(name = "tableId")
    private RestaurantTable tableId;

    @Element(name = "orderTime")
    private String orderTime;
    
    @Element(name = "orderIsDone", required = false)
    private String orderIsDone;

    /**
     * Only used for serialization of XML to object for Retrofit!
     */
    public Order() {
    }

    /**
     * Use this constructor when you're doing a POST request.
     *
     * @param tableId   a {@link RestaurantTable} with only an ID as it's constructor
     * @param orderTime use {@link DateConverter} getCurrentTime() formatted as ISO-8601
     */
    public Order(@NotNull RestaurantTable tableId, @NotNull String orderTime) {
        this.tableId = tableId;
        this.orderTime = orderTime;
        this.orderIsDone = "false";
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


    public String getOrderIsDone() {
        return orderIsDone;
    }

    public void setOrderIsDone(String orderIsDone) {
        this.orderIsDone = orderIsDone;
    }

    @Override
    public String toString() {
        return "ClassPojo [orderTime = " + orderTime + ", orderId = " + orderId + ", tableId = " + tableId + ", orderIsDone = " + orderIsDone + "]";
    }
}
