package se.miun.dt170.antonsskafferi.data.remote;


import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import rx.Observable;
import se.miun.dt170.antonsskafferi.data.model.Drinks;
import se.miun.dt170.antonsskafferi.data.model.Foods;
import se.miun.dt170.antonsskafferi.data.model.Order;
import se.miun.dt170.antonsskafferi.data.model.OrderRow;
import se.miun.dt170.antonsskafferi.data.model.OrderRows;
import se.miun.dt170.antonsskafferi.data.model.Orders;
import se.miun.dt170.antonsskafferi.data.model.Reservation;
import se.miun.dt170.antonsskafferi.data.model.Reservations;
import se.miun.dt170.antonsskafferi.data.model.RestaurantTables;

/**
 * This interface defines API end-points.
 * Documentation: https://square.github.io/retrofit/
 *
 * Consult https://code.tutsplus.com/tutorials/sending-data-with-retrofit-2-http-client-for-android--cms-27845
 * and https://guides.codepath.com/android/Consuming-APIs-with-Retrofit
 * for more information.
 *
 * Pojo classes generated using http://pojo.sodhanalibrary.com/
 * The classes are annotated using Simple XML Serialization annotations from
 * http://simple.sourceforge.net/download/stream/doc/tutorial/tutorial.php
 *
 * TODO: Add end-points for POST, PUT and DELETE
 */
public interface ApiService {

    // GET METHODS
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

    // POST METHODS

    /**
     * Posts a reservation to the database.
     *
     * @param reservation object should NOT include a reservationID, because it's auto-increment
     * @return posted reservation in a response-body (including the assigned auto-increment ID)
     */
    @POST("entities.reservations")
    Call<Reservation> postReservation(@Body Reservation reservation);

    @POST("entities.orderrows")
    Call<OrderRow> postOrderRow(@Body OrderRow orderRow);

    @POST("entities.orders")
    Call<Order> postOrder(@Body Order order);
  
    // DELETE METHODS
    @DELETE("entity.reservations/{reservationId}")
    Call<Reservation> deleteReservation(@Path("reservationId") long id);

    @DELETE("entity.orders/{orderId}")
    Call<Order> deleteOrder(@Path("orderId") long id);

    @DELETE("entity.orderrows/{orderRowId}")
    Call<OrderRow> deleteOrderRow(@Path("orderRowId") long id);
}