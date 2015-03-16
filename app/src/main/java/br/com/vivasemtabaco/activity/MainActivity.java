package br.com.vivasemtabaco.activity;


import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import br.com.vivasemtabaco.R;
import br.com.vivasemtabaco.fragment.DangerZonesFragment;
import br.com.vivasemtabaco.fragment.DashboardFragment;
import br.com.vivasemtabaco.fragment.MyPlanFragment;
import br.com.vivasemtabaco.fragment.RemindersFragment;
import br.com.vivasemtabaco.model.Settings;
import br.com.vivasemtabaco.model.User;

public class MainActivity extends Activity {

    private DrawerLayout drawerLayout;
    private ListView drawerList;
    private ActionBarDrawerToggle drawerToggle;
    private CharSequence drawerTitle;
    private CharSequence title;
    private String[] menuItems;
    private Settings settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            loadSettings();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(settings == null)
        settings = new Settings();
        title = drawerTitle = getTitle();
        menuItems = getResources().getStringArray(R.array.menuItems);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerList = (ListView) findViewById(R.id.left_drawer);

        drawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
        drawerList.setAdapter(new ArrayAdapter<>(this,R.layout.drawer_list_item, menuItems));
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
            selectMenuItem(0);
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
            selectMenuItem(position);
        }
    }

    public void selectMenuItem(int position) {
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
            default: fragment = new DashboardFragment();
        }


        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();

        drawerList.setItemChecked(position, true);
        setTitle(menuItems[position]);
        drawerLayout.closeDrawer(drawerList);
    }

    public void changeFragment (Fragment fragment){
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).addToBackStack(fragment.toString()).commit();
        saveSettings();
    }
    {}
    @Override
    public void setTitle(CharSequence title) {
        this.title = title;
        getActionBar().setTitle(title);
    }

    public boolean isLoggedIn() {
        return settings.isLoggedIn();
    }

    public void setLoggedIn(boolean loggedIn) {
        settings.setLoggedIn(loggedIn);
    }

    public boolean isRemindersOn() {
        return settings.isRemindersOn();
    }

    public void setRemindersOn(boolean remindersOn) {
        settings.setRemindersOn(remindersOn);
    }

    public boolean isDangerZonesOn() {
        return settings.isDangerZonesOn();
    }

    public void setDangerZonesOn(boolean dangerZonesOn) {
        settings.setDangerZonesOn(dangerZonesOn);
    }

    public User getUser() {
        return settings.getUser();
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

    public void loadSettings() throws IOException {
        FileInputStream fis = this.getApplicationContext().openFileInput("settings.data");
        ObjectInputStream is = new ObjectInputStream(fis);
        try {
            settings = (Settings) is.readObject();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        is.close();
        fis.close();

    }

    public void saveSettings(){
        try {
            FileOutputStream fos = this.getApplicationContext().openFileOutput("settings.data", Context.MODE_PRIVATE);
            ObjectOutputStream os = new ObjectOutputStream(fos);
            os.writeObject(settings);
            os.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }



}