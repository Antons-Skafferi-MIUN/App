package se.miun.dt170.antonsskafferi.ui.kitchen;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;

import se.miun.dt170.antonsskafferi.R;
import se.miun.dt170.antonsskafferi.data.Item;
import se.miun.dt170.antonsskafferi.ui.bong.BongItemView;
import se.miun.dt170.antonsskafferi.ui.bong.BongListViewModel;

public class KitchenBongContainerView extends LinearLayout implements LifecycleObserver {

    public KitchenBongContainerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public KitchenBongContainerView(Context context, LifecycleOwner lifecycle) {
        super(context);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.kitchen_bong_container_view, this, true);

        setOrientation(VERTICAL); // VERY IMPORTANT

        BongListViewModel bongListViewModel = new BongListViewModel();

        // Populate bong list on changes to the bong list view model
        bongListViewModel.bongItems.observe(lifecycle, items -> {
            for (Item item : items) {
                addView(new BongItemView(getContext(), item));
            }
        });
    }
}
