package se.miun.dt170.antonsskafferi.ui.order_overview.order_overview_menu_container;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import java.util.ArrayList;

import se.miun.dt170.antonsskafferi.R;
import se.miun.dt170.antonsskafferi.data.model.Food;
import se.miun.dt170.antonsskafferi.ui.order_overview.menu_category_view.MenuCategoryView;

public class MenuContainerView extends ScrollView {

    private String foodCategory;
    private ArrayList<String>categoryList = new ArrayList<String>();
    private ArrayList<Food>internalFoodList;

    public MenuContainerView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void addCategory(String categoryName) {
        MenuCategoryView menuCategoryView = new MenuCategoryView(getContext(), categoryName);
        LinearLayout menuContainerLayout = this.findViewById(R.id.menuContainerLayout);
        menuContainerLayout.addView(menuCategoryView);
    }

    public void addCategory(String categoryName, ArrayList<Food>internalFoodList) {
        MenuCategoryView menuCategoryView = new MenuCategoryView(getContext(), categoryName, internalFoodList);
        LinearLayout menuContainerLayout = this.findViewById(R.id.menuContainerLayout);
        menuContainerLayout.addView(menuCategoryView);

    }


    public void createMenuCategories(ArrayList<Food> foodList) {

        // Go through the food list
        for (Food foodItem : foodList) {
            // Get the current category
            foodCategory = foodItem.getFoodCategory();

            // Check if that category has been handled before and if not:
            if (!categoryList.contains(foodCategory)) {
                // Add that category in the category list
                categoryList.add(foodCategory);
            }
        }

        // Go through all categories in the category list
        for (String category : categoryList) {
            // Create a new food list for each category
            internalFoodList = new ArrayList<>();

            // Go through all the foods again and..
            for (Food foodItem : foodList) {

                // If they match the category, add them to the food list
                if (category.equals(foodItem.getFoodCategory())) {
                    internalFoodList.add(foodItem);
                }
            }

            // Create a category based on the current category name and all it's food
            addCategory(category, internalFoodList);
        }
    }


}

