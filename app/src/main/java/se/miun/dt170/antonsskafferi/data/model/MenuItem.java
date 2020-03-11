package se.miun.dt170.antonsskafferi.data.model;

public interface MenuItem {
    String getName();
    String getPrice();
    String getCategory();
    String getId();
    String getTypeOfMenuItem();
    String getOrderChange();
    void setOrderChanged(String orderChanged);
}
