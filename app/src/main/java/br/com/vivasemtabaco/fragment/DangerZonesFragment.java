package br.com.vivasemtabaco.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import br.com.vivasemtabaco.R;
import br.com.vivasemtabaco.activity.MainActivity;


public class DangerZonesFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemClickListener {

    private ListView listView;
    private Button button;

    public DangerZonesFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_dangerzones, container, false);
        listView = (ListView) rootView.findViewById(R.id.dangerZonesListView);
        listView.setAdapter(new ArrayAdapter<>(getActivity(),R.layout.dangerzones_list_item,
                ((MainActivity) getActivity()).getUser().getDangerZones()));
        listView.setOnItemClickListener(this);
        button = (Button) rootView.findViewById(R.id.dangerZonesButton);
        button.setOnClickListener(this);
        return rootView;
    }

    @Override
    public void onClick(View v) {
        ((MainActivity) getActivity()).changeFragment(new AddDangerZoneFragment());
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Bundle args = new Bundle();
        args.putInt("position", position);
        EditDangerZoneFragment fragment = new EditDangerZoneFragment();
        fragment.setArguments(args);
        ((MainActivity) getActivity()).changeFragment(fragment);
    }


}
