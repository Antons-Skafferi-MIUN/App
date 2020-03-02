package se.miun.dt170.antonsskafferi.data.model;

public class OrderRowss {
    private OrderRows[] orderRows;

    public OrderRows[] getOrderRows() {
        return orderRows;
    }

    public void setOrderRows(OrderRows[] orderRows) {
        this.orderRows = orderRows;
    }

    @Override
    public String toString() {
        return "ClassPojo [orderRows = " + orderRows + "]";
    }
}
