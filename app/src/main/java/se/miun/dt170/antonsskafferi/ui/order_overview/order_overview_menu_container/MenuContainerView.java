package se.miun.dt170.antonsskafferi.ui.order_overview.order_overview_menu_container;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import se.miun.dt170.antonsskafferi.R;
import se.miun.dt170.antonsskafferi.ui.order_overview.menu_category_view.MenuCategoryView;

public class MenuContainerView extends ScrollView {

    public MenuContainerView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void addCategory(String categoryName) {
        MenuCategoryView menuCategoryView = new MenuCategoryView(getContext(), categoryName);
        LinearLayout menuContainerLayout = this.findViewById(R.id.menuContainerLayout);
        menuContainerLayout.addView(menuCategoryView);
    }
}
