package se.miun.dt170.antonsskafferi.activity;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.flexbox.FlexboxLayout;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import se.miun.dt170.antonsskafferi.R;
import se.miun.dt170.antonsskafferi.data.model.Drinks;
import se.miun.dt170.antonsskafferi.data.model.Foods;
import se.miun.dt170.antonsskafferi.data.model.Order;
import se.miun.dt170.antonsskafferi.data.model.OrderRows;
import se.miun.dt170.antonsskafferi.data.model.Orders;
import se.miun.dt170.antonsskafferi.data.model.Reservation;
import se.miun.dt170.antonsskafferi.data.model.Reservations;
import se.miun.dt170.antonsskafferi.data.model.RestaurantTables;
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
        getRestaurantTables();
        getOrders();
        getReservations();
        getOrderRows();
        Log.i("TEST", "MESSAGE");

    }

    // Temporary location for getting food from database
    public void getFoods() {
        mAPIService.getFoods().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Foods>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

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

                    }

                    // Called on every new observed item
                    @Override
                    public void onNext(Drinks response) {
                        showResponse(response.toString());
                        Log.i("Retrofit RxJava", "get submitted to API." + response.toString());
                    }
                });
    }

    public void getRestaurantTables() {
        mAPIService.getRestaurantTables().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<RestaurantTables>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    // Called on every new observed item
                    @Override
                    public void onNext(RestaurantTables response) {
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
                        Log.i("Retrofit RxJava", e.toString());
                    }

                    // Called on every new observed item
                    @Override
                    public void onNext(Reservations response) {
                        showResponse(response.toString());
                        Log.i("Retrofit RxJava", "get submitted to API." + response.toString());
                    }
                });
    }

    public void getOrderRows() {
        mAPIService.getOrderRows().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<OrderRows>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    // Called on every new observed item
                    @Override
                    public void onNext(OrderRows response) {
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
