package se.miun.dt170.antonsskafferi.ui.dialog;

import androidx.fragment.app.DialogFragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import se.miun.dt170.antonsskafferi.R;

public class TableDialog extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        LayoutInflater layoutInflater = requireActivity().getLayoutInflater();
        layoutInflater.inflate(R.layout.dialog, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(layoutInflater.inflate(R.layout.dialog, null));

        return builder.create();
    }
}
