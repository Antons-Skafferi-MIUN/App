package se.miun.dt170.antonsskafferi.ui.table_overview;

import android.graphics.Color;
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

/**
 * This is the fullscreen fragment for showing available tables.
 * Contains several {@link se.miun.dtableButtont170.antonsskafferi.ui.table_overview.TableView}.
 */
public class TableOverviewFragment extends Fragment implements Button.OnClickListener {

    private TableOverviewViewModel mViewModel;
    private View fragmentView;;
    private TableDialogSharedViewModel sharedViewModel;
    private int green = Color.parseColor("#39FF14");


    public static TableOverviewFragment newInstance() {
        return new TableOverviewFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        fragmentView = inflater.inflate(R.layout.table_overview_fragment, container, false);
        ViewGroup parent = (ViewGroup) fragmentView.findViewById(R.id.table1).getParent();
        for(int tableIndex = 0; tableIndex < parent.getChildCount(); tableIndex++){ // for each child apply a listener to the childs tableButton
            TableView table = (TableView) fragmentView.findViewById(R.id.table1 + tableIndex);
            table.setup(tableIndex+1);
            Button tempButton = table.findViewById(R.id.tableButton);
            tempButton.setOnClickListener(this);
        }
        return fragmentView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(TableOverviewViewModel.class);
        sharedViewModel = new ViewModelProvider(requireActivity()).get(TableDialogSharedViewModel.class);

    }

    @Override
    public void onClick(View v) { // gets ID for table button.
        TableView table = (TableView) v.getParent();
        sharedViewModel.setTable(table);
        NavDirections action = TableOverviewFragmentDirections.actionTableOverviewFragmentToTableDialogFragment();
        Navigation.findNavController(getView()).navigate(action);

    }
}
