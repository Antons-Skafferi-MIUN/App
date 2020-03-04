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

/**
 * This interface defines API end-points.
 * Consult https://code.tutsplus.com/tutorials/sending-data-with-retrofit-2-http-client-for-android--cms-27845
 * and https://guides.codepath.com/android/Consuming-APIs-with-Retrofit
 * for more information.
 *
 * TODO: Add end-points for POST, PUT and DELETE
 */
public interface ApiService {

    @GET("entity.foods")
    Observable<Foods> getFoods();

    @GET("entity.drinks")
    Observable<Drinks> getDrinks();

    @GET("entity.orders")
    Observable<Orders> getOrders();

    @GET("entity.restauranttables")
    Observable<RestaurantTables> getRestaurantTables();

    @GET("entity.reservations")
    Observable<Reservations> getReservations();

    @GET("entity.orderrows")
    Observable<OrderRows> getOrderRows();
}