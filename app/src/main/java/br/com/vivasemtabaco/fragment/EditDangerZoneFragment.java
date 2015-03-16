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

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import br.com.vivasemtabaco.R;
import br.com.vivasemtabaco.activity.MainActivity;
import br.com.vivasemtabaco.model.DangerZone;

public class EditDangerZoneFragment extends Fragment implements View.OnClickListener, OnMapReadyCallback, GoogleMap.OnMapLongClickListener  {

    private EditText editText;
    private Button button1, button2;
    private MapFragment mapFragment;
    private GoogleMap googleMap;
    private double lt, lg;
    private DangerZone dangerZone;
    private Marker marker;

    public EditDangerZoneFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_edit_dangerzone, container, false);
        dangerZone = ((MainActivity)getActivity()).getUser().getDangerZones().get(getArguments().getInt("position",0));
        editText = (EditText) rootView.findViewById(R.id.editDangerZoneField);
        editText.setText(dangerZone.getTitle());
        button1 = (Button) rootView.findViewById(R.id.editDangerZoneButton1);
        button1.setOnClickListener(this);
        button2 = (Button) rootView.findViewById(R.id.editDangerZoneButton2);
        button2.setOnClickListener(this);
        lt = dangerZone.getLatitude();
        lg = dangerZone.getLongitude();
        mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.editDangerZoneMap);
        mapFragment.getMapAsync(this);
        return rootView;
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == button1.getId()) {
            dangerZone.setTitle(editText.getText().toString());
            dangerZone.setLatitude(lt);
            dangerZone.setLongitude(lg);
            ((MainActivity) getActivity()).getUser().getDangerZones().set(getArguments().getInt("position"),dangerZone);

        }
        if(v.getId() == button2.getId()){
            ((MainActivity)getActivity()).getUser().getDangerZones().remove(getArguments().getInt("position"));
        }
        FragmentManager fm = getFragmentManager();
        fm.beginTransaction().remove(fm.findFragmentById(R.id.editDangerZoneMap)).commit();
        ((MainActivity) getActivity()).changeFragment(new DangerZonesFragment());
    }

    @Override
    public void onMapReady(GoogleMap map) {
        googleMap = map;
        googleMap.setMyLocationEnabled(true);
        LatLng coordinate = new LatLng(dangerZone.getLatitude(), dangerZone.getLongitude());
        CameraUpdate location = CameraUpdateFactory.newLatLngZoom(coordinate, 10);
        googleMap.animateCamera(location);
        googleMap.addMarker(new MarkerOptions().position(coordinate).title(editText.getText().toString()));
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
