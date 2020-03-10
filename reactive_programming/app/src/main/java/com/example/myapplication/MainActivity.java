package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;


import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.myapplication.API.model.OrderRows;
import com.example.myapplication.API.model.Orders;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button myButt;
    private MainViewModel mvw;
    private TextView myView;
    private MediatorLiveData data;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mvw = new ViewModelProvider(this).get(MainViewModel.class);
        
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myButt = findViewById(R.id.button);
        myView = findViewById(R.id.textview);
        myButt.setOnClickListener(this);

//        data =  new MediatorLiveData<>();
//        data.addSource(mvw.getAllOrders(), new Observer<Orders>() {
//            @Override
//            public void onChanged(Orders orders) {
//                data.setValue(4);
//                myView.setText(mvw.extractDate(orders));
//            }
//        });
//        data.addSource(mvw.getAllOrderRows(), new Observer<OrderRows>() {
//                    @Override
//                    public void onChanged(OrderRows orderRows) {
//                        data.setValue(5);
//                    }
//        });


        // OBSERVER WITHOUT MEDIATOR
        final Observer<Orders> observer = new Observer<Orders>() {
            @Override
            public void onChanged(Orders orders) {
                Log.i("livedata","got an updt");
                myView.setText(mvw.extractDate(orders));
            }
        };
        mvw.getAllOrders().observe(this,observer);

    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.button:
                Log.i("button","clicked");
                Log.i("livedata" , data.getValue().toString());
                mvw.updateData();
        }
    }
}

// https://developer.android.com/reference/androidx/lifecycle/MediatorLiveData
//https://medium.com/androiddevelopers/livedata-beyond-the-viewmodel-reactive-patterns-using-transformations-and-mediatorlivedata-fda520ba00b7
// for syncing two lvie datas