package se.miun.dt170.antonsskafferi.ui.table_overview;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;

import se.miun.dt170.antonsskafferi.R;

public class TableView extends ConstraintLayout {
    private boolean isTableBooked = false;
    private boolean isTableOpen = false;
    public TableView(@NonNull Context context) {
        super(context);
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        layoutInflater.inflate(R.layout.table_view, this, true);
    }

    public TableView(@NonNull Context context, AttributeSet attrs) {
        super(context,attrs);
    }

    public void setButtonColor(int color){
        Button butt = findViewById(R.id.tableButton);
        butt.setBackgroundColor(color);
    }

    public boolean isTableBooked() {
        return isTableBooked;
    }

    public void setTableBooked(boolean tableBooked) {
        isTableBooked = tableBooked;
    }

    public boolean isTableOpen() {
        return isTableOpen;
    }

    public void setTableOpen(boolean tableOpen) {
        isTableOpen = tableOpen;
    }
}
