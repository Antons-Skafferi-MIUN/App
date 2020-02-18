package se.miun.dt170.antonsskafferi.ui.table_overview;

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
import se.miun.dt170.antonsskafferi.ui.order_overview.OrderOverviewFragment;

public class TableOverviewFragment extends Fragment {

    private TableOverviewViewModel mViewModel;

    public static TableOverviewFragment newInstance() {
        return new TableOverviewFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        Button orderButton = container.findViewById(R.id.tableButton1);
        orderButton.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.orderOverviewFragment3, null));
        return inflater.inflate(R.layout.table_overview_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(TableOverviewViewModel.class);
        // TODO: Use the ViewModel
    }

}
