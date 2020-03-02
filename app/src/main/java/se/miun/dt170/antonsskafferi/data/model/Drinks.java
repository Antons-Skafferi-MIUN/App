package se.miun.dt170.antonsskafferi.data.model;

public class Drinks {
    private String drinkId;

    private String drinkType;

    private String drinkPrice;

    private String drinkCategory;

    private String drinkName;

    public String getDrinkId() {
        return drinkId;
    }

    public void setDrinkId(String drinkId) {
        this.drinkId = drinkId;
    }

    public String getDrinkType() {
        return drinkType;
    }

    public void setDrinkType(String drinkType) {
        this.drinkType = drinkType;
    }

    public String getDrinkPrice() {
        return drinkPrice;
    }

    public void setDrinkPrice(String drinkPrice) {
        this.drinkPrice = drinkPrice;
    }

    public String getDrinkCategory() {
        return drinkCategory;
    }

    public void setDrinkCategory(String drinkCategory) {
        this.drinkCategory = drinkCategory;
    }

    public String getDrinkName() {
        return drinkName;
    }

    public void setDrinkName(String drinkName) {
        this.drinkName = drinkName;
    }

    @Override
    public String toString() {
        return "ClassPojo [drinkId = " + drinkId + ", drinkType = " + drinkType + ", drinkPrice = " + drinkPrice + ", drinkCategory = " + drinkCategory + ", drinkName = " + drinkName + "]";
    }
}
