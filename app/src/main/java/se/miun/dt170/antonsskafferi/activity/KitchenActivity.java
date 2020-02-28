package se.miun.dt170.antonsskafferi.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleOwner;

import java.util.HashMap;
import java.util.Map;

import se.miun.dt170.antonsskafferi.R;
import se.miun.dt170.antonsskafferi.data.Item;
import se.miun.dt170.antonsskafferi.databinding.ActivityKitchenBinding;
import se.miun.dt170.antonsskafferi.databinding.KitchenBongContainerViewBinding;
import se.miun.dt170.antonsskafferi.ui.bong.BongItemView;
import se.miun.dt170.antonsskafferi.ui.kitchen.KitchenBongContainerView;
import se.miun.dt170.antonsskafferi.ui.bong.BongListViewModel;

/**
 * Kitchen activity is the root for the kitchen navigation graph
 */
public class KitchenActivity extends AppCompatActivity {

    private Map<String, KitchenBongContainerView> KitchenBongContainerViews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kitchen);

        KitchenBongContainerViews = new HashMap<>();

        // Create view variables
        LinearLayout bongListLayoutContainer = findViewById(R.id.bongListContainer);

        // Add one bong list
        KitchenBongContainerViews.put("1", new KitchenBongContainerView(this, this));
        KitchenBongContainerViews.put("2", new KitchenBongContainerView(this, this));
        bongListLayoutContainer.addView(KitchenBongContainerViews.get("1"));
        bongListLayoutContainer.addView(KitchenBongContainerViews.get("2"));
    }
}
