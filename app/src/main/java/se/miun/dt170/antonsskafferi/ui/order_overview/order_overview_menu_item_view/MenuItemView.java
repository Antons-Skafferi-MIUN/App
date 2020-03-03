package se.miun.dt170.antonsskafferi.ui.order_overview.order_overview_menu_item_view;

import android.content.Context;
import android.text.Layout;
import android.util.AttributeSet;
import android.view.LayoutInflater;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;

import se.miun.dt170.antonsskafferi.R;
import se.miun.dt170.antonsskafferi.ui.order_overview.OrderOverviewFragment;

public class MenuItemView extends CardView {
    private OrderOverviewFragment orderOverviewFragment;
    /*public OrderOverviewMenuItemView(@NonNull Context context) {
        super(context);
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        layoutInflater.inflate(R.layout.menu_item_view, this, true);
    }*/

    public MenuItemView(@NonNull Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        //orderOverviewFragment = findViewById(R.id.orderOverviewFragment);
        //this.setOnClickListener(eventHandler);
        //LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //layoutInflater.inflate(R.layout.menu_item_view, this, true);
    }
}
