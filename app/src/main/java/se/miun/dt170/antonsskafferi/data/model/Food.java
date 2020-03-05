package se.miun.dt170.antonsskafferi.data.model;

import org.jetbrains.annotations.NotNull;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "foods")
public class Food {

    @Element(name = "foodId")
    private String foodId;

    @Element(name = "foodPrice", required = false)
    private String foodPrice;

    @Element(name = "foodName", required = false)
    private String foodName;

    @Element(name = "foodCategory", required = false)
    private String foodCategory;

    /**
     * Only used for serialization of XML to object for Retrofit!
     */
    public Food() {
    }

    /**
     * Use this constructor when you're doing a POST request.
     *
     * @param foodId
     */
    public Food(@NotNull String foodId) {
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
