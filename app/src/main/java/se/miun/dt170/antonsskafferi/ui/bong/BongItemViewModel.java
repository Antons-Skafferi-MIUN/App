package se.miun.dt170.antonsskafferi.ui.bong;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class BongItemViewModel extends ViewModel {

    class Item {
        String Name;
        List<String> extras;

        public Item(String name, List<String> extras) {
            Name = name;
            this.extras = extras;
        }
    }

    private MutableLiveData<List<Item>> items;

    public LiveData<List<Item>> getItems() {
        if (items == null) {
            items = new MutableLiveData<>();
            loadUsers();
        }
        return items;
    }

    private void loadUsers() {
        //TODO: Do an asynchronous operation to fetch items.
        List<Item> itemList = new ArrayList<>();
        itemList.add(new Item("Köttbullar", null));
        items.setValue(itemList);
    }
}
