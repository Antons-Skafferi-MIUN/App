package se.miun.dt170.antonsskafferi.data;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

public class MenuItemRepository {

    private MutableLiveData<List<MenuItem>> menuItems;

    public LiveData<List<MenuItem>> getItems() {
        if (menuItems == null) {
            menuItems = new MutableLiveData<>();
            loadItems();
        }
        return menuItems;
    }

    private void loadItems() {
        //TODO: Do an asynchronous operation to fetch items.

        List<MenuItem> itemList = new ArrayList<>();
        XMLParser xmlparser = new XMLParser();
        itemList = xmlparser.getMenuitemlist();
        menuItems.setValue(itemList);

    }



}
