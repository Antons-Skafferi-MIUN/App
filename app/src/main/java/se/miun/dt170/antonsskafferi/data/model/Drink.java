package se.miun.dt170.antonsskafferi.data.model;

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

    private String orderChanged = null;
    private String id = null;

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

    @Override
    public String getOrderChange() {
        return orderChanged;
    }

    //NEW
    @Override
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public void setOrderChanged(String orderChanged) {
        this.orderChanged = orderChanged;
    }

    //NEW
    @Override
    public void setIdChanged(int idChanged) {

    }

    //NEW
    @Override
    public int getIdChanged() {
        return 0;
    }
}
