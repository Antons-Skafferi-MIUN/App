package se.miun.dt170.antonsskafferi.data.model;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "drinks")
public class Drink {

    @Element(name = "drinkId")
    private String drinkId;

    @Element(name = "drinkType")
    private String drinkType;

    @Element(name = "drinkPrice")
    private String drinkPrice;

    @Element(name = "drinkCategory")
    private String drinkCategory;

    @Element(name = "drinkName")
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
