package se.miun.dt170.antonsskafferi.data.model;

public class Get {
    private Foods foods;
//    private Drinkss drinkss;
//    private RestaurantTabless restaurantTabless;
//    private Orderss orderss;
//    private OrderRowss orderRowss;
//    private Reservationss reservationss;

    public Foods getFoods() {
        return foods;
    }

    public void setFoods(Foods foods) {
        this.foods = foods;
    }

//    public Drinkss getDrinkss() {
//        return drinkss;
//    }
//
//    public void setDrinkss(Drinkss drinkss) {
//        this.drinkss = drinkss;
//    }
//
//    public RestaurantTabless getRestaurantTabless() {
//        return restaurantTabless;
//    }
//
//    public void setRestaurantTabless(RestaurantTabless restaurantTabless) {
//        this.restaurantTabless = restaurantTabless;
//    }
//
//    public Orderss getOrderss() {
//        return orderss;
//    }
//
//    public void setOrderss(Orderss orderss) {
//        this.orderss = orderss;
//    }
//
//    public OrderRowss getOrderRowss() {
//        return orderRowss;
//    }
//
//    public void setOrderRowss(OrderRowss orderRowss) {
//        this.orderRowss = orderRowss;
//    }
//
//    public Reservationss getReservationss() {
//        return reservationss;
//    }
//
//    public void setReservationss(Reservationss reservationss) {
//        this.reservationss = reservationss;
//    }

    @Override
    public String toString() {
        return "ClassPojo [foodss = " + foods + "]"; //, drinkss = " + drinkss + ", restaurantTabless = " + restaurantTabless + ", orderss = " + orderss + ", orderRowss = " + orderRowss + ", reservationss = " + reservationss + "]";
    }
}
