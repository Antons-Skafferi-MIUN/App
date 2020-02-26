package se.miun.dt170.antonsskafferi;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class TableDialogSharedViewModel extends ViewModel {
    private MutableLiveData<String> tableColor = new MutableLiveData<>();
    private MutableLiveData<Integer> availableSeats = new MutableLiveData<>();

    public MutableLiveData<Integer> getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(Integer availableSeats) {
        this.availableSeats.setValue(availableSeats);
    }


    public void setTableColor(String tableColor){
        this.tableColor.setValue(tableColor);
    }


    public MutableLiveData<String> getTableColor(){
        return tableColor;
    }

}
