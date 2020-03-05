package se.miun.dt170.antonsskafferi.data.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import se.miun.dt170.antonsskafferi.data.model.Reservations;
import se.miun.dt170.antonsskafferi.data.remote.ApiService;
import se.miun.dt170.antonsskafferi.data.remote.ApiUtils;
import se.miun.dt170.antonsskafferi.ui.table_overview.TableOverviewFragment;

public class ReservationRepository {
    private ApiService mAPIService;
    private MutableLiveData<Reservations> reservations;

    public ReservationRepository() {
        mAPIService = ApiUtils.getAPIService();
    }

    public MutableLiveData<Reservations> getReservations(TableOverviewFragment fragmentView) {
        if (reservations == null) {
            reservations = new MutableLiveData<>();
        }
        mAPIService.getReservations()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Reservations>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("ERROR IN getReservationS", e.toString());
                    }

                    // Called on every new observed item
                    @Override
                    public void onNext(Reservations response) {
                        fragmentView.updateFragment(response);
                    }
                });

        return reservations;
    }
}
