package se.miun.dt170.antonsskafferi.ui.kitchen;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import se.miun.dt170.antonsskafferi.R;
import se.miun.dt170.antonsskafferi.data.model.Order;
import se.miun.dt170.antonsskafferi.data.model.OrderRow;
import se.miun.dt170.antonsskafferi.data.model.OrderRows;
import se.miun.dt170.antonsskafferi.data.remote.ApiService;
import se.miun.dt170.antonsskafferi.data.remote.ApiUtils;
import se.miun.dt170.antonsskafferi.ui.bong.BongItemView;

public class KitchenBongContainerView extends CardView implements LifecycleObserver {

    private LinearLayout kitchenBongContainerLinearLayout;
    private ApiService mAPIService;

    public KitchenBongContainerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public KitchenBongContainerView(Context context, LifecycleOwner lifecycle, Order order) {
        super(context);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.kitchen_bong_container_view, this, true);

        mAPIService = ApiUtils.getAPIService();

        kitchenBongContainerLinearLayout = findViewById(R.id.kitchenBongContainerLinearLayout);

        kitchenBongContainerLinearLayout.addView(new KitchenBongHeaderView(getContext(), order));

        getOrderRows(order);

    }

    public void getOrderRows(Order order) {
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
                            Log.d("Building BongItem", "Getting bong items");
                            buildOrderRow(orderRow, order);
                        });
                    }
                });
    }

    private void buildOrderRow(OrderRow orderRow, Order thisOrder) {
        if (orderRow.getOrderId().getOrderId().equals(thisOrder.getOrderId())) {
            Log.d("Building BongItem", "Adding bong items");
            kitchenBongContainerLinearLayout.addView(new BongItemView(getContext(), orderRow.getFoodId(), orderRow.getOrderChange()));
        }
    }
}
