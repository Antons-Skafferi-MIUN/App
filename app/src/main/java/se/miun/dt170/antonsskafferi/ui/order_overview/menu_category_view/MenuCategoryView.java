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
import se.miun.dt170.antonsskafferi.data.model.Food;
import se.miun.dt170.antonsskafferi.ui.order_overview.order_overview_menu_item_view.MenuItemView;

public class MenuCategoryView extends LinearLayout {

    public MenuCategoryView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }


   public MenuCategoryView(@NonNull Context context, String categoryName, ArrayList<Food>internalFoodList){
       super(context);

       LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
       inflater.inflate(R.layout.menu_category_view, this, true);

       TextView textView = this.findViewById(R.id.menuCategoryNameTextView);
       textView.setText(categoryName);

       //Add menu items from foodList
       for (Food foodItem : internalFoodList){
           MenuItemView menuItemView = new MenuItemView(getContext(), foodItem.getFoodName(), foodItem.getFoodPrice());
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


        MenuItemView menuItemView = new MenuItemView(getContext(), "Test", "Test111");
        FlexboxLayout menuContainerLayout = this.findViewById(R.id.menuCategoryFlexbox);
        menuContainerLayout.addView(menuItemView);

        MenuItemView menuItemView2 = new MenuItemView(getContext(), "Test2", "Test222");
        menuContainerLayout.addView(menuItemView2);
    }





    public void addMenuItem(String menuItemName, String menuItemPrice) {
        MenuItemView menuItemView = new MenuItemView(getContext(), menuItemName, menuItemPrice);

        //MenuCategoryView menuCategoryView = new MenuCategoryView(getContext(), categoryName);
        FlexboxLayout menuContainerLayout = this.findViewById(R.id.menuCategoryFlexbox);
        menuContainerLayout.addView(menuItemView);
    }

}