package com.example.myapplication.API.model;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "orderRows")
public class OrderRow {

    @Element(name = "orderRowId", required = false)
    private String orderRowId; // auto-increment, don't specify this in constructor!

    @Element(name = "orderId")
    private Order orderId;

    @Element(name = "drinkId", required = false)
    private Drink drinkId;

    @Element(name = "foodId", required = false)
    private Food foodId;

    @Element(name = "orderChange", required = false)
    private String orderChange;

    /**
     * Only used for serialization of XML to object for Retrofit!
     */
    public OrderRow() {
    }

    /**
     * Use this constructor when you're doing a POST request.
     *
     * @param orderId     a {@link Order} with only it's ID in it's constructor
     * @param drinkId     a {@link Drink} with only it's ID in it's constructor
     * @param foodId      a {@link Food} with only it's ID in it's constructor
     * @param orderChange a string of changes to the order
     */
    public OrderRow(@NotNull Order orderId, @Nullable Drink drinkId, @Nullable Food foodId, @Nullable String orderChange) {
        this.orderId = orderId;
        this.drinkId = drinkId;
        this.foodId = foodId;
        this.orderChange = orderChange;
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
