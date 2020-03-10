package com.example.myapplication.API;

import com.example.myapplication.API.model.Drinks;
import com.example.myapplication.API.model.Foods;

import com.example.myapplication.API.model.Order;
import com.example.myapplication.API.model.OrderRow;
import com.example.myapplication.API.model.OrderRows;
import com.example.myapplication.API.model.Orders;
import com.example.myapplication.API.model.Reservation;
import com.example.myapplication.API.model.Reservations;
import com.example.myapplication.API.model.RestaurantTables;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import rx.Observable;
import rx.Single;

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
     * Posts a {@link Reservation} to the database.
     *
     * @param reservation object should NOT include a reservationID, because it's auto-increment
     * @return posted {@link Reservation} in a response-body (including the assigned auto-increment ID)
     */
    @POST("entities.reservations")
    Call<Reservation> postReservation(@Body Reservation reservation);


    /**
     * Posts a new {@link OrderRow} to the database.
     * <p>
     * It's very important to post a new {@link Order} to the database before attempting to post
     * a new {@link OrderRow}
     *
     * @param orderRow object should NOT include a OrderRowID, because it's auto-increment
     * @return posted {@link OrderRow} in a response-body (including the assigned auto-increment ID)
     */
    @POST("entities.orderrows")
    Call<OrderRow> postOrderRow(@Body OrderRow orderRow);

    /**
     * Posts a new {@link Order} to the database.
     *
     * @param order object should NOT include a OrderID, because it's auto-increment
     * @return posts {@link Order} in a response-body (including the assigned auto-increment ID)
     */
    @POST("entities.orders")
    Call<Order> postOrder(@Body Order order);

    // DELETE METHODS
    /**
     * Deletes the specified {@link Reservation} from the database.
     *
     * @param id reservation ID
     * @return a confirmation text in response-body
     */
    @DELETE("entities.reservations/{reservationId}")
    Call<Reservation> deleteReservation(@Path("reservationId") long id);

    /**
     * Deletes a specified {@link Order} from the database.
     *
     * @param id order ID
     * @return a confirmation text in response-body
     */
    @DELETE("entities.orders/{orderId}")
    Call<Order> deleteOrder(@Path("orderId") long id);

    /**
     * Deletes the specified {@link OrderRow} from the database.
     *
     * @param id orderRow ID
     * @return a confirmation text in response-body
     */
    @DELETE("entities.orderrows/{orderRowId}")
    Call<OrderRow> deleteOrderRow(@Path("orderRowId") long id);
}
