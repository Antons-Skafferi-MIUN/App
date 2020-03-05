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
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<OrderRows>() {
//                    @Override
//                    public void onCompleted() {
//
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        Log.i("ERROR IN GET ORDER ROWS", e.toString());
//                    }
//                    @Override
//                    public void onNext(OrderRows response) {
//                        Log.i("ERROR IN GET ORDER ROWS", response.toString());
//                    }
//                });
    }
}
