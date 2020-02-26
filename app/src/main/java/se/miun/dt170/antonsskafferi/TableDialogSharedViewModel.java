package se.miun.dt170.antonsskafferi;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class TableDialogSharedViewModel extends ViewModel {
    private MutableLiveData<String> tableColor = new MutableLiveData<>();
    private MutableLiveData<Integer> availableSeats = new MutableLiveData<>();
    private MutableLiveData<Boolean> isTableOpen = new MutableLiveData<>();

    public MutableLiveData<Boolean> getIsTableOpen() {
        return isTableOpen;
    }

    public void setIsTableOpen(Boolean isTableOpen) {
        this.isTableOpen.setValue(isTableOpen);
    }

    public MutableLiveData<Integer> getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats.setValue(availableSeats);
    }

    public void setTableColor(String tableColor){
        this.tableColor.setValue(tableColor);
    }


    public MutableLiveData<String> getTableColor(){
        return tableColor;
    }

}
