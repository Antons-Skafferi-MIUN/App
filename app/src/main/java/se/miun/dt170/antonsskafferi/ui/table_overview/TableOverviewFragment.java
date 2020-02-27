package se.miun.dt170.antonsskafferi.ui.table_overview;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import se.miun.dt170.antonsskafferi.R;

/**
 * This is the fullscreen fragment for showing available tables.
 * Contains several {@link se.miun.dt170.antonsskafferi.ui.table_overview.table_button.TableButtonFragment}s.
 */
public class TableOverviewFragment extends Fragment implements Button.OnClickListener {

    private TableOverviewViewModel mViewModel;
    private View fragmentView;;
    private Button myButt;
    private View layout;

    public static TableOverviewFragment newInstance() {
        return new TableOverviewFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        fragmentView = inflater.inflate(R.layout.table_overview_fragment, container, false);
        new TableView(this.getContext());
        for(int i = 0; i < 7; i++){

//            View child = fragmentView.findViewById(R.id.table1 + i);
//            ViewGroup parent = (ViewGroup) child.getParent();
//            int index = parent.indexOfChild(child);
//            parent.removeView(child);
//            child = getLayoutInflater().inflate(new TableView(Context), parent, false);
//            parent.addView(child, index);

            Button tempButton = fragmentView.findViewById(R.id.table1 + i).findViewById(R.id.tableButton);
            tempButton.setOnClickListener(this);
        }
        return fragmentView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(TableOverviewViewModel.class);


        // TODO: Use the ViewModel
    }

    @Override
    public void onClick(View v) { // gets ID for table button.
        int k = 2;
        ConstraintLayout tablelayout = (ConstraintLayout) v.getParent();

        switch(tablelayout.getId()){
            case R.id.table1:
                Toast.makeText(getActivity(), "I CLICK TABLE 1", Toast.LENGTH_SHORT).show();

                break;
            case R.id.table2:
                Toast.makeText(getActivity(), "I CLICK TABLE 2", Toast.LENGTH_SHORT).show();
                break;
            case R.id.table3:
                Toast.makeText(getActivity(), "I CLICK TABLE 3", Toast.LENGTH_SHORT).show();
                break;
            case R.id.table4:
                Toast.makeText(getActivity(), "I CLICK TABLE 4", Toast.LENGTH_SHORT).show();
                break;
            case R.id.table5:
                Toast.makeText(getActivity(), "I CLICK TABLE 5", Toast.LENGTH_SHORT).show();
                break;
            case R.id.table6:
                Toast.makeText(getActivity(), "I CLICK TABLE 6", Toast.LENGTH_SHORT).show();
                break;
            case R.id.table7:
                Toast.makeText(getActivity(), "I CLICK TABLE 7", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
