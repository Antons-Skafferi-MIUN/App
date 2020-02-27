package se.miun.dt170.antonsskafferi;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class TableDialogSharedViewModel extends ViewModel {
    private MutableLiveData<String> tableColor = new MutableLiveData<>();
    private MutableLiveData<Boolean> isTableOpen = new MutableLiveData<>(false);
    private MutableLiveData<Boolean> isTableBooked = new MutableLiveData<>(false);

    public MutableLiveData<Boolean> getIsTableBooked() {
        return isTableBooked;
    }

    public void setIsTableBooked(Boolean isTableBooked) {
        this.isTableBooked.setValue(isTableBooked);
    }



    public MutableLiveData<Boolean> getIsTableOpen() {
        return isTableOpen;
    }

    public void setIsTableOpen(Boolean isTableOpen) {
        this.isTableOpen.setValue(isTableOpen);
    }


    public void setTableColor(String tableColor){
        this.tableColor.setValue(tableColor);
    }


    public MutableLiveData<String> getTableColor(){
        return tableColor;
    }

}
