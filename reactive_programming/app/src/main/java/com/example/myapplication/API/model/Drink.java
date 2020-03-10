package com.example.myapplication.API.model;

import org.jetbrains.annotations.NotNull;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "drinks")
public class Drink implements MenuItem {

    @Element(name = "drinkId")
    private String drinkId;

    @Element(name = "drinkType", required = false)
    private String drinkType;

    @Element(name = "drinkPrice", required = false)
    private String drinkPrice;

    @Element(name = "drinkCategory", required = false)
    private String drinkCategory;

    @Element(name = "drinkName", required = false)
    private String drinkName;

    /**
     * Only used for serialization of XML to object for Retrofit!
     */
    public Drink() {
    }

    /**
     * Use this constructor when you're doing a POST request.
     *
     * @param drinkId
     */
    public Drink(@NotNull String drinkId) {
        this.drinkId = drinkId;
    }

    public Drink(String drinkId, String drinkType, String drinkPrice, String drinkCategory, String drinkName) {
        this.drinkId = drinkId;
        this.drinkType = drinkType;
        this.drinkPrice = drinkPrice;
        this.drinkCategory = drinkCategory;
        this.drinkName = drinkName;
    }

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

    @Override
    public String getName() {
        return drinkName;
    }

    @Override
    public String getPrice() {
        return drinkPrice;
    }

    @Override
    public String getCategory() {
        return drinkCategory;
    }

    @Override
    public String getId() {
        return drinkId;
    }

    @Override
    public String getTypeOfMenuItem() {
        return "Drink";
    }
}
