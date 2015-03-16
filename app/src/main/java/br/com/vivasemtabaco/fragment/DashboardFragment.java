package br.com.vivasemtabaco.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;

import br.com.vivasemtabaco.R;
import br.com.vivasemtabaco.activity.MainActivity;


public class DashboardFragment extends Fragment implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {

    Button button1, button2, button3;
    Switch switch1,switch2;

    public DashboardFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_dashboard, container, false);
        String title = getResources().getStringArray(R.array.menuItems)[0];
        getActivity().setTitle(title);
        button1 = (Button) rootView.findViewById(R.id.dashboardButton1);
        button1.setOnClickListener(this);
        if( ((MainActivity) getActivity()).isLoggedIn()){
            button1.setText(R.string.myPlan);
        }else{
            button1.setText(R.string.login);
        }
        button2 = (Button) rootView.findViewById(R.id.dashboardButton2);
        button2.setOnClickListener(this);
        button3 = (Button) rootView.findViewById(R.id.dashboardButton3);
        button3.setOnClickListener(this);
        switch1 = (Switch) rootView.findViewById(R.id.dashboardSwitch1);
        switch1.setOnCheckedChangeListener(this);
        if( ((MainActivity) getActivity()).isRemindersOn()){
            switch1.setChecked(true);
        }else{
            switch1.setChecked(false);
        }
        switch2 = (Switch) rootView.findViewById(R.id.dashboardSwitch2);
        switch2.setOnCheckedChangeListener(this);
        if( ((MainActivity) getActivity()).isDangerZonesOn()){
            switch2.setChecked(true);
        }else{
            switch2.setChecked(false);
        }

        return rootView;
    }


    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.dashboardButton1){
            ((MainActivity) getActivity()).changeFragment(new MyPlanFragment());
        }
        if(v.getId() == R.id.dashboardButton2){
            ((MainActivity) getActivity()).changeFragment(new RemindersFragment());
        }
        if(v.getId() == R.id.dashboardButton3){
            ((MainActivity) getActivity()).changeFragment(new DangerZonesFragment());
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (buttonView.getId() == R.id.dashboardSwitch1) {
            if (isChecked) {
                ((MainActivity) getActivity()).setRemindersOn(true);
            } else {
                ((MainActivity) getActivity()).setRemindersOn(false);
            }
        }if(buttonView.getId() == R.id.dashboardSwitch2){
            if (isChecked) {
                ((MainActivity) getActivity()).setDangerZonesOn(true);
            } else {
                ((MainActivity) getActivity()).setDangerZonesOn(false);
            }
        }
    }


 }
