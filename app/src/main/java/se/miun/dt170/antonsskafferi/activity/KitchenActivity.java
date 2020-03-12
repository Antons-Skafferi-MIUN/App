package se.miun.dt170.antonsskafferi.activity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.util.Pair;

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
import se.miun.dt170.antonsskafferi.R;
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
    private Map<String, KitchenBongContainerView> kitchenBongContainerViews;
    private ApiService mAPIService;
    private CountDownTimer cTimer;
    //public int counter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kitchen);
        kitchenBongContainerViews = new HashMap<>();

        getLifecycle();
        mAPIService = ApiUtils.getAPIService();

        // Create view variables
        bongListLayoutContainer = findViewById(R.id.bongListContainer);

        //counter = 0;

        cTimer = null;
        startTimer();

        getOrderRows();
    }

    void startTimer() {
        cTimer = new CountDownTimer(3000, 1000) {
            private int counter = 0;
            public void onTick(long millisUntilFinished) {
                Log.d("Timer", "" + counter);
                counter++;
            }
            public void onFinish() {
                cTimer.start();
            }
        };
        cTimer.start();
    }

    @Override
    protected void onDestroy() {
        cancelTimer();
        super.onDestroy();
    }

    void cancelTimer() {
        if(cTimer!=null)
            cTimer.cancel();
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
                        Log.e("Retrofit RxJava", e.toString());
                    }

                    // Called on every new observed item
                    @Override
                    public void onNext(OrderRows response) {
                            Map<String, List<OrderRow>> orderRowMap = new HashMap<>();
                        response.getOrderRows().forEach(orderRow -> {
                            orderRowMap.computeIfAbsent(orderRow.getOrderId().getOrderId(), k -> new ArrayList<>()).add(orderRow);
                        });
                        buildOrders(orderRowMap);
                    }
                });
    }

    private void buildOrders(Map<String, List<OrderRow>> orderRowMap) {
        Log.d("order", "" + orderRowMap.size());
        orderRowMap.forEach((order, orderRows) -> {
            KitchenBongContainerView kitchenBongContainerView = new KitchenBongContainerView(this, orderRows);
            bongListLayoutContainer.addView(kitchenBongContainerView);
            kitchenBongContainerViews.put(order, kitchenBongContainerView);
        });
    }
}
