package se.miun.dt170.antonsskafferi.data.remote;

import retrofit2.Call;
import retrofit2.http.GET;
import se.miun.dt170.antonsskafferi.data.model.Drinks;
import se.miun.dt170.antonsskafferi.data.model.Foods;
import se.miun.dt170.antonsskafferi.data.model.OrderRows;
import se.miun.dt170.antonsskafferi.data.model.Orders;
import se.miun.dt170.antonsskafferi.data.model.Reservations;
import se.miun.dt170.antonsskafferi.data.model.RestaurantTables;

public interface ApiService {

    @GET("entities.foods")
    Call<Foods> getFoods();

    @GET("entities.drinks")
    Call<Drinks> getDrinks();

    @GET("entities.orders")
    Call<Orders> getOrders();

    @GET("entities.restauranttables")
    Call<RestaurantTables> getRestaurantTables();

    @GET("entities.reservations")
    Call<Reservations> getReservations();

    @GET("entities.orderrows")
    Call<OrderRows> getOrderRows();
}