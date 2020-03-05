package se.miun.dt170.antonsskafferi.ui.kitchen;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;

import se.miun.dt170.antonsskafferi.R;
import se.miun.dt170.antonsskafferi.data.Item;
import se.miun.dt170.antonsskafferi.data.model.Order;
import se.miun.dt170.antonsskafferi.ui.bong.BongItemView;
import se.miun.dt170.antonsskafferi.ui.bong.BongListViewModel;

public class KitchenBongContainerView extends CardView implements LifecycleObserver {

    public KitchenBongContainerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public KitchenBongContainerView(Context context, LifecycleOwner lifecycle, Order order) {
        super(context);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.kitchen_bong_container_view, this, true);

        BongListViewModel bongListViewModel = new BongListViewModel();

        LinearLayout kitchenBongContainerLinearLayout = findViewById(R.id.kitchenBongContainerLinearLayout);

        kitchenBongContainerLinearLayout.addView(new KitchenBongHeaderView(getContext(), order));

        // Populate bong list on changes to the bong list view model
        bongListViewModel.bongItems.observe(lifecycle, items -> {
            for (Item item : items) {
                kitchenBongContainerLinearLayout.addView(new BongItemView(getContext(), item));
            }
        });
    }
}
