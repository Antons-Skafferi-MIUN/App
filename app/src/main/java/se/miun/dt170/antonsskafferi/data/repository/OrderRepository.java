package se.miun.dt170.antonsskafferi.data.repository;

import rx.Observable;
import se.miun.dt170.antonsskafferi.data.model.OrderRows;
import se.miun.dt170.antonsskafferi.data.remote.ApiService;
import se.miun.dt170.antonsskafferi.data.remote.ApiUtils;
import se.miun.dt170.antonsskafferi.ui.dialog.TableDialogFragment;

public class OrderRepository {
    private ApiService mAPIService;

    public OrderRepository(){

        mAPIService = ApiUtils.getAPIService();
    }
    // can use method overloading for other types.
    // alternative builder

    public Observable getOrders() {
        return mAPIService.getOrders();
    }

    public Observable<OrderRows> getOrderRows() {

        return mAPIService.getOrderRows();

    }
}
