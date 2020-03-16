package se.miun.dt170.antonsskafferi.ui.dialog;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.HashSet;
import java.util.Set;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import se.miun.dt170.antonsskafferi.data.APIWrappers.DeleteWrapper;
import se.miun.dt170.antonsskafferi.data.model.OrderRows;
import se.miun.dt170.antonsskafferi.data.model.Reservations;
import se.miun.dt170.antonsskafferi.data.remote.ApiService;
import se.miun.dt170.antonsskafferi.data.remote.ApiUtils;
import se.miun.dt170.antonsskafferi.data.repository.ReservationRepository;

/**
 * Data container for {@link TableDialogFragment}
 */
public class TableDialogViewModel extends ViewModel {

    private ApiService mAPIService;
    private DeleteWrapper deleteWrapper;
    private ReservationRepository reservationRepository;
    private MutableLiveData<Reservations> allReservations;
    private Set<String> ordersToRemoveFromKitchen;

    public TableDialogViewModel() {
        mAPIService = ApiUtils.getAPIService();
        deleteWrapper = new DeleteWrapper();
        reservationRepository = new ReservationRepository();
        ordersToRemoveFromKitchen = new HashSet<>();
    }

    public MutableLiveData<Reservations> getAllReservations() {
//        if (allReservations == null) {
        allReservations = reservationRepository.getAllReservations();
//        }
        return allReservations;
    }

    public void updateData() {
        allReservations.setValue(reservationRepository.getAllReservations().getValue());
    }

    public void clearOrderSet() {
        ordersToRemoveFromKitchen.clear();
    }

    public void clearCurrentOrderFromDatabase(int tableNr) {

        mAPIService.getOrderRows()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<OrderRows>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(OrderRows orderRows) {
                        Log.i("ORDER ROW ", "AT TOP");
                        Set<String> orderRowsForDeleting = new HashSet<>();

                        orderRows.getOrderRows().forEach(orderRow -> {

                            int tableThatContainsOrderRow = Integer.parseInt(orderRow.getOrderId().getTableId().getTableId());
                            if (tableThatContainsOrderRow == tableNr) {
                                orderRowsForDeleting.add(orderRow.getOrderRowId());
                                ordersToRemoveFromKitchen.add(orderRow.getOrderId().getOrderId());
                            }
                        });
                        deleteWrapper.deleteAllOrderRows(orderRowsForDeleting, ordersToRemoveFromKitchen);
                    }
                });
    }
}
