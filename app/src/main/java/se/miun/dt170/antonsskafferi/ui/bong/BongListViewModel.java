package se.miun.dt170.antonsskafferi.ui.bong;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import se.miun.dt170.antonsskafferi.data.Item;
import se.miun.dt170.antonsskafferi.data.ItemRepository;

/**
 * Data container for {@link BongListView}
 */
public class BongListViewModel extends ViewModel {
    // TODO: Implement the ViewModel

    public LiveData<List<Item>> bongItems;

    public BongListViewModel() {

        ItemRepository itemRepository = new ItemRepository();
        bongItems = itemRepository.getItems();
    }
}
