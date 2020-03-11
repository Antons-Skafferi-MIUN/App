package se.miun.dt170.antonsskafferi.activity;

import androidx.lifecycle.ViewModel;

import java.util.Map;

import se.miun.dt170.antonsskafferi.ui.kitchen.KitchenBongContainerView;

public class RestaurantSharedViewModel extends ViewModel {
    public Map<String, KitchenBongContainerView> getKitchenBongContainerViews() {
        return kitchenBongContainerViews;
    }

    private Map<String, KitchenBongContainerView> kitchenBongContainerViews;

    public void removeOrderFromKitchen(String orderID) {

    }
}
