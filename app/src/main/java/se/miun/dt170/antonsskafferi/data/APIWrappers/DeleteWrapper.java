package se.miun.dt170.antonsskafferi.data.APIWrappers;

import android.util.Log;

import java.util.Set;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

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
    private ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
    private Lock rLock = rwl.readLock();
    private Lock wLock = rwl.writeLock();

    public DeleteWrapper() {
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
        Log.i("Retrofit DELETE", "order delete.");
        mAPIService.deleteOrder(delOrderId).enqueue(new Callback<Order>() {
            @Override
            public void onResponse(Call<Order> call, Response<Order> response) {
                if (response.isSuccessful()) {
                    // TODO: Show success message
                    Log.i("Retrofit DELETE", "order delete submitted to API.");
                } else {
                    Log.e("Retrofit DELETE", response.errorBody().toString());
                }
            }

            @Override
            public void onFailure(Call<Order> call, Throwable t) {
                Log.e("Retrofit DELETE", "Unable to submit delete to API." + t.toString());
                t.printStackTrace();
            }
        });
    }

    public void deleteAllOrderRows(Set<String> orderRows, Set<String> ordersToRemoveFromKitchen) {
        orderRows.forEach(orderRow -> {
            Log.i("Retrofit DELETE", "orderRow delete.");

            // Takes a read lock
            rLock.lock();

            mAPIService.deleteOrderRow(orderRow).enqueue(new Callback<OrderRow>() {
                @Override
                public void onResponse(Call<OrderRow> call, Response<OrderRow> response) {
                    if (response.isSuccessful()) {
                        Log.i("Retrofit DELETE", "orderRow delete submitted to API.");
                        // Unlock read lock
                        rLock.unlock();

                        Log.i("Retrofit DELETE", "orderRow delete write lock below.");
                        // Will gain the write lock only after all read locks have been unlocked
                        // I.E. deleteOrder() will only be called after all deleteRows have finished!
                        if (wLock.tryLock()) {
                            try {
                                ordersToRemoveFromKitchen.forEach(currentOrderID -> {
                                    deleteOrder(currentOrderID);
                                });
                            } finally {
                                wLock.unlock();
                            }
                        } else {
                            Log.d("Retrofit DELETE", "Write is locked with locks :" + rwl.getReadLockCount());
                        }
                    }
                }

                @Override
                public void onFailure(Call<OrderRow> call, Throwable t) {
                    Log.e("Retrofit DELETE", "Unable to submit delete to API." + t.toString());
                    t.printStackTrace();
                }
            });
        });


    }
}
