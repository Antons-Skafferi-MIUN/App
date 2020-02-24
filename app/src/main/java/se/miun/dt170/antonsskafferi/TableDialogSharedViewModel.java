package se.miun.dt170.antonsskafferi;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class TableDialogSharedViewModel extends ViewModel {
    private MutableLiveData<CharSequence> tableColor = new MutableLiveData<>();

    public void setTableColor(CharSequence _tableColor){
        tableColor.setValue(_tableColor);
    }
    public MutableLiveData<CharSequence> getTableColor(){ // change to string??
        return tableColor;
    }

}
