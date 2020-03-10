package se.miun.dt170.antonsskafferi.data.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import se.miun.dt170.antonsskafferi.data.model.OrderRows;
import se.miun.dt170.antonsskafferi.data.model.Orders;
import se.miun.dt170.antonsskafferi.data.remote.ApiService;
import se.miun.dt170.antonsskafferi.data.remote.ApiUtils;
import se.miun.dt170.antonsskafferi.ui.dialog.TableDialogFragment;

public class OrderRepository {
    private ApiService mAPIService;
    MutableLiveData<Orders> allOrders;
    MutableLiveData<OrderRows> allOrderRows;

    public OrderRepository(){
        mAPIService = ApiUtils.getAPIService();
        allOrders = new MutableLiveData<Orders>();
        allOrderRows = new MutableLiveData<OrderRows>();
    }

    /**
     * Gets all orders from database and puts them in a MutableLiveData object.
     *
     * @return an observerable object containing all orders.
     */
    public MutableLiveData<Orders> getAllOrders(){
        Log.i("func","starting func");
        mAPIService.getOrders().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Orders>() {
                    @Override
                    public void onCompleted() {
                        Log.i("func","onComplete");

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("onError",e.toString());

                    }

                    @Override
                    public void onNext(Orders orders) {
                        Log.i("Repo",orders.toString());
                        allOrders.setValue(orders);
                    }
                });
        return allOrders;
    }
    /**
     * Gets all ordersRows from database and puts them in a MutableLiveData object.
     *
     * @return an observerable object containing all ordersRows.
     */
    public MutableLiveData<OrderRows> getOrderRows(){
        Log.i("func","starting func");
        mAPIService.getOrderRows().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<OrderRows>() {
                    @Override
                    public void onCompleted() {
                        Log.i("func","onComplete");

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("onError",e.toString());

                    }

                    @Override
                    public void onNext(OrderRows orderRows) {
                        Log.i("orderRowRepo",orderRows.toString());
                        allOrderRows.setValue(orderRows);
                    }
                });
        return allOrderRows;
    }
}