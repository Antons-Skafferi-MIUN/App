package se.miun.dt170.antonsskafferi.data.remote;


import retrofit2.Call;
import retrofit2.http.GET;
import rx.Observable;
import se.miun.dt170.antonsskafferi.data.model.Drinks;
import se.miun.dt170.antonsskafferi.data.model.Foods;
import se.miun.dt170.antonsskafferi.data.model.OrderRows;
import se.miun.dt170.antonsskafferi.data.model.Orders;
import se.miun.dt170.antonsskafferi.data.model.Reservations;
import se.miun.dt170.antonsskafferi.data.model.RestaurantTables;

public interface ApiService {

    @GET("entities.foods")
    Observable<Foods> getFoods();

    @GET("entities.drinks")
    Observable<Drinks> getDrinks();

    @GET("entities.orders")
    Observable<Orders> getOrders();

    @GET("entities.restauranttables")
    Observable<RestaurantTables> getRestaurantTables();

    @GET("entities.reservations")
    Observable<Reservations> getReservations();

    @GET("entities.orderrows")
    Observable<OrderRows> getOrderRows();
}