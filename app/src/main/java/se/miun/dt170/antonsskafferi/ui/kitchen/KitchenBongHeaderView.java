package se.miun.dt170.antonsskafferi.ui.kitchen;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import se.miun.dt170.antonsskafferi.R;
import se.miun.dt170.antonsskafferi.data.DateConverter;
import se.miun.dt170.antonsskafferi.data.model.Order;

public class KitchenBongHeaderView extends ConstraintLayout implements View.OnClickListener {

    private View bongHeaderView;
    private CheckBox bongHeaderCheckbox;
    private TextView tableNumber;
    private TextView orderTime;
    private boolean headerClicked = false;

    public KitchenBongHeaderView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public KitchenBongHeaderView(Context context, Order order) {
        super(context);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        bongHeaderView = inflater.inflate(R.layout.kitchen_bong_header_view, this, true);

        DateConverter dateConverter = new DateConverter();

        tableNumber = bongHeaderView.findViewById(R.id.tableText);
        orderTime = bongHeaderView.findViewById(R.id.timeText);
        bongHeaderCheckbox = bongHeaderView.findViewById(R.id.foodCheck);

        tableNumber.setText(String.format("Bord %s", order.getTableId().getTableId()));
        orderTime.setText(dateConverter.formatHHMM(order.getOrderTime()));

        bongHeaderCheckbox.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.foodCheck:
                setHeaderClicked();
                break;

        }
    }

    public void setHeaderClicked() {
        if (!headerClicked) {
            tableNumber.setTextColor(Color.parseColor("#00cc00"));
            orderTime.setTextColor(Color.parseColor("#00cc00"));
            headerClicked = true;
        } else {
            tableNumber.setTextColor(Color.parseColor("#000000"));
            orderTime.setTextColor(Color.parseColor("#000000"));
            headerClicked = false;
        }
    }
}
