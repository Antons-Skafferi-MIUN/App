package se.miun.dt170.antonsskafferi.ui.dialog;

import android.util.Log;
import android.view.ViewDebug;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import se.miun.dt170.antonsskafferi.activity.RestaurantSharedViewModel;
import se.miun.dt170.antonsskafferi.data.APIWrappers.DeleteWrapper;
import se.miun.dt170.antonsskafferi.data.model.OrderRow;
import se.miun.dt170.antonsskafferi.data.model.OrderRows;
import se.miun.dt170.antonsskafferi.data.remote.ApiService;
import se.miun.dt170.antonsskafferi.data.remote.ApiUtils;

/**
 * Data container for {@link TableDialogFragment}
 */
public class TableDialogViewModel extends ViewModel {
    private ApiService mAPIService;
    private DeleteWrapper deleteWrapper;

    public Set<String> getOrdersToRemoveFromKitchen() {
        return ordersToRemoveFromKitchen;
    }

    Set<String> ordersToRemoveFromKitchen;

    public TableDialogViewModel() {
        mAPIService = ApiUtils.getAPIService();
        deleteWrapper = new DeleteWrapper();
        ordersToRemoveFromKitchen = new HashSet<>();
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
                        ArrayList<OrderRow> allOrderRows = orderRows.getOrderRows();
                        String orderID = "-1";


                        for (OrderRow orderRow : allOrderRows) {
                            int tableThatContainsOrderRow = Integer.parseInt(orderRow.getOrderId().getTableId().getTableId());

                            if (tableThatContainsOrderRow == tableNr) {
                                orderID = orderRow.getOrderId().getOrderId(); //order ID that belongs to the current order row.
                                ordersToRemoveFromKitchen.add(orderID);
                                String orderRowID = orderRow.getOrderRowId(); // ID of the current orderrow
                                Log.i("ORDER ID", orderRow.getOrderId().getOrderId());
                                Log.i("DELETING ORDER ROW", orderRow.getOrderRowId());
                                deleteWrapper.deleteOrderRow(orderRowID);
                            }

                        }

                        ordersToRemoveFromKitchen.forEach(currentOrderID -> {
                            if (!currentOrderID.equals("-1")) {
                                Log.i("DELETING ORDER", "" + currentOrderID);
                                deleteWrapper.deleteOrder(currentOrderID);

                            }
                        } );
                    }
                });
    }
}
