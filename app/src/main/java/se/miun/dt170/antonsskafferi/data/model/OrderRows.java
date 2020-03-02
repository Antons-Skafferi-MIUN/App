package se.miun.dt170.antonsskafferi.data.model;

public class OrderRows {
    private Drinks drinkId;

    private Orders orderId;

    private Foods foodId;

    private String orderRowId;

    public Drinks getDrinkId() {
        return drinkId;
    }

    public void setDrinkId(Drinks drinkId) {
        this.drinkId = drinkId;
    }

    public Orders getOrderId() {
        return orderId;
    }

    public void setOrderId(Orders orderId) {
        this.orderId = orderId;
    }

    public Foods getFoodId() {
        return foodId;
    }

    public void setFoodId(Foods foodId) {
        this.foodId = foodId;
    }

    public String getOrderRowId() {
        return orderRowId;
    }

    public void setOrderRowId(String orderRowId) {
        this.orderRowId = orderRowId;
    }

    @Override
    public String toString() {
        return "ClassPojo [drinkId = " + drinkId + ", orderId = " + orderId + ", foodId = " + foodId + ", orderRowId = " + orderRowId + "]";
    }
}
