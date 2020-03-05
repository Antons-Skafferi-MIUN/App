package se.miun.dt170.antonsskafferi.data.repository;

import android.util.Log;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import se.miun.dt170.antonsskafferi.data.model.OrderRows;
import se.miun.dt170.antonsskafferi.data.model.Orders;
import se.miun.dt170.antonsskafferi.data.model.Reservations;
import se.miun.dt170.antonsskafferi.data.remote.ApiService;
import se.miun.dt170.antonsskafferi.data.remote.ApiUtils;
import se.miun.dt170.antonsskafferi.ui.dialog.TableDialogFragment;
import se.miun.dt170.antonsskafferi.ui.table_overview.TableOverviewFragment;

public class OrderRepository {
    private ApiService mAPIService;

    public OrderRepository(){

        mAPIService = ApiUtils.getAPIService();
    }
    // can use method overloading for other types.
    // alternative builder
    public void getOrders(TableDialogFragment tableDialogFragment) {

        mAPIService.getOrders()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Orders>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("ERROR IN GET ORDERS", e.toString());
                    }
                    @Override
                    public void onNext(Orders response) {
                        Log.i("MY ORDER", response.toString());
                        tableDialogFragment.setTableOrders(response);
                    }

                });

    }
    public void getOrderRows(TableDialogFragment tableDialogFragment) {

        mAPIService.getOrderRows()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<OrderRows>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("ERROR IN GET ORDER ROWS", e.toString());
                    }
                    @Override
                    public void onNext(OrderRows response) {
                        Log.i("ERROR IN GET ORDER ROWS", response.toString());
                    }
                });
    }
}
