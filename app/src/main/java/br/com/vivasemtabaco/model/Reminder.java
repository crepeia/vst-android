package br.com.vivasemtabaco.model;


import java.io.Serializable;
import java.util.Calendar;

public class Reminder  implements Serializable {

    private static final long serialVersionUID = 1L;

    private String message;
    private int hour, minute;
    private int frequency; //1 = once, 2 = daily, 3 = weekly, 4 = monthly
    private boolean on;

    @Override
    public String toString(){
        return (message  + "\n" + getTimeStr() + " (" + getFrequencyStr() + ")");
    }

    public String getFrequencyStr(){
        if(frequency == 1) {
            return "once";
        }else if(frequency == 2){
            return "daily";
        }else if(frequency == 3){
            return "weekly";
        }else if(frequency == 4){
            return "monthly";
        }else{
            return null;
        }
    }

    public String getTimeStr(){
        return (String.valueOf(String.format("%02d",hour)) + ":" + String.valueOf(String.format("%02d",minute)));
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getFrequency() {
        return frequency;
    }


    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public boolean isOn() {
        return on;
    }

    public void setOn(boolean on) {
        this.on = on;
    }
}
