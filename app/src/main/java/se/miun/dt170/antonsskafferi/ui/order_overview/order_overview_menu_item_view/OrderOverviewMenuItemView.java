package se.miun.dt170.antonsskafferi.ui.order_overview.order_overview_menu_item_view;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;

import se.miun.dt170.antonsskafferi.R;

public class OrderOverviewMenuItemView extends CardView {
    public OrderOverviewMenuItemView(@NonNull Context context) {
        super(context);
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        layoutInflater.inflate(R.layout.order_overview_menu_container_fragment, this, true);
    }
}
