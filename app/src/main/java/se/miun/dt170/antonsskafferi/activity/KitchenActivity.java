package se.miun.dt170.antonsskafferi.activity;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.flexbox.FlexboxLayout;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import se.miun.dt170.antonsskafferi.R;
import se.miun.dt170.antonsskafferi.data.model.Drinks;
import se.miun.dt170.antonsskafferi.data.model.Foods;
import se.miun.dt170.antonsskafferi.data.model.OrderRows;
import se.miun.dt170.antonsskafferi.data.model.Orders;
import se.miun.dt170.antonsskafferi.data.model.Reservations;
import se.miun.dt170.antonsskafferi.data.model.RestaurantTables;
import se.miun.dt170.antonsskafferi.data.remote.ApiService;
import se.miun.dt170.antonsskafferi.data.remote.ApiUtils;
import se.miun.dt170.antonsskafferi.ui.kitchen.KitchenBongContainerView;

/**
 * Kitchen activity is the root for the kitchen navigation graph
 */
public class KitchenActivity extends AppCompatActivity {

    private Map<String, KitchenBongContainerView> KitchenBongContainerViews;
    private ApiService mAPIService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kitchen);

        KitchenBongContainerViews = new HashMap<>();

        // Create view variables
        FlexboxLayout bongListLayoutContainer = findViewById(R.id.bongListContainer);

        // TEMPORARY CODE
        // Add one bong list
        KitchenBongContainerViews.put("1", new KitchenBongContainerView(this, this));
        KitchenBongContainerViews.put("2", new KitchenBongContainerView(this, this));
        KitchenBongContainerViews.put("3", new KitchenBongContainerView(this, this));
        KitchenBongContainerViews.put("4", new KitchenBongContainerView(this, this));
        KitchenBongContainerViews.put("5", new KitchenBongContainerView(this, this));
        KitchenBongContainerViews.put("6", new KitchenBongContainerView(this, this));
        KitchenBongContainerViews.put("7", new KitchenBongContainerView(this, this));
        bongListLayoutContainer.addView(KitchenBongContainerViews.get("1"));
        bongListLayoutContainer.addView(KitchenBongContainerViews.get("2"));
        bongListLayoutContainer.addView(KitchenBongContainerViews.get("3"));
        bongListLayoutContainer.addView(KitchenBongContainerViews.get("4"));
        bongListLayoutContainer.addView(KitchenBongContainerViews.get("5"));
        bongListLayoutContainer.addView(KitchenBongContainerViews.get("6"));
        bongListLayoutContainer.addView(KitchenBongContainerViews.get("7"));

        // API testing
        mAPIService = ApiUtils.getAPIService();

        getFoods();
        getDrinks();
        getRestaurantTables();
        getOrders();
        getReservations();
        getOrderRows();
    }

    // Temporary location for getting food from database
    public void getFoods() {
        mAPIService.getFoods().enqueue(new Callback<Foods>() {
            @Override
            public void onResponse(Call<Foods> Foods, Response<Foods> response) {
                if (response.isSuccessful()) {
                    showResponse(response.body().toString());
                    Log.i("Retrofit", "get submitted to API." + response.body().toString());
                }
            }

            @Override
            public void onFailure(Call<Foods> call, Throwable t) {
                Log.e("Retrofit", "Unable to submit post to API." + t.toString());
            }
        });
    }

    public void getDrinks() {
        mAPIService.getDrinks().enqueue(new Callback<Drinks>() {
            @Override
            public void onResponse(Call<Drinks> Drinks, Response<Drinks> response) {
                if (response.isSuccessful()) {
                    showResponse(response.body().toString());
                    Log.i("Retrofit", "get submitted to API." + response.body().toString());
                }
            }

            @Override
            public void onFailure(Call<Drinks> call, Throwable t) {
                Log.e("Retrofit", "Unable to submit post to API." + t.toString());
            }
        });
    }

    public void getRestaurantTables() {
        mAPIService.getRestaurantTables().enqueue(new Callback<RestaurantTables>() {
            @Override
            public void onResponse(Call<RestaurantTables> Drinks, Response<RestaurantTables> response) {
                if (response.isSuccessful()) {
                    showResponse(response.body().toString());
                    Log.i("Retrofit", "get submitted to API." + response.body().toString());
                }
            }

            @Override
            public void onFailure(Call<RestaurantTables> call, Throwable t) {
                Log.e("Retrofit", "Unable to submit post to API." + t.toString());
            }
        });
    }

    public void getOrders() {
        mAPIService.getOrders().enqueue(new Callback<Orders>() {
            @Override
            public void onResponse(Call<Orders> Drinks, Response<Orders> response) {
                if (response.isSuccessful()) {
                    showResponse(response.body().toString());
                    Log.i("Retrofit", "get submitted to API." + response.body().toString());
                }
            }

            @Override
            public void onFailure(Call<Orders> call, Throwable t) {
                Log.e("Retrofit", "Unable to submit post to API." + t.toString());
            }
        });
    }

    public void getReservations() {
        mAPIService.getReservations().enqueue(new Callback<Reservations>() {
            @Override
            public void onResponse(Call<Reservations> Drinks, Response<Reservations> response) {
                if (response.isSuccessful()) {
                    showResponse(response.body().toString());
                    Log.i("Retrofit", "get submitted to API." + response.body().toString());
                }
            }

            @Override
            public void onFailure(Call<Reservations> call, Throwable t) {
                Log.e("Retrofit", "Unable to submit post to API." + t.toString());
            }
        });
    }

    public void getOrderRows() {
        mAPIService.getOrderRows().enqueue(new Callback<OrderRows>() {
            @Override
            public void onResponse(Call<OrderRows> Drinks, Response<OrderRows> response) {
                if (response.isSuccessful()) {
                    showResponse(response.body().toString());
                    Log.i("Retrofit", "get submitted to API." + response.body().toString());
                }
            }

            @Override
            public void onFailure(Call<OrderRows> call, Throwable t) {
                Log.e("Retrofit", "Unable to submit post to API." + t.toString());
            }
        });
    }

    public void showResponse(String response) {
        // TODO: Do something with response
        Log.i("Retrofit", response);

//        if (mResponseTv.getVisibility() == View.GONE) {
//            mResponseTv.setVisibility(View.VISIBLE);
//        }
//        mResponseTv.setText(response);
    }
}
