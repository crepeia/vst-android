package br.com.vivasemtabaco;

import java.util.Locale;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity {

    private DrawerLayout drawerLayout;
    private ListView drawerList;
    private ActionBarDrawerToggle drawerToggle;
    private CharSequence drawerTitle;
    private CharSequence title;
    private String[] options;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        title = drawerTitle = getTitle();
        options = getResources().getStringArray(R.array.options);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerList = (ListView) findViewById(R.id.left_drawer);

        drawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
        drawerList.setAdapter(new ArrayAdapter<>(this,R.layout.drawer_list_item, options));
        drawerList.setOnItemClickListener(new DrawerItemClickListener());

        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);

        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.drawable.ic_drawer,
                R.string.drawer_open, R.string.drawer_close) {
            public void onDrawerClosed(View view) {
                getActionBar().setTitle(title);
                invalidateOptionsMenu();
            }

            public void onDrawerOpened(View drawerView) {
                getActionBar().setTitle(drawerTitle);
                invalidateOptionsMenu();
            }
        };

        drawerLayout.setDrawerListener(drawerToggle);

        if (savedInstanceState == null) {
            selectItem(0);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);
        }
    }

    private void selectItem(int position) {
        Fragment fragment;

        switch(position){
            case 0: fragment = new DashboardFragment();
                break;
            case 1: fragment = new MyPlanFragment();
                break;
            case 2: fragment = new RemindersFragment();
                break;
            case 3: fragment = new DangerZonesFragment();
                break;
            case 4: fragment = new SettingsFragment();
                break;
            default: fragment = new DashboardFragment();
        }

        Bundle args = new Bundle();
        args.putInt("0", position);
        fragment.setArguments(args);

        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();

        drawerList.setItemChecked(position, true);
        setTitle(options[position]);
        drawerLayout.closeDrawer(drawerList);
    }

    @Override
    public void setTitle(CharSequence title) {
        this.title = title;
        getActionBar().setTitle(title);
    }


    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }


    public static class DashboardFragment extends Fragment {

        public DashboardFragment() {

        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_dashboard, container, false);
            String title = getResources().getStringArray(R.array.options)[0];
            getActivity().setTitle(title);
            return rootView;
        }
    }

    public static class MyPlanFragment extends Fragment {

        public MyPlanFragment() {

        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_myplan, container, false);
            return rootView;
        }
    }

    public static class RemindersFragment extends Fragment {

        public RemindersFragment() {

        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_reminders, container, false);
            return rootView;
        }
    }

    public static class DangerZonesFragment extends Fragment {

        public DangerZonesFragment() {

        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_dangerzones, container, false);
            return rootView;
        }
    }

    public static class SettingsFragment extends Fragment {

        public SettingsFragment() {

        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_settings, container, false);
            return rootView;
        }
    }


}