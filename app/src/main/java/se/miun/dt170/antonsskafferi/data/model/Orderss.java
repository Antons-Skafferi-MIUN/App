package se.miun.dt170.antonsskafferi.data.model;

public class Orderss {
    private Orders[] orders;

    public Orders[] getOrders() {
        return orders;
    }

    public void setOrders(Orders[] orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "ClassPojo [orders = " + orders + "]";
    }
}
