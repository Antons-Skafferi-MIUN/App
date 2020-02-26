package se.miun.dt170.antonsskafferi.ui.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import se.miun.dt170.antonsskafferi.R;
import se.miun.dt170.antonsskafferi.TableDialogSharedViewModel;

/**
 * Popup dialog displayed when a {@link se.miun.dt170.antonsskafferi.ui.table_overview.table_button.TableButtonFragment} is clicked.
 * Will navigate you to {@link se.miun.dt170.antonsskafferi.ui.order_overview.OrderOverviewFragment}
 * or reserve table.
 */
public class TableDialogFragment extends DialogFragment {
    private Button openOrderButton;
    private View dialogView;
    private Fragment parent;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        LayoutInflater layoutInflater = requireActivity().getLayoutInflater();
        dialogView = layoutInflater.inflate(R.layout.table_dialog_fragment, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(dialogView);
        return builder.create();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        dialogView = view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        parent = getParentFragment();
        openOrderButton = dialogView.findViewById(R.id.openOrderButton);

        TableDialogSharedViewModel sharedViewModel = new ViewModelProvider(requireActivity()).
                get(TableDialogSharedViewModel.class); //gets the shared view model from the associsated fragment.

        //creates a new observers that will update once the shared view model has new data
        sharedViewModel.getAvailableSeats().observe(parent.getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer nrOfSeats) {
                Toast.makeText(getActivity(), nrOfSeats.toString(), Toast.LENGTH_SHORT).show();
            }
        });

        openOrderButton.setOnClickListener(v -> {
            NavDirections action = TableDialogFragmentDirections.actionTableDialogFragmentToOrderOverviewFragment();
            Navigation.findNavController(parent.getView()).navigate(action);
        });
    }


}


