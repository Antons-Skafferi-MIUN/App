package se.miun.dt170.antonsskafferi.ui.dialog;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
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

    public TableDialogViewModel(){
        mAPIService = ApiUtils.getAPIService();
        deleteWrapper = new DeleteWrapper();
    }

    public void clearCurrentOrderFromDatabase(int tableNr){
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
                        int orderID = -1;
                        for (OrderRow orderRow : allOrderRows) {
                            int orderRowTableID = Integer.parseInt(orderRow.getOrderId().getTableId().getTableId());
                            if (orderRowTableID == tableNr) {
                                orderID = Integer.parseInt(orderRow.getOrderId().getOrderId());
                                Log.i("ORDER ID", orderRow.getOrderId().getOrderId());
                                Log.i("ORDER TABLE", orderRow.getOrderId().getTableId().getTableId());
                                deleteWrapper.deleteOrderRow(orderRowTableID);
                            }
                        }
                        if (orderID != -1) {
                            deleteWrapper.deleteOrder(orderID);
                        }
                    }

                });
    }
}
