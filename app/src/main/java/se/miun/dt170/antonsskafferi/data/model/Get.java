package se.miun.dt170.antonsskafferi.data.model;

public class Get {
    private Foodss foodss;
    private Drinkss drinkss;
    private RestaurantTabless restaurantTabless;
    private Orderss orderss;
    private OrderRowss orderRowss;
    private Reservationss reservationss;

    public Foodss getFoodss() {
        return foodss;
    }

    public void setFoodss(Foodss foodss) {
        this.foodss = foodss;
    }

    public Drinkss getDrinkss() {
        return drinkss;
    }

    public void setDrinkss(Drinkss drinkss) {
        this.drinkss = drinkss;
    }

    public RestaurantTabless getRestaurantTabless() {
        return restaurantTabless;
    }

    public void setRestaurantTabless(RestaurantTabless restaurantTabless) {
        this.restaurantTabless = restaurantTabless;
    }

    public Orderss getOrderss() {
        return orderss;
    }

    public void setOrderss(Orderss orderss) {
        this.orderss = orderss;
    }

    public OrderRowss getOrderRowss() {
        return orderRowss;
    }

    public void setOrderRowss(OrderRowss orderRowss) {
        this.orderRowss = orderRowss;
    }

    public Reservationss getReservationss() {
        return reservationss;
    }

    public void setReservationss(Reservationss reservationss) {
        this.reservationss = reservationss;
    }

    @Override
    public String toString() {
        return "ClassPojo [foodss = " + foodss + ", drinkss = " + drinkss + ", restaurantTabless = " + restaurantTabless + ", orderss = " + orderss + ", orderRowss = " + orderRowss + ", reservationss = " + reservationss + "]";
    }
}
