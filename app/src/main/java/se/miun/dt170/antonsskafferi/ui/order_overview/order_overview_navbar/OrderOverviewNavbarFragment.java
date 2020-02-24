package se.miun.dt170.antonsskafferi.ui.order_overview.order_overview_navbar;

import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.Objects;

import se.miun.dt170.antonsskafferi.R;
import se.miun.dt170.antonsskafferi.activity.WaiterActivity;
import se.miun.dt170.antonsskafferi.ui.order_overview.OrderOverviewFragment;
import se.miun.dt170.antonsskafferi.ui.order_overview.order_overview_courses.OrderOverviewCoursesFragment;
import se.miun.dt170.antonsskafferi.ui.order_overview.order_overview_drinks.OrderOverviewDrinksFragment;

public class OrderOverviewNavbarFragment extends Fragment implements View.OnClickListener {

    private OrderOverviewNavbarViewModel mViewModel;
    private ImageView btnline1;
    private ImageView btnline2;
    private Boolean boolbtn1 = true;
    private Boolean boolbtn2 = false;


    public static OrderOverviewNavbarFragment newInstance() {
        return new OrderOverviewNavbarFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.order_overview_navbar_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(OrderOverviewNavbarViewModel.class);

        //Def of header buttons
        btnline1 = getActivity().findViewById(R.id.btn_line1);
        btnline2 = getActivity().findViewById(R.id.btn_line2);
        btnline1.setVisibility(View.VISIBLE);
        btnline2.setVisibility(View.INVISIBLE);
        Button orderButton1 = getActivity().findViewById(R.id.laCarteButton);
        Button orderButton2 = getActivity().findViewById(R.id.drinkButton);
        orderButton1.setOnClickListener(this);
        orderButton2.setOnClickListener(this);


        // TODO: Use the ViewModel
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.laCarteButton:
                //temp
                boolbtn1 = true;
                boolbtn2 = false;
                if (boolbtn1) {
                    btnline1.setVisibility(View.VISIBLE);
                    btnline2.setVisibility(View.INVISIBLE);
                }

                Toast.makeText(getActivity(), "button1", Toast.LENGTH_SHORT).show();

                // Create fragment and give it an argument specifying the article it should show

                /*
                OrderOverviewCoursesFragment coursesfragment = new OrderOverviewCoursesFragment();
                Bundle args = new Bundle();
                args.putInt(OrderOverviewCoursesFragment.ARG_POSITION, OrderOverviewCoursesFragment.mCurrentPosition);
                coursesfragment.setArguments(args);

                FragmentTransaction transaction1 = Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction();

                // Replace whatever is in the fragment_container view with this fragment,
                // and add the transaction to the back stack so the user can navigate back
                transaction1.replace(R.id.coursesFragment, coursesfragment);
                transaction1.addToBackStack(null);

                // Commit the transaction
                transaction1.commit();
                */

                break;

            case R.id.drinkButton:
                //Temp
                boolbtn1 = false;
                boolbtn2 = true;

                if (boolbtn2) {
                    btnline2.setVisibility(View.VISIBLE);
                    btnline1.setVisibility(View.INVISIBLE);
                }

                Toast.makeText(getActivity(), "button2", Toast.LENGTH_SHORT).show();

                /*
                // Create fragment and give it an argument specifying the article it should show
                OrderOverviewDrinksFragment drinksfragment = new OrderOverviewDrinksFragment();
                Bundle args2 = new Bundle();
                args2.putInt(OrderOverviewDrinksFragment.ARG_POSITION, OrderOverviewDrinksFragment.mCurrentPosition);
                drinksfragment.setArguments(args2);

                FragmentTransaction transaction = Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction();

                // Replace whatever is in the fragment_container view with this fragment,
                // and add the transaction to the back stack so the user can navigate back
                transaction.replace(R.id.coursesFragment, drinksfragment);
                transaction.addToBackStack(null);

                // Commit the transaction
                transaction.commit();

                 */

                break;

        }


    }

    public void day() {

        //TimerSettings
        //TextView textView = (TextView) getActivity().findViewById(R.id.timeTextView);
        SimpleDateFormat format = new SimpleDateFormat("yyyy:HH:mm");

        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_WEEK);

        switch (day) {
            case Calendar.SUNDAY:
                //Code
                break;
            case Calendar.MONDAY:
                //Code
                break;
            case Calendar.TUESDAY:
                //Code
                break;
            case Calendar.WEDNESDAY:
                //Code
                break;
            case Calendar.THURSDAY:
                //Code
                break;
            case Calendar.FRIDAY:
                //Code
                break;
            case Calendar.SATURDAY:
                //Code
                break;
        }
    }


}