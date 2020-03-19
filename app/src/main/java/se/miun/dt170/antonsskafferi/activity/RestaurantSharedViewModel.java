package se.miun.dt170.antonsskafferi.activity;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import java.util.HashMap;
import java.util.Map;

import se.miun.dt170.antonsskafferi.ui.kitchen.KitchenBongContainerView;

public class RestaurantSharedViewModel extends ViewModel {
    private Map<String, KitchenBongContainerView> kitchenBongContainerViews;

    public RestaurantSharedViewModel() {
        kitchenBongContainerViews = new HashMap<>();
    }

    public Map<String, KitchenBongContainerView> getKitchenBongContainerViews() {
        Log.d("Kitchensize", Integer.toString(kitchenBongContainerViews.size()));
        return kitchenBongContainerViews;
    }// = new HashMap<>();


    public void removeOrderFromKitchen(String orderID) {
        //kitchenBongContainerViews.remove(orderID);
        Log.d("Removing from kitchen", orderID);
    }
}
