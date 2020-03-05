package se.miun.dt170.antonsskafferi.data.model;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "foods")
public class Food {

    @Element(name = "foodPrice", required = false)
    private String foodPrice;

    @Element(name = "foodName", required = false)
    private String foodName;

    @Element(name = "foodId")
    private String foodId;

    @Element(name = "foodCategory", required = false)
    private String foodCategory;

    public Food() {
    }

    public Food(String foodId) {
        this.foodId = foodId;
    }

    public String getFoodPrice() {
        return foodPrice;
    }

    public void setFoodPrice(String foodPrice) {
        this.foodPrice = foodPrice;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getFoodId() {
        return foodId;
    }

    public void setFoodId(String foodId) {
        this.foodId = foodId;
    }

    public String getFoodCategory() {
        return foodCategory;
    }

    public void setFoodCategory(String foodCategory) {
        this.foodCategory = foodCategory;
    }

    @Override
    public String toString() {
        return "ClassPojo [foodPrice = " + foodPrice + ", foodName = " + foodName + ", foodId = " + foodId + ", foodCategory = " + foodCategory + "]";
    }
}
