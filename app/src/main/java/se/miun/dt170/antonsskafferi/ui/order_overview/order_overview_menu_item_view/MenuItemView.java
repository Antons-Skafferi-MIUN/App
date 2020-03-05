package se.miun.dt170.antonsskafferi.ui.order_overview.order_overview_menu_item_view;

import android.content.Context;
import android.text.Layout;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;

import se.miun.dt170.antonsskafferi.R;
import se.miun.dt170.antonsskafferi.ui.order_overview.OrderOverviewFragment;

public class MenuItemView extends CardView {

    public MenuItemView(@NonNull Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public MenuItemView(@NonNull Context context, String menuItemName, String menuItemPrice) {
        super(context);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.menu_item_view, this, true);

        TextView menuItemNameTextView = this.findViewById(R.id.menuItemName);
        menuItemNameTextView.setText(menuItemName);

        TextView menuItemPriceTextView = this.findViewById(R.id.menuItemPrice);
        menuItemPriceTextView.setText(menuItemPrice);

        setId(R.id.menuItemView);
    }

    public MenuItemView(@NonNull Context context, String drinkItemName, String drinktemPrice, String drinktemType) {
        super(context);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.menu_item_view, this, true);

        TextView menuItemNameTextView = this.findViewById(R.id.menuItemName);
        menuItemNameTextView.setText(drinkItemName);

        TextView menuItemPriceTextView = this.findViewById(R.id.menuItemPrice);
        menuItemPriceTextView.setText(drinktemPrice);

        //TEMP if we want to show type of bottle in menu also
        //TextView menuItemTypeTextView = this.findViewById(R.id.menuItemType);
        //menuItemTypeTextView.setText(drinktemType);

        setId(R.id.menuItemView);
    }



}
