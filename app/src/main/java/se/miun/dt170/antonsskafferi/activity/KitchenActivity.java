package se.miun.dt170.antonsskafferi.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.PersistableBundle;
import android.util.Log;
import android.util.Pair;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.flexbox.FlexboxLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import se.miun.dt170.antonsskafferi.MainActivity;
import se.miun.dt170.antonsskafferi.R;
import se.miun.dt170.antonsskafferi.TableDialogSharedViewModel;
import se.miun.dt170.antonsskafferi.data.model.Drinks;
import se.miun.dt170.antonsskafferi.data.model.Foods;
import se.miun.dt170.antonsskafferi.data.model.Order;
import se.miun.dt170.antonsskafferi.data.model.OrderRow;
import se.miun.dt170.antonsskafferi.data.model.OrderRows;
import se.miun.dt170.antonsskafferi.data.model.Orders;
import se.miun.dt170.antonsskafferi.data.model.Reservations;
import se.miun.dt170.antonsskafferi.data.remote.ApiService;
import se.miun.dt170.antonsskafferi.data.remote.ApiUtils;
import se.miun.dt170.antonsskafferi.ui.kitchen.KitchenBongContainerView;

/**
 * Kitchen activity is the root for the kitchen navigation graph
 */
public class KitchenActivity extends AppCompatActivity {

    private FlexboxLayout bongListLayoutContainer;
    private RestaurantSharedViewModel restaurantSharedViewModel;
    private ApiService mAPIService;
    private CountDownTimer timer;
    private Map<String, List<OrderRow>> orderRowMap;// = new HashMap<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kitchen);

        orderRowMap = new HashMap<>();

        restaurantSharedViewModel = new ViewModelProvider(this).
              get(RestaurantSharedViewModel.class);

        mAPIService = ApiUtils.getAPIService();

        // Create view variables
        bongListLayoutContainer = findViewById(R.id.bongListContainer);

        timer = new CountDownTimer(3000, 1000) {
            public void onTick(long millisUntilFinished) {
                getOrderRows();
                long scnds = 0;
                scnds = (millisUntilFinished / 1000);
                Log.d("Ticker", "" + scnds);
            }

            public void onFinish() {
                this.start();
            }
        }.start();
    }


    @Override
    protected void onStop() {
        super.onStop();
        timer.cancel();
    }

    /*public void removeOrderFromActivity(String orderID) {
        kitchenBongContainerViews.remove(orderID);
    }*/

    public void getOrderRows() {
        mAPIService.getOrderRows().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<OrderRows>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("Retrofit KITCHEN", e.toString());
                    }

                    // Called on every new observed item
                    @Override
                    public void onNext(OrderRows response) {
                        response.getOrderRows().forEach(orderRow -> {
                            Order order = orderRow.getOrderId();
                            if (!restaurantSharedViewModel.getKitchenBongContainerViews().containsKey(order.getOrderId())) {
                                orderRowMap.computeIfAbsent(orderRow.getOrderId().getOrderId(), k -> new ArrayList<>()).add(orderRow);
                            }
                        });
                        buildOrders(orderRowMap);
                    }
                });
    }

    private void buildOrders(Map<String, List<OrderRow>> orderRowMap) {
        orderRowMap.forEach((orderID, orderRows) -> {
            KitchenBongContainerView kitchenBongContainerView = new KitchenBongContainerView(this, orderRows, orderID);
            bongListLayoutContainer.addView(kitchenBongContainerView);
            restaurantSharedViewModel.getKitchenBongContainerViews().put(orderID, kitchenBongContainerView);
            Log.d("containersize", "" + restaurantSharedViewModel.getKitchenBongContainerViews().size());
        });
        orderRowMap.clear();
    }

    public void removeOrderFromKitchen(String orderID) {
        restaurantSharedViewModel.removeOrderFromKitchen(orderID);
    }
}
