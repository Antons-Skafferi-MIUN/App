package se.miun.dt170.antonsskafferi.ui.bong;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import se.miun.dt170.antonsskafferi.R;

/**
 * This is the container for the list of {@link BongItemView}.
 * Displayed to the right in the order {@link se.miun.dt170.antonsskafferi.ui.order_overview.OrderOverviewFragment}.
 * Displayed in the {@link se.miun.dt170.antonsskafferi.activity.KitchenActivity}.
 */
public class BongListView extends LinearLayout {

    public BongListView(Context context) {
        super(context);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.bong_list_view, this, true);

        setOrientation(VERTICAL); // VERY IMPORTANT
    }
}
