package se.miun.dt170.antonsskafferi.ui.order_overview.menu_category_view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.google.android.flexbox.FlexboxLayout;

import java.util.ArrayList;

import se.miun.dt170.antonsskafferi.R;
import se.miun.dt170.antonsskafferi.data.model.Drink;
import se.miun.dt170.antonsskafferi.data.model.Food;
import se.miun.dt170.antonsskafferi.data.model.MenuItem;
import se.miun.dt170.antonsskafferi.ui.order_overview.order_overview_menu_item_view.MenuItemView;

public class MenuCategoryView extends LinearLayout {

    public MenuCategoryView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    //Fills Alacarte
   public MenuCategoryView(@NonNull Context context, String categoryName, ArrayList<MenuItem> internalItemList){
       super(context);

       LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
       inflater.inflate(R.layout.menu_category_view, this, true);

       TextView textView = this.findViewById(R.id.menuCategoryNameTextView);
       textView.setText(categoryName);

       //Add menu items from foodList
       for (MenuItem menuItem : internalItemList){
           MenuItemView menuItemView = new MenuItemView(getContext(), menuItem);
           FlexboxLayout menuContainerLayout = this.findViewById(R.id.menuCategoryFlexbox);
           menuContainerLayout.addView(menuItemView);
       }

   }




    public MenuCategoryView(@NonNull Context context, String categoryName) {
        super(context);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.menu_category_view, this, true);

        TextView textView = this.findViewById(R.id.menuCategoryNameTextView);
        textView.setText(categoryName);
    }



}