package se.miun.dt170.antonsskafferi.ui.bong;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import se.miun.dt170.antonsskafferi.data.Item;

/**
 * Data container for {@link BongItemView}
 */
public class BongItemViewModel extends ViewModel {

    private MutableLiveData<Item> item;

    public LiveData<Item> getItem() {
        if (item == null) {
            item = new MutableLiveData<>();
            loadItem();
        }
        return item;
    }

    private void loadItem() {
        //TODO: Do an asynchronous operation to fetch items.
        List<String> extrasList = new ArrayList<>();
        extrasList.add("- Sås");

        Item newItem = new Item("Pasta Carbonara", extrasList);
        item.setValue(newItem);
    }
}
