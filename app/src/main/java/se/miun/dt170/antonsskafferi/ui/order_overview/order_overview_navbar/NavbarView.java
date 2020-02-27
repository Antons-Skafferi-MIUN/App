package se.miun.dt170.antonsskafferi.ui.order_overview.order_overview_navbar;

import android.content.Context;
import android.view.LayoutInflater;

import androidx.constraintlayout.widget.ConstraintLayout;

import se.miun.dt170.antonsskafferi.R;

public class NavbarView extends ConstraintLayout {
    public NavbarView(Context context) {
        super(context);
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        layoutInflater.inflate(R.layout.navbar_view, this, true);
    }
}
