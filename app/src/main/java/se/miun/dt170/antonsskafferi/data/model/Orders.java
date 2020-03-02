package se.miun.dt170.antonsskafferi.data.model;

public class Orders {
    private String orderTime;

    private String orderId;

    private RestaurantTables tableId;

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

    public RestaurantTables getTableId() {
        return tableId;
    }

    public void setTableId(RestaurantTables tableId) {
        this.tableId = tableId;
    }

    @Override
    public String toString() {
        return "ClassPojo [orderTime = " + orderTime + ", orderId = " + orderId + ", tableId = " + tableId + "]";
    }
}
