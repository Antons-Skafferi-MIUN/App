package se.miun.dt170.antonsskafferi.data.model;

public class Drinkss {
    private Drinks[] drinks;

    public Drinks[] getDrinks() {
        return drinks;
    }

    public void setDrinks(Drinks[] drinks) {
        this.drinks = drinks;
    }

    @Override
    public String toString() {
        return "ClassPojo [drinks = " + drinks + "]";
    }
}
