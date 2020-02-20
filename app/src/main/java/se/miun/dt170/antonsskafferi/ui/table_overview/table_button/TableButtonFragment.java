package se.miun.dt170.antonsskafferi.ui.table_overview.table_button;

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

public class TableButtonFragment extends Fragment {

    private View view;
    private TableButtonViewModel mViewModel;

    public static TableButtonFragment newInstance() {
        return new TableButtonFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.table_button_fragment, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(TableButtonViewModel.class);
        // TODO: Use the ViewModel

        // Example of one button navigation
        Button orderButton = view.findViewById(R.id.tableButton);
        orderButton.setOnClickListener(v -> {
            NavDirections action = TableOverviewFragmentDirections.actionTableOverviewFragmentToOrderOverviewFragment3();
            Navigation.findNavController(getView()).navigate(action);
        });
    }
}
