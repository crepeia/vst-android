package br.com.vivasemtabaco.fragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import br.com.vivasemtabaco.R;
import br.com.vivasemtabaco.activity.MainActivity;
import br.com.vivasemtabaco.model.DangerZone;
import br.com.vivasemtabaco.model.Reminder;


public class AddDangerZoneFragment extends Fragment implements View.OnClickListener, OnMapReadyCallback, GoogleMap.OnMapLongClickListener {

    private EditText editText;
    private Button button;
    private MapFragment mapFragment;
    private GoogleMap googleMap;
    private double lt, lg;
    private Marker marker;

    public AddDangerZoneFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_add_dangerzone, container, false);
        editText = (EditText) rootView.findViewById(R.id.addDangerZoneField);
        button = (Button) rootView.findViewById(R.id.addDangerZoneButton);
        button.setOnClickListener(this);
        mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.addDangerZoneMap);
        mapFragment.getMapAsync(this);
        return rootView;
    }

    @Override
    public void onClick(View v) {
        DangerZone dangerZone = new DangerZone();
        dangerZone.setTitle(editText.getText().toString());
        dangerZone.setLatitude(lt);
        dangerZone.setLongitude(lg);
        ((MainActivity)getActivity()).getUser().getDangerZones().add(dangerZone);
        FragmentManager fm = getFragmentManager();
        fm.beginTransaction().remove(fm.findFragmentById(R.id.addDangerZoneMap)).commit();
        ((MainActivity)getActivity()).changeFragment(new DangerZonesFragment());

    }

    @Override
    public void onMapReady(GoogleMap map) {
       googleMap = map;
       googleMap.setMyLocationEnabled(true);
       googleMap.setOnMapLongClickListener(this);
    }


    @Override
    public void onMapLongClick(LatLng latLng) {
        googleMap.clear();
        marker = googleMap.addMarker(new MarkerOptions().position(latLng).title(editText.getText().toString()));
        lt = marker.getPosition().latitude;
        lg = marker.getPosition().longitude;
    }


}
