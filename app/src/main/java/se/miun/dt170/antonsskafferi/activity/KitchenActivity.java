package se.miun.dt170.antonsskafferi.activity;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.flexbox.FlexboxLayout;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import se.miun.dt170.antonsskafferi.R;
import se.miun.dt170.antonsskafferi.data.DateConverter;
import se.miun.dt170.antonsskafferi.data.model.Drinks;
import se.miun.dt170.antonsskafferi.data.model.Food;
import se.miun.dt170.antonsskafferi.data.model.Foods;
import se.miun.dt170.antonsskafferi.data.model.Order;
import se.miun.dt170.antonsskafferi.data.model.OrderRow;
import se.miun.dt170.antonsskafferi.data.model.OrderRows;
import se.miun.dt170.antonsskafferi.data.model.Orders;
import se.miun.dt170.antonsskafferi.data.model.Reservation;
import se.miun.dt170.antonsskafferi.data.model.Reservations;
import se.miun.dt170.antonsskafferi.data.model.RestaurantTable;
import se.miun.dt170.antonsskafferi.data.remote.ApiService;
import se.miun.dt170.antonsskafferi.data.remote.ApiUtils;
import se.miun.dt170.antonsskafferi.ui.kitchen.KitchenBongContainerView;

/**
 * Kitchen activity is the root for the kitchen navigation graph
 */
public class KitchenActivity extends AppCompatActivity {

    private Map<String, KitchenBongContainerView> KitchenBongContainerViews;
    private ApiService mAPIService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kitchen);

        KitchenBongContainerViews = new HashMap<>();

        // Create view variables
        FlexboxLayout bongListLayoutContainer = findViewById(R.id.bongListContainer);

        // TEMPORARY CODE
        // Add one bong list
        KitchenBongContainerViews.put("1", new KitchenBongContainerView(this, this));
        KitchenBongContainerViews.put("2", new KitchenBongContainerView(this, this));
        KitchenBongContainerViews.put("3", new KitchenBongContainerView(this, this));
        KitchenBongContainerViews.put("4", new KitchenBongContainerView(this, this));
        KitchenBongContainerViews.put("5", new KitchenBongContainerView(this, this));
        KitchenBongContainerViews.put("6", new KitchenBongContainerView(this, this));
        KitchenBongContainerViews.put("7", new KitchenBongContainerView(this, this));
        bongListLayoutContainer.addView(KitchenBongContainerViews.get("1"));
        bongListLayoutContainer.addView(KitchenBongContainerViews.get("2"));
        bongListLayoutContainer.addView(KitchenBongContainerViews.get("3"));
        bongListLayoutContainer.addView(KitchenBongContainerViews.get("4"));
        bongListLayoutContainer.addView(KitchenBongContainerViews.get("5"));
        bongListLayoutContainer.addView(KitchenBongContainerViews.get("6"));
        bongListLayoutContainer.addView(KitchenBongContainerViews.get("7"));

        // API testing
        mAPIService = ApiUtils.getAPIService();

        // TEMPORARY EXAMPLE CODE
        getFoods();
        getDrinks();
        Log.i("TEST", "MESSAGE");


        DateConverter dateConverter = new DateConverter();
        Reservation reservation = new Reservation(new RestaurantTable("2"), "070-98752", dateConverter.getCurrentTime(), "Billy Sallad Test");
//        postReservation(reservation);

        Order order = new Order(new RestaurantTable("2"), dateConverter.getCurrentTime());
        postOrder(order);

