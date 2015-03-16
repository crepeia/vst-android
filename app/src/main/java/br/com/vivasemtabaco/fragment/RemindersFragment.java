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


public class RemindersFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemClickListener{

    private ListView listView;

    private Button button;

    public RemindersFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_reminders, container, false);
        listView = (ListView) rootView.findViewById(R.id.remindersListView);
        listView.setAdapter(new ArrayAdapter<>(getActivity(),R.layout.reminders_list_item,
                ((MainActivity) getActivity()).getUser().getReminders()));
        listView.setOnItemClickListener(this);
        button = (Button) rootView.findViewById(R.id.remindersButton);
        button.setOnClickListener(this);
        return rootView;

    }


    @Override
    public void onClick(View v) {
        ((MainActivity) getActivity()).changeFragment(new AddReminderFragment());
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Bundle args = new Bundle();
        args.putInt("position", position);
        EditReminderFragment fragment = new EditReminderFragment();
        fragment.setArguments(args);
        ((MainActivity) getActivity()).changeFragment(fragment);
    }
}
