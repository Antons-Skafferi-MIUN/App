package se.miun.dt170.antonsskafferi.ui.bong;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import se.miun.dt170.antonsskafferi.data.Item;

/**
 * Data container for {@link BongListFragment}
 */
public class BongListViewModel extends ViewModel {
    // TODO: Implement the ViewModel

    private MutableLiveData<List<BongItemView>> bongItems;

    public LiveData<List<BongItemView>> getItems() {
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
//        bongItems.setValue(BongItemFragment.newInstance());
    }
}
