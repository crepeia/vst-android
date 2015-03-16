package br.com.vivasemtabaco.fragment;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

import br.com.vivasemtabaco.R;
import br.com.vivasemtabaco.activity.MainActivity;
import br.com.vivasemtabaco.model.User;


public class MyPlanFragment extends Fragment implements View.OnClickListener {


    private static final String dburl = "";
    private static final String dbuser = "";
    private static final String dbpassword = "";
    private EditText emailField,passwordField;
    private Button button;
    private TextView text2,text3,text7,logout;

    public MyPlanFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView;
        if(((MainActivity)getActivity()).isLoggedIn()){
            rootView = inflater.inflate(R.layout.fragment_myplan, container, false);
            text2 = (TextView) rootView.findViewById(R.id.myPlanText2);
            text2.setText(((MainActivity) getActivity()).getUser().getQuitDate());
            text3 = (TextView) rootView.findViewById(R.id.myPlanText3);
            text3.setText(((MainActivity) getActivity()).getUser().getTechniques());
            text7 = (TextView) rootView.findViewById(R.id.myPlanText7);
            text7.setText(((MainActivity) getActivity()).getUser().getEmail());
            logout = (TextView) rootView.findViewById(R.id.myPlanLogOut);
            logout.setOnClickListener(this);
        }else{
            rootView = inflater.inflate(R.layout.fragment_login, container, false);
            emailField = (EditText)rootView.findViewById(R.id.loginField1);
            passwordField = (EditText)rootView.findViewById(R.id.loginField2);
            button = (Button)rootView.findViewById(R.id.loginButton);
            button.setOnClickListener(this);

        }

        return rootView;
    }

    @Override
    public void onClick(View v) {

        if(v.getId() == R.id.loginButton){
            login();
            InputMethodManager inputManager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            inputManager.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
        if(v.getId() == R.id.myPlanLogOut){
            logout();
        }

    }

    public void login(){
            ((MainActivity) getActivity()).getUser().setEmail(emailField.getText().toString());
            ((MainActivity) getActivity()).setLoggedIn(true);
            ((MainActivity) getActivity()).changeFragment(new MyPlanFragment());

    }

    public void logout(){
        ((MainActivity) getActivity()).getUser().setEmail(null);
        ((MainActivity) getActivity()).getUser().setQuitDate(null);
        ((MainActivity) getActivity()).getUser().setPreviousAttempts(null);

        ((MainActivity) getActivity()).setLoggedIn(false);
        ((MainActivity) getActivity()).changeFragment(new MyPlanFragment());
    }

    public String testDB() {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(dburl, dbuser, dbpassword);


            String result = "Retrieved:\n";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from user");
            ResultSetMetaData rsmd = rs.getMetaData();

            while(rs.next()) {
                result += rsmd.getColumnName(1) + ": " + rs.getInt(1) + "\n";
                result += rsmd.getColumnName(2) + ": " + rs.getString(2) + "\n";
                result += rsmd.getColumnName(3) + ": " + rs.getString(3) + "\n";
            }
            return result;
        }
        catch(Exception e) {
            e.printStackTrace();
            return e.toString();
        }

    }


}


