package se.miun.dt170.antonsskafferi.data.model;

public class OrderRows {
    private Drink drinkId;

    private Orders orderId;

    private Food foodId;

    private String orderRowId;

    public Drink getDrinkId() {
        return drinkId;
    }

    public void setDrinkId(Drink drinkId) {
        this.drinkId = drinkId;
    }

    public Orders getOrderId() {
        return orderId;
    }

    public void setOrderId(Orders orderId) {
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
