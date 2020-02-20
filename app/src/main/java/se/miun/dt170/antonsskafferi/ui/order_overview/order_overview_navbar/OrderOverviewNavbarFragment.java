package se.miun.dt170.antonsskafferi.ui.order_overview.order_overview_navbar;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import se.miun.dt170.antonsskafferi.R;
import se.miun.dt170.antonsskafferi.activity.WaiterActivity;

public class OrderOverviewNavbarFragment extends Fragment implements View.OnClickListener {

    private OrderOverviewNavbarViewModel mViewModel;
    private TextView timeView;


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

        //Denna borde vara global plus att dag skall vara med oxo
        //Thread based updates each sekun or minute mayebe.
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        String time = format.format(calendar.getTime());

        WaiterActivity waiter = new WaiterActivity();
        String test = waiter.getTest();

        //Def of header buttons
        Button orderButton1 = getActivity().findViewById(R.id.laCarteButton);
        Button orderButton2 = getActivity().findViewById(R.id.etcButton);
        orderButton1.setOnClickListener(this);
        orderButton2.setOnClickListener(this);
        // TODO: Use the ViewModel
    }

    @Override
    public void onClick(View view) {

        switch(view.getId()){

            case R.id.laCarteButton:
                //temp
                Toast.makeText(getActivity(), "button1", Toast.LENGTH_SHORT).show();


            case R.id.etcButton:

                //Temp
                Toast.makeText(getActivity(), "button2", Toast.LENGTH_SHORT).show();



                break;

        }


    }
}
