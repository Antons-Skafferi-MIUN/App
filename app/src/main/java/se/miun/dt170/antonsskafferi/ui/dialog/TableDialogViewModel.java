package se.miun.dt170.antonsskafferi.ui.dialog;

import android.util.Log;

import androidx.lifecycle.ViewModel;

/**
 * Data container for {@link TableDialogFragment}
 */
public class TableDialogViewModel extends ViewModel {
    public void clearCurrentOrderFromDatabase(){
        Log.i("VIEWMODEL", "clearCurrentOrderFromDatabase:");
    }
}
