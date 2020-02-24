package se.miun.dt170.antonsskafferi.ui.table_overview;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import se.miun.dt170.antonsskafferi.R;

/**
 * This is the fullscreen fragment for showing available tables.
 * Contains several {@link se.miun.dt170.antonsskafferi.ui.table_overview.table_button.TableButtonFragment}s.
 */
public class TableOverviewFragment extends Fragment {

    private TableOverviewViewModel mViewModel;

    public static TableOverviewFragment newInstance() {
        return new TableOverviewFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.table_overview_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(TableOverviewViewModel.class);
        // TODO: Use the ViewModel
    }
}
