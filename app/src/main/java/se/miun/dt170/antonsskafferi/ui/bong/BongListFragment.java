package se.miun.dt170.antonsskafferi.ui.bong;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import se.miun.dt170.antonsskafferi.R;
import se.miun.dt170.antonsskafferi.ui.table_overview.TableOverviewFragmentDirections;

/**
 * This is the container for the list of {@link BongItemFragment}.
 * Displayed to the right in the order {@link se.miun.dt170.antonsskafferi.ui.order_overview.OrderOverviewFragment}.
 * Displayed in the {@link se.miun.dt170.antonsskafferi.activity.KitchenActivity}.
 */

public class BongListFragment extends Fragment {

    private BongListViewModel mViewModel;

    public static BongListFragment newInstance() {
        return new BongListFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        return  inflater.inflate(R.layout.bong_list_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(BongListViewModel.class);
        // TODO: Use the ViewModel
    }

}
