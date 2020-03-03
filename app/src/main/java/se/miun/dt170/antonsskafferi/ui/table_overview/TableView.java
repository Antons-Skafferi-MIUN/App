package se.miun.dt170.antonsskafferi.ui.table_overview;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import se.miun.dt170.antonsskafferi.R;

public class TableView extends ConstraintLayout {
    private boolean isTableBooked = false;
    private boolean isTableOpen = false;
    private TextView textView;
    private Button tableButton;

    public int getTableNr() {
        return tableNr;
    }

    public void setTableNr(int tableNr) {
        this.tableNr = tableNr;
    }

    private int tableNr;
    private int tableAvailableColor;
    private int tableBookedColor;
    private int tableTextColor;

    public int getTableAvailableColor() {
        return tableAvailableColor;
    }

    public int getTableBookedColor() {
        return tableBookedColor;
    }


    public TableView(@NonNull Context context) {
        super(context);
    }

    public TableView(@NonNull Context context, AttributeSet attrs) {
        super(context,attrs);

    }
    @Override
    public void onFinishInflate() {
        super.onFinishInflate();
        tableBookedColor = ContextCompat.getColor(this.getContext(), R.color.table_overview_red);
        tableAvailableColor = ContextCompat.getColor(this.getContext(), R.color.table_overview_green);
        tableTextColor = ContextCompat.getColor(this.getContext(), R.color.table_overview_text_dark);
        tableButton = findViewById(R.id.tableButton);
        textView = findViewById(R.id.arrivalTime);
        textView.setTextColor(tableTextColor);
        setButtonColor(tableAvailableColor);
        setArrivalTime("");
    }


    /**
     * Sets up a table with default values.
     * @param tablenr The number for the table.
     */
    public void setup(int tablenr){
        setTableButtonText("Bord " + Integer.toString(tablenr));
        setTableNr(tablenr);
    }

    /**
     * Sets the color of a table.
     * @param color
     */
    public void setButtonColor(int color){
        tableButton.setBackgroundColor(color);
    }


    public boolean isTableBooked() {
        return isTableBooked;
    }

    public void setTableBooked(boolean tableBooked) {
        isTableBooked = tableBooked;
    }

    public void setArrivalTime(String time) {
        this.textView.setText(time);
    }

    public void setTableButtonText(String buttonText) {
        this.tableButton.setText(buttonText);
    }
}