//        //DELETE variables
//        long delReservationId = 1;
//        long delOrderId = 1;
//        long delOrderRowId = 1;
//
//        //DELETE method calls
//        deleteReservation(delReservationId);
//        deleteOrder(delOrderId);
//        deleteOrderRow(delOrderRowId);
    }

    private void postOrder(Order order) {
        mAPIService.postOrder(order).enqueue(new Callback<Order>() {
            @Override
            public void onResponse(Call<Order> call, Response<Order> response) {
                if (response.isSuccessful()) {
                    // TODO: Show success message
                    Log.i("Retrofit POST", response.body().toString());
                    Log.i("Retrofit POST", "order post submitted to API.");

                    // Post a new OrderRow using the new OrderID
                    OrderRow orderRow = new OrderRow(response.body(), null, new Food("3"), null);
                    postOrderRow(orderRow);
                }
            }

            @Override
            public void onFailure(Call<Order> call, Throwable t) {
                Log.e("Retrofit POST", "Unable to submit order post to API." + t.toString());
                t.printStackTrace();
            }
        });
    }

    private void postOrderRow(OrderRow orderRow) {
        mAPIService.postOrderRow(orderRow).enqueue(new Callback<OrderRow>() {
            @Override
            public void onResponse(Call<OrderRow> call, Response<OrderRow> response) {

                if (response.isSuccessful()) {
                    // TODO: Show success message
                    Log.i("Retrofit POST", response.body().toString());
                    Log.i("Retrofit POST", "order post submitted to API.");
                }
            }

            @Override
            public void onFailure(Call<OrderRow> call, Throwable t) {
                Log.e("Retrofit POST", "Unable to submit order post to API." + t.toString());
                t.printStackTrace();
            }
        });
    }

    //DELETE Calls
    private void deleteReservation(long delReservationId) {
        mAPIService.deleteReservation(delReservationId).enqueue(new Callback<Reservation>() {
            @Override
            public void onResponse(Call<Reservation> call, Response<Reservation> response) {
                if (response.isSuccessful()) {
                    // TODO: Show success message
                    Log.i("Retrofit DELETE", "delete submitted to API.");
                }
            }

            @Override
            public void onFailure(Call<Reservation> call, Throwable t) {
                Log.e("Retrofit DELETE", "Unable to submit delete to API." + t.toString());
                t.printStackTrace();
            }
        });
    }

    private void deleteOrder(long delOrderId) {
        mAPIService.deleteOrder(delOrderId).enqueue(new Callback<Order>() {
            @Override
            public void onResponse(Call<Order> call, Response<Order> response) {
                if (response.isSuccessful()) {
                    // TODO: Show success message
                    Log.i("Retrofit DELETE", "delete submitted to API.");
                }
            }

            @Override
            public void onFailure(Call<Order> call, Throwable t) {
                Log.e("Retrofit DELETE", "Unable to submit delete to API." + t.toString());
                t.printStackTrace();
            }
        });
    }

    private void deleteOrderRow(long delOrderRowId) {
        mAPIService.deleteOrderRow(delOrderRowId).enqueue(new Callback<OrderRow>() {
            @Override
            public void onResponse(Call<OrderRow> call, Response<OrderRow> response) {
                if (response.isSuccessful()) {
                    // TODO: Show success message
                    Log.i("Retrofit DELETE", "delete submitted to API.");
                }
            }

            @Override
            public void onFailure(Call<OrderRow> call, Throwable t) {
                Log.e("Retrofit DELETE", "Unable to submit delete to API." + t.toString());
                t.printStackTrace();
            }
        });
    }


    private void postReservation(Reservation reservation) {
        mAPIService.postReservation(reservation).enqueue(new Callback<Reservation>() {
            @Override
            public void onResponse(Call<Reservation> call, Response<Reservation> response) {
                if (response.isSuccessful()) {
                    // TODO: Show success message
                    Log.i("Retrofit POST", response.body().toString());
                    Log.i("Retrofit POST", "reservation post submitted to API.");
                }
            }

            @Override
            public void onFailure(Call<Reservation> call, Throwable t) {
                Log.e("Retrofit POST", "Unable to submit post to API." + t.toString());
                t.printStackTrace();
            }
        });
    }

    // Temporary location for getting food from database
    public void getFoods() {
        mAPIService.getFoods().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Foods>() {
                    @Override
                    public void onCompleted() {
                        Log.i("Complete", "GET food complete");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("Retrofit RxJava", e.toString());
                    }

                    // Called on every new observed item
                    @Override
                    public void onNext(Foods response) {
                        showResponse(response.toString());
                        Log.i("Retrofit RxJava", "get submitted to API." + response.toString());
                    }
                });
    }

    public void getDrinks() {
        mAPIService.getDrinks().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Drinks>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("Retrofit RxJava", e.toString());
                    }

                    // Called on every new observed item
                    @Override
                    public void onNext(Drinks response) {
                        showResponse(response.toString());
                        Log.i("Retrofit RxJava", "get submitted to API." + response.toString());
                    }
                });
    }

    public void showResponse(String response) {
        // TODO: Do something with response
        Log.i("Retrofit", response);
    }
}
