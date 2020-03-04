package se.miun.dt170.antonsskafferi.data.model;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "orderRows")
public class OrderRow {

    @Element(name = "drinkId", required = false)
    private Drink drinkId;

    @Element(name = "orderId")
    private Order orderId;

    @Element(name = "foodId", required = false)
    private Food foodId;

    @Element(name = "orderRowId", required = false)
    private String orderRowId; // auto-increment

    @Element(name = "orderChange", required = false)
    private String orderChange;

    public OrderRow() {
    }

    /**
     * @param drinkId
     * @param orderId
     * @param foodId
     * @param orderChange
     */
    public OrderRow(Drink drinkId, Order orderId, Food foodId, String orderChange) {
        this.drinkId = drinkId;
        this.orderId = orderId;
        this.foodId = foodId;
        this.orderChange = orderChange;
    }

    public OrderRow(Drink drinkId, Order orderId, Food foodId) {
        this.drinkId = drinkId;
        this.orderId = orderId;
        this.foodId = foodId;
    }

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

    public String getOrderChange() {
        return orderChange;
    }

    public void getOrderChange(String orderChange) {
        this.orderChange = orderChange;
    }

    @Override
    public String toString() {
        return "ClassPojo [drinkId = " + drinkId + ", orderId = " + orderId + ", foodId = " + foodId + ", orderRowId = " + orderRowId + ", orderRowId = " + orderChange + "]";
    }
}
