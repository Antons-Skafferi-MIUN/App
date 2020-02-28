package se.miun.dt170.antonsskafferi.ui.order_overview.order_overview_bong;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import se.miun.dt170.antonsskafferi.R;

public class OrderBongListView extends LinearLayout {
    public OrderBongListView(Context context) {
        super(context);
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        layoutInflater.inflate(R.layout.order_bong_list_view, this, true);
    }
}
