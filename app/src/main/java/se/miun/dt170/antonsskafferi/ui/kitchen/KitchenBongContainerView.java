package se.miun.dt170.antonsskafferi.ui.kitchen;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.LifecycleObserver;

import java.util.List;

import se.miun.dt170.antonsskafferi.R;
import se.miun.dt170.antonsskafferi.activity.KitchenActivity;
import se.miun.dt170.antonsskafferi.activity.RestaurantSharedViewModel;
import se.miun.dt170.antonsskafferi.data.model.OrderRow;
import se.miun.dt170.antonsskafferi.data.remote.ApiService;
import se.miun.dt170.antonsskafferi.data.remote.ApiUtils;
import se.miun.dt170.antonsskafferi.ui.bong.BongItemView;
import se.miun.dt170.antonsskafferi.ui.bong.BongListView;

public class KitchenBongContainerView extends CardView implements LifecycleObserver {

    private BongListView kitchenBongContainerLinearLayout;
    private RestaurantSharedViewModel restaurantSharedViewModel;
    private String orderID;
    private ApiService mAPIService;
    private KitchenActivity kitchenActivity;

    public KitchenBongContainerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public KitchenBongContainerView(Context context, List<OrderRow> orderRows, String orderID) {
        super(context);

        //kitchenActivity = (KitchenActivity) context;

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.kitchen_bong_container_view, this, true);


        //this.restaurantSharedViewModel = restaurantSharedViewModel;
        //new ViewModelProvider(this).
        //get(RestaurantSharedViewModel.class);

        this.orderID = orderID;

        mAPIService = ApiUtils.getAPIService();

        kitchenBongContainerLinearLayout = findViewById(R.id.kitchenBongContainerLinearLayout);
        kitchenBongContainerLinearLayout.addView(new KitchenBongHeaderView(getContext(), orderRows.get(0).getOrderId()));

        orderRows.forEach(orderRow -> {
            buildOrderRow(orderRow);
        });
    }

    private void buildOrderRow(OrderRow orderRow) {
        kitchenBongContainerLinearLayout.addView(new BongItemView(getContext(), orderRow.getFoodId(), orderRow.getOrderChange()));
        kitchenBongContainerLinearLayout.raiseNumberOfItems();
    }

    public void updateOrderStatus() {
        //kitchenActivity.removeOrderFromKitchen(orderID);
        //restaurantSharedViewModel.removeOrderFromKitchen(orderID);
    }
}
