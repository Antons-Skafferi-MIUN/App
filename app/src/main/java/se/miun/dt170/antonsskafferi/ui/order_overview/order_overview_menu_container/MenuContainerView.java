package se.miun.dt170.antonsskafferi.ui.order_overview.order_overview_menu_container;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;

import java.util.List;

import se.miun.dt170.antonsskafferi.R;
import se.miun.dt170.antonsskafferi.data.MenuItem;
import se.miun.dt170.antonsskafferi.data.XMLParser;
import se.miun.dt170.antonsskafferi.ui.order_overview.menu_category_view.MenuCategoryView;
import se.miun.dt170.antonsskafferi.ui.order_overview.menu_category_view.MenuListViewModel;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class MenuContainerView extends ScrollView {
    /*public MenuContainerView(Context context) {
        super(context);
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        layoutInflater.inflate(R.layout.menu_container_view, this, true);
    }*/
    public MenuContainerView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
     //   LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
       // layoutInflater.inflate(R.layout.menu_container_view, this, true);

    }

}
