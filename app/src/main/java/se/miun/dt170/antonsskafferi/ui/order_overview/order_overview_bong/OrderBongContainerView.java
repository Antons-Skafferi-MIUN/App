package se.miun.dt170.antonsskafferi.ui.order_overview.order_overview_bong;

import android.content.Context;
import android.util.AttributeSet;

import android.util.Log;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import androidx.constraintlayout.widget.ConstraintLayout;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import se.miun.dt170.antonsskafferi.R;
import se.miun.dt170.antonsskafferi.data.model.OrderRows;
import se.miun.dt170.antonsskafferi.data.model.Orders;
import se.miun.dt170.antonsskafferi.data.remote.ApiService;

public class OrderBongContainerView extends ConstraintLayout {
    public OrderBongContainerView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }
}
