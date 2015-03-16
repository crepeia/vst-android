package br.com.vivasemtabaco.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;

import br.com.vivasemtabaco.R;
import br.com.vivasemtabaco.activity.MainActivity;
import br.com.vivasemtabaco.model.Reminder;

/**
 * Created by thiago on 16/03/2015.
 */
public class EditReminderFragment  extends Fragment implements View.OnClickListener {

    EditText editText;
    TimePicker picker;
    Button button1, button2;
    RadioGroup radiogroup;
    Reminder reminder;

    public EditReminderFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        View rootView = inflater.inflate(R.layout.fragment_edit_reminder, container, false);
        reminder = ((MainActivity)getActivity()).getUser().getReminders().get(getArguments().getInt("position",0));
        editText = (EditText) rootView.findViewById(R.id.editReminderField);
        editText.setText(reminder.getMessage());
        picker = (TimePicker) rootView.findViewById(R.id.editReminderPicker);
        picker.setIs24HourView(true);
        picker.setDescendantFocusability(TimePicker.FOCUS_BLOCK_DESCENDANTS);
        picker.setCurrentHour(reminder.getHour());
        picker.setCurrentMinute(reminder.getMinute());
        radiogroup = (RadioGroup) rootView.findViewById(R.id.editReminderRadioGroup);
        if(reminder.getFrequency() == 1){
            radiogroup.check(R.id.editReminderRadio1);
        }if(reminder.getFrequency() == 2){
            radiogroup.check(R.id.editReminderRadio2);
        }if(reminder.getFrequency() == 3){
            radiogroup.check(R.id.editReminderRadio4);
        }if(reminder.getFrequency() == 1){
            radiogroup.check(R.id.editReminderRadio4);
        }
        button1 = (Button) rootView.findViewById(R.id.editReminderButton1);
        button1.setOnClickListener(this);
        button2 = (Button) rootView.findViewById(R.id.editReminderButton2);
        button2.setOnClickListener(this);

        return rootView;
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == button1.getId()){
            reminder.setMessage(editText.getText().toString());
            reminder.setHour(picker.getCurrentHour());
            reminder.setMinute(picker.getCurrentMinute());
            if(radiogroup.getCheckedRadioButtonId() == R.id.editReminderRadio1){
                reminder.setFrequency(1);
            }if(radiogroup.getCheckedRadioButtonId() == R.id.editReminderRadio2){
                reminder.setFrequency(2);
            }if(radiogroup.getCheckedRadioButtonId() == R.id.editReminderRadio3){
                reminder.setFrequency(3);
            }if(radiogroup.getCheckedRadioButtonId() == R.id.editReminderRadio4){
                reminder.setFrequency(4);
            }
            ((MainActivity)getActivity()).getUser().getReminders().set(getArguments().getInt("position"),reminder);

        }
        if(v.getId() == button2.getId()){
            ((MainActivity)getActivity()).getUser().getReminders().remove(getArguments().getInt("position"));
        }

        ((MainActivity)getActivity()).changeFragment(new RemindersFragment());

    }
}
