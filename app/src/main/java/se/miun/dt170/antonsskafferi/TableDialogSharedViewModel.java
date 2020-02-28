package se.miun.dt170.antonsskafferi;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import se.miun.dt170.antonsskafferi.ui.table_overview.TableView;

public class TableDialogSharedViewModel extends ViewModel {

    private MutableLiveData<TableView> table = new MutableLiveData<>();

    public void setTable(TableView table) {
        this.table.setValue(table);
    }
    public MutableLiveData<TableView> getTable() {
        return table;
    }

}
