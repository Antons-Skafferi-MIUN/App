package se.miun.dt170.antonsskafferi.ui.table_overview.table_button;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import se.miun.dt170.antonsskafferi.R;
import se.miun.dt170.antonsskafferi.TableDialogSharedViewModel;
import se.miun.dt170.antonsskafferi.ui.table_overview.TableOverviewFragmentDirections;

/**
 * Displayed inside a {@link se.miun.dt170.antonsskafferi.ui.table_overview.TableOverviewFragment}.
 * Buttons will open a {@link se.miun.dt170.antonsskafferi.ui.dialog.TableDialogFragment}.
 * Describes a table button.
 */
public class TableButtonFragment extends Fragment {

    private View fragmentView;
    private TableButtonViewModel mViewModel;
    private TableDialogSharedViewModel sharedViewModel;


    public static TableButtonFragment newInstance() {
        return new TableButtonFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.table_button_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fragmentView = view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(TableButtonViewModel.class);
        // TODO: Use the ViewModel
        sharedViewModel = new ViewModelProvider(requireActivity()).get(TableDialogSharedViewModel.class);

        // Example of one button navigation
        Button orderButton = fragmentView.findViewById(R.id.tableButton);
        orderButton.setOnClickListener(v -> {
            sharedViewModel.setTableColor("Green"); //Get the actual table color.
            sharedViewModel.setAvailableSeats(new Integer(5)); //get the actual number of avaialbe seats.
            NavDirections action = TableOverviewFragmentDirections.actionTableOverviewFragmentToTableDialogFragment();
            Navigation.findNavController(getView()).navigate(action);
        });
    }
}
