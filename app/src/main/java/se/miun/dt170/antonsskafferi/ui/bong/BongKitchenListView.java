package se.miun.dt170.antonsskafferi.ui.bong;

import android.content.Context;
import android.view.LayoutInflater;

import androidx.constraintlayout.widget.ConstraintLayout;

import se.miun.dt170.antonsskafferi.R;

public class BongKitchenListView extends ConstraintLayout {
    public BongKitchenListView(Context context) {
        super(context);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.bong_kitchen_list, this, true);

    }
}
