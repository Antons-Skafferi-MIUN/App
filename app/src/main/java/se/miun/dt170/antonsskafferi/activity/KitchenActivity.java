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
import se.miun.dt170.antonsskafferi.data.model.Drinks;
import se.miun.dt170.antonsskafferi.data.model.Food;
import se.miun.dt170.antonsskafferi.data.model.Foods;
import se.miun.dt170.antonsskafferi.data.model.Order;
import se.miun.dt170.antonsskafferi.data.model.OrderRow;
import se.miun.dt170.antonsskafferi.data.model.OrderRows;
import se.miun.dt170.antonsskafferi.data.model.Orders;
import se.miun.dt170.antonsskafferi.data.model.Reservation;
import se.miun.dt170.antonsskafferi.data.model.Reservations;
import se.miun.dt170.antonsskafferi.data.remote.ApiService;
import se.miun.dt170.antonsskafferi.data.remote.ApiUtils;
import se.miun.dt170.antonsskafferi.ui.kitchen.KitchenBongContainerView;

/**
 * Kitchen activity is the root for the kitchen navigation graph
 */
public class KitchenActivity extends AppCompatActivity {

    private FlexboxLayout bongListLayoutContainer;
    private Map<String, KitchenBongContainerView> kitchenBongContainerViews;
    private ApiService mAPIService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kitchen);

        kitchenBongContainerViews = new HashMap<>();
        mAPIService = ApiUtils.getAPIService();

        // Create view variables
        bongListLayoutContainer = findViewById(R.id.bongListContainer);

        getOrderRows();
    }

    public void getOrderRows() {
        mAPIService.getOrderRows().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<OrderRows>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("Retrofit RxJava", e.toString());
                    }

                    // Called on every new observed item
                    @Override
                    public void onNext(OrderRows response) {
                        response.getOrderRows().forEach(orderRow -> {
                            buildOrder(orderRow.getOrderId());
                            buildOrderRow(orderRow);
                        });

                        showResponse(response.toString());
                        Log.i("Retrofit RxJava", "get submitted to API." + response.toString());
                    }
                });
    }

    private void buildOrder(Order order) {
        if (null == kitchenBongContainerViews.putIfAbsent(order.getOrderId(), new KitchenBongContainerView(this, this, order))) {
            Log.d("Building order", order.toString());
            bongListLayoutContainer.addView(kitchenBongContainerViews.get(order.getOrderId()));
        }
    }

    public void buildOrderRow(OrderRow orderRow) {
        Log.d("Building orderRow", orderRow.toString());

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

    public void getOrders() {
        mAPIService.getOrders().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Orders>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("Retrofit RxJava", e.toString());
                    }

                    // Called on every new observed item
                    @Override
                    public void onNext(Orders response) {
                        showResponse(response.toString());
                        Log.i("Retrofit RxJava", "get submitted to API." + response.toString());
                    }
                });
    }

    public void getReservations() {
        mAPIService.getReservations().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Reservations>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("Retrofit RxJava", e.toString());
                    }

                    // Called on every new observed item
                    @Override
                    public void onNext(Reservations response) {
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
