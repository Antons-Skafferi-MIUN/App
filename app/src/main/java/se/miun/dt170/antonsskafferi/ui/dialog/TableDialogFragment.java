package se.miun.dt170.antonsskafferi.ui.dialog;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import se.miun.dt170.antonsskafferi.R;

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

        openOrderButton = view.findViewById(R.id.openOrderButton);
        openOrderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openOrderButton.setText("ASDASDASDASDASD");
                NavDirections action = TableDialogFragmentDirections.actionTableDialogFragmentToOrderOverviewFragment();
                Navigation.findNavController(getView()).navigate(action);
            }
        });

    }
}
