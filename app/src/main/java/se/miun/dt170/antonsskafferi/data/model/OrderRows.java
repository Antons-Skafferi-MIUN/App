package se.miun.dt170.antonsskafferi.data.model;

public class OrderRows {
    private Drink drinkId;

    private Order orderId;

    private Food foodId;

    private String orderRowId;

    public Drink getDrinkId() {
        return drinkId;
    }

    public void setDrinkId(Drink drinkId) {
        this.drinkId = drinkId;
    }

    public Order getOrderId() {
        return orderId;
    }

    public void setOrderId(Order orderId) {
        this.orderId = orderId;
    }

    public Food getFoodId() {
        return foodId;
    }

    public void setFoodId(Food foodId) {
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
