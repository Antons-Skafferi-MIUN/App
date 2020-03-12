package se.miun.dt170.antonsskafferi.ui.table_overview;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import se.miun.dt170.antonsskafferi.R;
import se.miun.dt170.antonsskafferi.data.model.OrderRows;
import se.miun.dt170.antonsskafferi.data.remote.ApiService;
import se.miun.dt170.antonsskafferi.data.remote.ApiUtils;

public class TableView extends ConstraintLayout {
    private boolean isTableBooked = false;
    private boolean isTableOpen = true;
    private TextView textView;
    private Button tableButton;
    private int tableNr;
    private int tableAvailableColor;
    private int tableBookedColor;
    private int tableOCcupiedColor;
    private int tableTextColor;
    private String dialogText = "";
    private String reservationID;
    private ApiService mAPIService;
    private boolean returnStatus = false;

    public TableView(@NonNull Context context) {
        super(context);
    }

    public TableView(@NonNull Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public int getTableNr() {
        return tableNr;
    }

    public void setTableNr(int tableNr) {
        this.tableNr = tableNr;
    }

    public int getTableAvailableColor() {
        return tableAvailableColor;
    }

    public int getTableOccupiedColor() {

        return tableOCcupiedColor; }

    public int getTableBookedColor() {
        return tableBookedColor;
    }


    @Override
    public void onFinishInflate() {
        super.onFinishInflate();
        tableBookedColor = ContextCompat.getColor(this.getContext(), R.color.table_overview_orange);
        tableAvailableColor = ContextCompat.getColor(this.getContext(), R.color.table_overview_green);
        tableOCcupiedColor = ContextCompat.getColor(this.getContext(), R.color.table_overview_red);
        tableTextColor = ContextCompat.getColor(this.getContext(), R.color.table_overview_text_dark);
        tableButton = findViewById(R.id.tableButton);
        textView = findViewById(R.id.arrivalTime);
        textView.setTextColor(tableTextColor);
        setButtonColor(tableAvailableColor);
        setArrivalTime("");
    }


    /**
     * Sets up a table with default values.
     *
     * @param tablenr The number for the table.
     */
    public void setup(int tablenr) {
        setTableButtonText("Bord " + tablenr);
        setTableNr(tablenr);
    }

    /**
     * Sets the color of a table.
     */

    public void addBookedStatus() {
        setButtonColor(getTableBookedColor());
        setTableBooked(true);
    }

    public void addOccupiedStatus() {
        setButtonColor(getTableOccupiedColor());
        setTableOpen(false);
    }


    public void removeOccupiedStatus() {
        setButtonColor(getTableAvailableColor());
        setTableOpen(true);
    }

    public void removeBookedStatus() {
        setButtonColor(getTableAvailableColor());
        setDialogText("Bord " + getTableNr());
        setTableBooked(false);
        setArrivalTime("");
    }

    public void setButtonColor(int color) {
        tableButton.setBackgroundColor(color);
    }



    public boolean isTableBooked() {
        return isTableBooked;
    }

    public void setTableBooked(boolean tableBooked) {
        isTableBooked = tableBooked;
    }

    public void setTableOpen(boolean tableOpen) {
        isTableOpen = tableOpen;
    }

    public boolean isTableOpen() {
        return isTableOpen;
    }

    public void setArrivalTime(String time) {
        this.textView.setText(time);
    }

    public void setTableButtonText(String buttonText) {
        this.tableButton.setText(buttonText);
    }

    public String getDialogText() {
        return dialogText;
    }

    public void setDialogText(String dialogText) {
        this.dialogText = dialogText;
    }

    public String getReservationID() {
        return reservationID;
    }

    public void setReservationID(String reservationID) {
        this.reservationID = reservationID;
    }

    public boolean hasOrders() {
        returnStatus = false;
        mAPIService = ApiUtils.getAPIService();
        mAPIService.getOrderRows().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<OrderRows>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("Retrofit RxJava", e.toString());
                    }

                    // Called on every new observed item
                    @Override
                    public void onNext(OrderRows response) {
                        response.getOrderRows().forEach(orderRow -> {
                            String orderTableID = orderRow.getOrderId().getTableId().getTableId();

                            if (Integer.parseInt(orderTableID) == getTableNr()) {
                                returnStatus = true;
                            }
                        });
                        Log.i("Retrofit RxJava", "get submitted to API." + response.toString());
                    }
                });
        return returnStatus;
    }
}
