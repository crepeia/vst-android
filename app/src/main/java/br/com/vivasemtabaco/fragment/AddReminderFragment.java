package br.com.vivasemtabaco.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TimePicker;

import java.util.Calendar;

import br.com.vivasemtabaco.R;
import br.com.vivasemtabaco.activity.MainActivity;
import br.com.vivasemtabaco.model.Reminder;


public class AddReminderFragment extends Fragment implements View.OnClickListener {

    private EditText editText;
    private TimePicker picker;
    private Button button;
    private RadioGroup radiogroup;

    public AddReminderFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        View rootView = inflater.inflate(R.layout.fragment_add_reminder, container, false);
        editText = (EditText) rootView.findViewById(R.id.addReminderField);
        picker = (TimePicker) rootView.findViewById(R.id.addReminderPicker);
        picker.setIs24HourView(true);
        picker.setDescendantFocusability(TimePicker.FOCUS_BLOCK_DESCENDANTS);
        radiogroup = (RadioGroup) rootView.findViewById(R.id.addReminderRadioGroup);
        radiogroup.check(R.id.addReminderRadio1);
        button = (Button) rootView.findViewById(R.id.addReminderButton);
        button.setOnClickListener(this);

        return rootView;
    }

    @Override
    public void onClick(View v) {
        Reminder reminder = new Reminder();
        reminder.setMessage(editText.getText().toString());
        reminder.setHour(picker.getCurrentHour());
        reminder.setMinute(picker.getCurrentMinute());
        if(radiogroup.getCheckedRadioButtonId() == R.id.addReminderRadio1){
            reminder.setFrequency(1);
        }if(radiogroup.getCheckedRadioButtonId() == R.id.addReminderRadio2){
            reminder.setFrequency(2);
        }if(radiogroup.getCheckedRadioButtonId() == R.id.addReminderRadio3){
            reminder.setFrequency(3);
        }if(radiogroup.getCheckedRadioButtonId() == R.id.addReminderRadio4){
            reminder.setFrequency(4);
        }
        ((MainActivity)getActivity()).getUser().getReminders().add(reminder);
        ((MainActivity)getActivity()).changeFragment(new RemindersFragment());

    }

}
