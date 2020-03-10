package se.miun.dt170.antonsskafferi.ui.dialog;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
    Map<Integer, ArrayList<OrderRow> > map;

    public TableDialogViewModel(){
        mAPIService = ApiUtils.getAPIService();
        deleteWrapper = new DeleteWrapper();
        map = new HashMap<Integer, ArrayList<OrderRow>>();
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
                            int tableThatContainsOrderRow = Integer.parseInt(orderRow.getOrderId().getTableId().getTableId());
                            int orderRowID;

                            if (tableThatContainsOrderRow == tableNr) {
                                orderID = Integer.parseInt(orderRow.getOrderId().getOrderId()); //order ID that belongs to the current order row.
                                map.put(orderID,null);
                                orderRowID = Integer.parseInt(orderRow.getOrderRowId()); // ID of the current orderrow
                                Log.i("ORDER ID", orderRow.getOrderId().getOrderId());
                                Log.i("DELETING ORDER ROW", orderRow.getOrderRowId());
                                deleteWrapper.deleteOrderRow(orderRowID);
                            }
                            for (Map.Entry<Integer, ArrayList<OrderRow> > entry : map.entrySet()) {
                                Integer key = entry.getKey();
                                Log.i("HASHMAP TEST " , key.toString());
                            }
                        }
                        if (orderID != -1) {
                            Log.i("DELETING ORDER","" + orderID);
                            deleteWrapper.deleteOrder(orderID);
                        }
                    }

                });
    }
}
