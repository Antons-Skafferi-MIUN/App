package se.miun.dt170.antonsskafferi.ui.order_overview.menu_category_view;

import androidx.lifecycle.LiveData;

import java.util.List;

import se.miun.dt170.antonsskafferi.data.MenuItemRepository;
import se.miun.dt170.antonsskafferi.data.Item;

public class CategoryListViewModel
{

    public LiveData<List<Item>> categoryItems;

    public CategoryListViewModel() {

        MenuItemRepository menuItemRepository = new MenuItemRepository();
        //menuItemRepository = MenuItemRepository();
    }

}
