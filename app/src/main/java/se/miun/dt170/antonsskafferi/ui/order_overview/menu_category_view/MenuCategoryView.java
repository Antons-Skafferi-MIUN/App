package se.miun.dt170.antonsskafferi.ui.order_overview.menu_category_view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.google.android.flexbox.FlexboxLayout;

import se.miun.dt170.antonsskafferi.R;
import se.miun.dt170.antonsskafferi.data.model.Drink;
import se.miun.dt170.antonsskafferi.data.model.Food;
import se.miun.dt170.antonsskafferi.data.model.MenuItem;
import se.miun.dt170.antonsskafferi.ui.order_overview.order_overview_menu_item_view.MenuItemView;

public class MenuCategoryView extends LinearLayout {

    public MenuCategoryView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public MenuCategoryView(@NonNull Context context, String categoryName) {
        super(context);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.menu_category_view, this, true);

        TextView textView = this.findViewById(R.id.menuCategoryNameTextView);
        textView.setText(categoryName);

        Food food = new Food("1", "129", "Lasagne", "Mat");
        Drink drink = new Drink("2", "79", "Skräp", "Oboy", "Dryck");

        MenuItemView menuItemView = new MenuItemView(getContext(), food);
        MenuItemView menuItemView1 = new MenuItemView(getContext(), drink);
        FlexboxLayout menuContainerLayout = this.findViewById(R.id.menuCategoryFlexbox);
        menuContainerLayout.addView(menuItemView);
        menuContainerLayout.addView(menuItemView1);
    }

    public void addMenuItem(MenuItem menuItem) {
        //MenuItemView menuItemView = new MenuItemView(getContext(), menuItemName, menuItemPrice);

        //MenuCategoryView menuCategoryView = new MenuCategoryView(getContext(), categoryName);
        FlexboxLayout menuContainerLayout = this.findViewById(R.id.menuCategoryFlexbox);
        //menuContainerLayout.addView(menuItemView);
    }

}