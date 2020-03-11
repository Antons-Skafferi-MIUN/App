package se.miun.dt170.antonsskafferi.data.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import se.miun.dt170.antonsskafferi.data.model.Orders;
import se.miun.dt170.antonsskafferi.data.model.Reservation;
import se.miun.dt170.antonsskafferi.data.model.Reservations;
import se.miun.dt170.antonsskafferi.data.remote.ApiService;
import se.miun.dt170.antonsskafferi.data.remote.ApiUtils;

public class ReservationRepository {

    private ApiService mAPIService;
    MutableLiveData<Reservations> allReservations;

    public ReservationRepository() {
        mAPIService = ApiUtils.getAPIService();
        allReservations = new MutableLiveData<>();
    }

    public MutableLiveData<Reservations> getAllReservations(){
        mAPIService.getReservations().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Reservations>() {
                    @Override
                    public void onCompleted() {
                        Log.i("func","onComplete");

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("onError",e.toString());

                    }

                    @Override
                    public void onNext(Reservations reservations) {
                        Log.i("Repo",reservations.toString());
                        allReservations.setValue(reservations);
                    }
                });
        return allReservations;
    }
}
