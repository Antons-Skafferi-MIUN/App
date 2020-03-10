package com.example.myapplication.API.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.myapplication.API.ApiService;
import com.example.myapplication.API.ApiUtils;
import com.example.myapplication.API.model.Order;
import com.example.myapplication.API.model.OrderRows;
import com.example.myapplication.API.model.Orders;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class GetOrder {
    private ApiService mAPIService;
    MutableLiveData<Orders> allOrders;
    MutableLiveData<OrderRows> allOrderRows;

    public GetOrder(){
        mAPIService = ApiUtils.getAPIService();
        allOrders = new MutableLiveData<Orders>();
        allOrderRows = new MutableLiveData<OrderRows>();
    }
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

    public MutableLiveData<OrderRows> getAllOrderRows(){
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
                        Log.i("Repo",orderRows.toString());
                        allOrderRows.setValue(orderRows);
                    }
                });
        return allOrderRows;
    }
}
