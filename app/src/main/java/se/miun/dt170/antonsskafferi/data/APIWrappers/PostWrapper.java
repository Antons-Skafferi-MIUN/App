package se.miun.dt170.antonsskafferi.data.APIWrappers;

import android.util.Log;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import se.miun.dt170.antonsskafferi.data.model.Drink;
import se.miun.dt170.antonsskafferi.data.model.Food;
import se.miun.dt170.antonsskafferi.data.model.MenuItem;
import se.miun.dt170.antonsskafferi.data.model.Order;
import se.miun.dt170.antonsskafferi.data.model.OrderRow;
import se.miun.dt170.antonsskafferi.data.model.OrderRows;
import se.miun.dt170.antonsskafferi.data.model.Reservation;
import se.miun.dt170.antonsskafferi.data.remote.ApiService;
import se.miun.dt170.antonsskafferi.data.remote.ApiUtils;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class PostWrapper {
    private ApiService mAPIService;
    public PostWrapper(){
        mAPIService = ApiUtils.getAPIService();
    }
    //public int increment = 1;

    public void postOrder(Order order, List<MenuItem> menuItems) {
        mAPIService.postOrder(order).enqueue(new Callback<Order>() {
            @Override
            public void onResponse(Call<Order> call, Response<Order> response) {
                if (response.isSuccessful()) {
                    // TODO: Show success message
                    Log.i("Retrofit POST", response.body().toString());
                    Log.i("Retrofit POST", "order post submitted to API.");

                    // Post a new OrderRow using the new OrderID

                    menuItems.forEach(menuItem -> {
                        OrderRow orderRow = null;
                        //menuItem.setIdChanged(increment);
                        //menuItem.setId("dfffff");

                        Log.d("ORDERROW_ITEMS", menuItem.getId() + "-" + menuItem.getName() + "- " + menuItem.getOrderChange() + " - "  + menuItem.getIdChanged());



                        if (menuItem.getTypeOfMenuItem().equals("Food")) {
                            //TODO FIX ORDER CHANGE
                            //orderRow = new OrderRow(response.body(), null, new Food(menuItem.getId()), menuItem.getOrderChange());
                            orderRow = new OrderRow(response.body(), null, new Food(menuItem.getId()), menuItem.getOrderChange());
                        } else if (menuItem.getTypeOfMenuItem().equals("Drink")) {
                            orderRow = new OrderRow(response.body(), new Drink(menuItem.getId()), null, menuItem.getOrderChange());
                        }

                        postOrderRow(orderRow);
                       //increment++;

                    });
                }
            }

            @Override
            public void onFailure(Call<Order> call, Throwable t) {
                Log.e("Retrofit POST", "Unable to submit order post to API." + t.toString());
                t.printStackTrace();
            }
        });
    }

    public void postOrderRow(OrderRow orderRow) {
        mAPIService.postOrderRow(orderRow).enqueue(new Callback<OrderRow>() {
            @Override
            public void onResponse(Call<OrderRow> call, Response<OrderRow> response) {

                if (response.isSuccessful()) {
                    // TODO: Show success message
                    Log.i("Retrofit POST", response.body().toString());
                    Log.i("Retrofit POST", "order post submitted to API.");
                }
            }

            @Override
            public void onFailure(Call<OrderRow> call, Throwable t) {
                Log.e("Retrofit POST", "Unable to submit order post to API." + t.toString());
                t.printStackTrace();
            }
        });
    }




    public void postReservation(Reservation reservation) {

        mAPIService.postReservation(reservation).enqueue(new Callback<Reservation>() {
            @Override
            public void onResponse(Call<Reservation> call, Response<Reservation> response) {
                Log.i("Retrofit POST", "ATTEMPTING POST");
                if (response.isSuccessful()) {
                    // TODO: Show success message
                    Log.i("Retrofit POST", response.body().toString());
                    Log.i("Retrofit POST", "reservation post submitted to API.");
                }
            }
            @Override
            public void onFailure(Call<Reservation> call, Throwable t) {
                Log.e("Retrofit POST", "Unable to submit post to API." + t.toString());
                t.printStackTrace();
            }
        });
    }

}
