package se.miun.dt170.antonsskafferi.ui.order_overview.order_overview_navbar;

import androidx.annotation.NavigationRes;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import se.miun.dt170.antonsskafferi.R;
import se.miun.dt170.antonsskafferi.activity.WaiterActivity;
import se.miun.dt170.antonsskafferi.ui.order_overview.OrderOverviewFragmentDirections;
import se.miun.dt170.antonsskafferi.ui.table_overview.TableOverviewFragmentDirections;

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
        timeView = getActivity().findViewById(R.id.timeView);


        //Denna borde vara global plus att dag skall vara med oxo
        //Thread based updates each sekun or minute mayebe.
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        String time = format.format(calendar.getTime());

        WaiterActivity waiter = new WaiterActivity();
        String test = waiter.getTest();
        timeView.setText(time);

        //Def of header buttons
        Button orderButton1 = getActivity().findViewById(R.id.button1);
        Button orderButton2 = getActivity().findViewById(R.id.button2);
        orderButton1.setOnClickListener(this);
        orderButton2.setOnClickListener(this);
        // TODO: Use the ViewModel
    }

    @Override
    public void onClick(View view) {

        switch(view.getId()){

            case R.id.button1:
                //temp
                Toast.makeText(getActivity(), "button1", Toast.LENGTH_SHORT).show();

                //Navigate to previous activity
                NavDirections action = OrderOverviewFragmentDirections.actionOrderOverviewFragment3ToTableOverviewFragment();
                Navigation.findNavController(getView()).navigate(action);
                //Navigation.findNavController(getView()).navigate(action);
                //Intent intent = new Intent(getActivity(), WaiterActivity.class);
                //startActivity(intent);
                //getActivity().finish();  //kommer tillbaka till start loginsidan
            break;

            case R.id.button2:

                //Temp
                Toast.makeText(getActivity(), "button2", Toast.LENGTH_SHORT).show();



                break;

        }


    }
}
