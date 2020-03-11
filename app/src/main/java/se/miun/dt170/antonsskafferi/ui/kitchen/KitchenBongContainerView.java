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

import java.util.List;

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
import se.miun.dt170.antonsskafferi.ui.bong.BongListView;

public class KitchenBongContainerView extends CardView implements LifecycleObserver {

    private BongListView kitchenBongContainerLinearLayout;
    private ApiService mAPIService;

    public KitchenBongContainerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public KitchenBongContainerView(Context context, List<OrderRow> orderRows) {
        super(context);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.kitchen_bong_container_view, this, true);

        mAPIService = ApiUtils.getAPIService();

        kitchenBongContainerLinearLayout = findViewById(R.id.kitchenBongContainerLinearLayout);
        kitchenBongContainerLinearLayout.addView(new KitchenBongHeaderView(getContext(), orderRows.get(0).getOrderId()));

        Log.d("orderrows", "" + orderRows.size());
        orderRows.forEach(orderRow -> {
            buildOrderRow(orderRow);
        });
    }

    private void buildOrderRow(OrderRow orderRow) {
        kitchenBongContainerLinearLayout.addView(new BongItemView(getContext(), orderRow.getFoodId(), orderRow.getOrderChange()));
        kitchenBongContainerLinearLayout.raiseNumberOfItems();
    }
}
