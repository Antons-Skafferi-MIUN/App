package se.miun.dt170.antonsskafferi.ui.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import se.miun.dt170.antonsskafferi.R;

/**
 * Popup dialog displayed when a {@link se.miun.dt170.antonsskafferi.ui.table_overview.table_button.TableButtonFragment} is clicked.
 * Will navigate you to {@link se.miun.dt170.antonsskafferi.ui.order_overview.OrderOverviewFragment}
 * or reserve table.
 */
public class TableDialogFragment extends DialogFragment {
    private Button openOrderButton;
    private View dialogView;

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
        openOrderButton = dialogView.findViewById(R.id.openOrderButton);
        openOrderButton.setOnClickListener(v -> {
            NavDirections action = TableDialogFragmentDirections.actionTableDialogFragmentToOrderOverviewFragment();
            Navigation.findNavController(getParentFragment().getView()).navigate(action);
        });
    }


}


