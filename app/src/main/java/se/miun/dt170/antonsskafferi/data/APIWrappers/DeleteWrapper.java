package se.miun.dt170.antonsskafferi.data.APIWrappers;

import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import se.miun.dt170.antonsskafferi.data.model.Order;
import se.miun.dt170.antonsskafferi.data.model.OrderRow;
import se.miun.dt170.antonsskafferi.data.model.Reservation;
import se.miun.dt170.antonsskafferi.data.remote.ApiService;
import se.miun.dt170.antonsskafferi.data.remote.ApiUtils;

public class DeleteWrapper {
    private ApiService mAPIService;
    public DeleteWrapper(){
        mAPIService = ApiUtils.getAPIService();
    }

    //DELETE Calls
    public void deleteReservation(String delReservationId) {
        mAPIService.deleteReservation(delReservationId).enqueue(new Callback<Reservation>() {
            @Override
            public void onResponse(Call<Reservation> call, Response<Reservation> response) {
                if (response.isSuccessful()) {
                    // TODO: Show success message
                    Log.i("Retrofit DELETE", "delete submitted to API.");
                }
            }

            @Override
            public void onFailure(Call<Reservation> call, Throwable t) {
                Log.e("Retrofit DELETE", "Unable to submit delete to API." + t.toString());
                t.printStackTrace();
            }
        });
    }

    public void deleteOrder(String delOrderId) {
        mAPIService.deleteOrder(delOrderId).enqueue(new Callback<Order>() {
            @Override
            public void onResponse(Call<Order> call, Response<Order> response) {
                if (response.isSuccessful()) {
                    // TODO: Show success message
                    Log.i("Retrofit DELETE", "delete submitted to API.");
                }
            }

            @Override
            public void onFailure(Call<Order> call, Throwable t) {
                Log.e("Retrofit DELETE", "Unable to submit delete to API." + t.toString());
                t.printStackTrace();
            }
        });
    }

    public void deleteOrderRow(String delOrderRowId) {
        mAPIService.deleteOrderRow(delOrderRowId).enqueue(new Callback<OrderRow>() {
            @Override
            public void onResponse(Call<OrderRow> call, Response<OrderRow> response) {
                if (response.isSuccessful()) {
                    // TODO: Show success message
                    Log.i("Retrofit DELETE", "delete submitted to API.");
                }
            }

            @Override
            public void onFailure(Call<OrderRow> call, Throwable t) {
                Log.e("Retrofit DELETE", "Unable to submit delete to API." + t.toString());
                t.printStackTrace();
            }
        });
    }
}
