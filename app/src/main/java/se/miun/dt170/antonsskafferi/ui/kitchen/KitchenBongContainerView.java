package se.miun.dt170.antonsskafferi.ui.kitchen;

import android.content.Context;
import android.view.LayoutInflater;

import androidx.constraintlayout.widget.ConstraintLayout;

import se.miun.dt170.antonsskafferi.R;

public class KitchenBongContainerView extends ConstraintLayout {
    public KitchenBongContainerView(Context context) {
        super(context);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.kitchen_bong_container_view, this, true);

    }
}
