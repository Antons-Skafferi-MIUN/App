package se.miun.dt170.antonsskafferi.ui.bong;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import se.miun.dt170.antonsskafferi.R;

/**
 * This is the container for the list of {@link BongItemView}.
 * Displayed to the right in the order {@link se.miun.dt170.antonsskafferi.ui.order_overview.OrderOverviewFragment}.
 * Displayed in the {@link se.miun.dt170.antonsskafferi.activity.KitchenActivity}.
 */
public class BongListView extends LinearLayout {
    private Integer numberOfItems = 0;
    private Integer checkedItems = 0;

    public BongListView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public BongListView(Context context) {
        super(context);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.bong_list_view, this, true);

        setOrientation(VERTICAL); // VERY IMPORTANT
    }

    public void raiseNumberOfItems() {
        numberOfItems++;
    }

    public void raiseCheckedItems() {
        checkedItems++;
    }

    public void reduceNumberOfItems() {
        numberOfItems--;
    }

    public void reduceCheckedItems() {
        checkedItems--;
    }

    public Integer getNumberOfItems() {
        return numberOfItems;
    }

    public Integer getCheckedItems() {
        return checkedItems;
    }
}
