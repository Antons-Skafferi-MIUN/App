package se.miun.dt170.antonsskafferi.ui.dialog;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import se.miun.dt170.antonsskafferi.R;

public class TableNavigation extends Fragment {

    private TableNavigationViewModel mViewModel;

    public static TableNavigation newInstance() {
        return new TableNavigation();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.table_navigation_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(TableNavigationViewModel.class);
        // TODO: Use the ViewModel
    }

}
