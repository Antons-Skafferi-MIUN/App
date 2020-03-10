package com.example.myapplication;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.myapplication.API.model.Order;
import com.example.myapplication.API.model.OrderRows;
import com.example.myapplication.API.model.Orders;
import com.example.myapplication.API.repository.GetOrder;

import java.util.ArrayList;

public class MainViewModel extends ViewModel {
    private GetOrder getOrderRepo;
    private MutableLiveData<Orders> allOrders;
    private MutableLiveData<OrderRows> allOrderRows;


    public MainViewModel(){
        getOrderRepo = new GetOrder();
    }

    public MutableLiveData<Orders> getAllOrders() {
        if(allOrders == null){
            allOrders = new MutableLiveData<>();
            allOrders = getOrderRepo.getAllOrders();
        }
        return  allOrders;
    }

    public MutableLiveData<OrderRows> getAllOrderRows() {
        if(allOrderRows == null){
            allOrderRows = new MutableLiveData<>();
            allOrderRows = getOrderRepo.getAllOrderRows();
        }
        return allOrderRows;
    }

    public void updateData(){
        Log.i("updating","updating data");
        Orders orders = getOrderRepo.getAllOrders().getValue();
        allOrders.setValue(orders);
    }
    public void updateOrderRowData(){
        Log.i("updating","updating data");
        OrderRows orderRows = getOrderRepo.getAllOrderRows().getValue();
        allOrderRows.setValue(orderRows);
    }

    public String extractDate(Orders orders){
        ArrayList<Order> listOfOrders = orders.getOrders();
        Log.i("extractDate", ""+(listOfOrders.size()-1));
        String date = listOfOrders.get(listOfOrders.size()-1).getOrderTime();
        return date;
    }

}
