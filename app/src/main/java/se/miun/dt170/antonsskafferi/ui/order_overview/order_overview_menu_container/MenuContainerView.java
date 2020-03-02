package se.miun.dt170.antonsskafferi.ui.order_overview.order_overview_menu_container;

import android.content.Context;
import android.util.Log;
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

public class MenuContainerView extends ScrollView implements LifecycleObserver {

    public MenuContainerView(Context context, LifecycleOwner lifecycle) {
        super(context);
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        layoutInflater.inflate(R.layout.menu_container_view, this, true);

        Log.d(TAG, "KÖR NU----MenuContainerView: ");

        /////Denna borde lägga till alla Category yiews (Förrätt, Varmrätt, Desert) från en lista
        //3 st views categorys borde addas aka includes som finns i xml layouten?

        //foodname = findViewById(R.id.foodnameTextview);
        //foodname.setName(menuitem);


        //List<MenuItem> menuItemList;

        //XMLParser xmlparser = new XMLParser();
        //menuItemList = xmlparser.getMenuitemlist();
        MenuItem menuitem = new MenuItem();
        MenuItem menuitem2 = new MenuItem();
        menuitem.setCategory("Varmrätter");
        menuitem.setCost("199");
        menuitem.setTitle("Köttfiskar med Glass");
        menuitem2.setCategory("Dessert");
        menuitem2.setCost("199");
        menuitem2.setTitle("Glass");

        //MenuCategoryView menuCategoryView = new MenuCategoryView(context, menuitem);

        LinearLayout menuCategoryContainerLinearLayout = findViewById(R.id.menuCategoryContainerLinearLayout);

        menuCategoryContainerLinearLayout.addView(new MenuCategoryView(getContext(), menuitem));
        menuCategoryContainerLinearLayout.addView(new MenuCategoryView(getContext(), menuitem2));
        menuCategoryContainerLinearLayout.addView(new MenuCategoryView(getContext(), menuitem2));

        MenuListViewModel menuListViewModel = new MenuListViewModel();

        // Populate category list on changes to the menu list view model
       /*
        menuListViewModel.categoryItems.observe(lifecycle, items -> {
            for (MenuItem parsedmenuitems : items) {
                menuCategoryContainerLinearLayout.addView(new MenuCategoryView(getContext(), parsedmenuitems));
            }
        });


        */






    }

}
