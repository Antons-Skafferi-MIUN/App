package se.miun.dt170.antonsskafferi.data;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

import se.miun.dt170.antonsskafferi.ui.bong.BongItemView;

public class ItemRepository {

    private MutableLiveData<List<Item>> bongItems;
    private MutableLiveData<List<Item>> drinkItems;

    public LiveData<List<Item>> getItems() {
        if (bongItems == null) {
            bongItems = new MutableLiveData<>();
            loadItems();
        }
        return bongItems;
    }

    public LiveData<List<Item>> getDrinks() {
        if (drinkItems == null) {
            drinkItems = new MutableLiveData<>();
            loadDrinks();
        }
        return drinkItems;
    }

    private void loadItems() {
        //TODO: Do an asynchronous operation to fetch items.
        List<String> extrasList1 = new ArrayList<>();
        List<String> extrasList2 = new ArrayList<>();
        extrasList1.add("- Sås");
        extrasList2.add("- Gluten");
        extrasList2.add("+ Extra gurka");

        List<Item> itemList = new ArrayList<>();
        itemList.add(new Item("Köttbullar", null));
        itemList.add(new Item("Pasta Carbonara", extrasList1));
        itemList.add(new Item("Kycklingtallrik", null));
        itemList.add(new Item("Kycklingtallrik", extrasList2));
        bongItems.setValue(itemList);
    }

    private void loadDrinks() {
        //TODO: Do an asynchronous operation to fetch items.
        List<String> extrasList1 = new ArrayList<>();
        List<String> extrasList2 = new ArrayList<>();
        List<String> extrasList3 = new ArrayList<>();
        extrasList1.add("119:-");
        extrasList2.add("229:-");
        extrasList3.add("99:-");

        List<Item> itemList = new ArrayList<>();
        itemList.add(new Item("White Russian", extrasList1));
        itemList.add(new Item("San Fransisco", extrasList2));
        itemList.add(new Item("Pepsi", extrasList3));

        drinkItems.setValue(itemList);
    }
}
