package se.miun.dt170.antonsskafferi.data.model;

public interface MenuItem {
    String getName();
    String getPrice();
    String getCategory();
    String getId();
    String getTypeOfMenuItem();
    String getOrderChange();
    void setId(String id);
    void setOrderChanged(String orderChanged);
    void setIdChanged(int idChanged);
    int getIdChanged();
}
