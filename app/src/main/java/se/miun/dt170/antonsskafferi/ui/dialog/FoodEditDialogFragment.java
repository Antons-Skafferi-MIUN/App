package se.miun.dt170.antonsskafferi.ui.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProviders;

import se.miun.dt170.antonsskafferi.R;

/**
 * Popup dialog displayed when a {@link se.miun.dt170.antonsskafferi.ui.bong.BongItemFragment} is clicked.
 */
public class FoodEditDialogFragment extends DialogFragment {

    private FoodEditDialogViewModel mViewModel;
    private View dialogView;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        LayoutInflater layoutInflater = requireActivity().getLayoutInflater();
        dialogView = layoutInflater.inflate(R.layout.food_edit_dialog_fragment, null);
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
        mViewModel = ViewModelProviders.of(this).get(FoodEditDialogViewModel.class);
        // TODO: Use the ViewModel
    }

}
