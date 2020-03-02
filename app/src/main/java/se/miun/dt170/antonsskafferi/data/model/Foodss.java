package se.miun.dt170.antonsskafferi.data.model;

public class Foodss {
    private Foods[] foods;

    public Foods[] getFoods() {
        return foods;
    }

    public void setFoods(Foods[] foods) {
        this.foods = foods;
    }

    @Override
    public String toString() {
        return "ClassPojo [foods = " + foods + "]";
    }
}
