package se.miun.dt170.antonsskafferi.data;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

import se.miun.dt170.antonsskafferi.ui.bong.BongItemView;

public class ItemRepository {

    private MutableLiveData<List<Item>> bongItems;

    public LiveData<List<Item>> getItems() {
        if (bongItems == null) {
            bongItems = new MutableLiveData<>();
            loadItems();
        }
        return bongItems;
    }

    private void loadItems() {
        //TODO: Do an asynchronous operation to fetch items.
        List<String> extrasList = new ArrayList<>();
        extrasList.add("- Sås");

        List<Item> itemList = new ArrayList<>();
        itemList.add(new Item("Köttbullar", null));
        itemList.add(new Item("Pasta Carbonara", extrasList));
        bongItems.setValue(itemList);
    }
}
