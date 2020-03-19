package se.miun.dt170.antonsskafferi.data.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import se.miun.dt170.antonsskafferi.data.model.Reservations;
import se.miun.dt170.antonsskafferi.data.remote.ApiService;
import se.miun.dt170.antonsskafferi.data.remote.ApiUtils;

public class ReservationRepository {

    MutableLiveData<Reservations> allReservations;
    private ApiService mAPIService;

    public ReservationRepository() {
        mAPIService = ApiUtils.getAPIService();
        allReservations = new MutableLiveData<>();
    }

    public MutableLiveData<Reservations> getAllReservations() {

        mAPIService.getReservations().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Reservations>() {
                    @Override
                    public void onCompleted() {
                        Log.i("func", "onComplete");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("REPO ERROR", "FAILED TO GET " + e.toString());
                        allReservations.setValue(null);
                    }

                    @Override
                    public void onNext(Reservations reservations) {
                        Log.i("Repo reservation", reservations.toString());
                        allReservations.setValue(reservations);
                    }
                });
        return allReservations;
    }
}
