package se.miun.dt170.antonsskafferi.activity;

import android.os.Bundle;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;
import java.util.Map;

import se.miun.dt170.antonsskafferi.R;
import se.miun.dt170.antonsskafferi.data.Item;
import se.miun.dt170.antonsskafferi.ui.bong.BongItemView;
import se.miun.dt170.antonsskafferi.ui.kitchen.KitchenBongContainerView;
import se.miun.dt170.antonsskafferi.ui.bong.BongListView;
import se.miun.dt170.antonsskafferi.ui.bong.BongListViewModel;

/**
 * Kitchen activity is the root for the kitchen navigation graph
 */
public class KitchenActivity extends AppCompatActivity {

    private BongListViewModel bongListViewModel;
    private LinearLayout bongListLayoutContainer;
    private Map<String, KitchenBongContainerView> bongListViewMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kitchen);

        // Create variables
        bongListLayoutContainer = findViewById(R.id.bongListContainer);
        bongListViewMap = new HashMap<>();
        bongListViewModel = new BongListViewModel();

        // Add one bong list
        bongListViewMap.put("1", new BongListView(this));
        bongListLayoutContainer.addView(bongListViewMap.get("1"));

        // Populate bong list on changes to the bong list view model
        bongListViewModel.bongItems.observe(this, items -> {
            for (Item item : items) {
                bongListViewMap.get("1").addView(new BongItemView(this, item));
            }
        });
    }
}
