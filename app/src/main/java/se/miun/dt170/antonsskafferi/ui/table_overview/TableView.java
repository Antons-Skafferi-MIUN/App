package se.miun.dt170.antonsskafferi.ui.table_overview;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;

import se.miun.dt170.antonsskafferi.R;

public class TableView extends ConstraintLayout {

    public TableView(@NonNull Context context) {
        super(context);
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        layoutInflater.inflate(R.layout.table_view, this, true);

    }

    public void setButtonColor(){
        Button butt = findViewById(R.id.tableButton);
        butt.setText("ASDASDASDASDASd");
    }
}
