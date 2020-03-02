package se.miun.dt170.antonsskafferi.ui.order_overview.menu_category_view;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import se.miun.dt170.antonsskafferi.data.MenuItem;
import se.miun.dt170.antonsskafferi.data.MenuItemRepository;
import se.miun.dt170.antonsskafferi.data.Item;

import static androidx.constraintlayout.widget.Constraints.TAG;

//new file

public class MenuListViewModel extends ViewModel
{

    public LiveData<List<MenuItem>> categoryItems;

    public MenuListViewModel() {

        //Collect category items
        Log.d(TAG, "MenuListViewModel: ");
        MenuItemRepository menuItemRepository = new MenuItemRepository();
        Log.d(TAG, "MenuListViewModel2: ");
        categoryItems = menuItemRepository.getItems();
    }

}
