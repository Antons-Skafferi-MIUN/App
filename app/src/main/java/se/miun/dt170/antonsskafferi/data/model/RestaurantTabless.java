package se.miun.dt170.antonsskafferi.data.model;

public class RestaurantTabless {
    private RestaurantTables[] restaurantTables;

    public RestaurantTables[] getRestaurantTables() {
        return restaurantTables;
    }

    public void setRestaurantTables(RestaurantTables[] restaurantTables) {
        this.restaurantTables = restaurantTables;
    }

    @Override
    public String toString() {
        return "ClassPojo [restaurantTables = " + restaurantTables + "]";
    }
}
