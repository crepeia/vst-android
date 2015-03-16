package br.com.vivasemtabaco.model;

import java.io.Serializable;
import java.util.ArrayList;


public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    private String email;
    private String quitDate;
    private String techniques;
    private String previousAttempts;
    private ArrayList<Reminder> reminders;
    private ArrayList<DangerZone> dangerZones ;

    public User(){
        reminders = new ArrayList<>();
        dangerZones = new ArrayList<>();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getQuitDate() {
        return quitDate;
    }

    public void setQuitDate(String quitDate) {
        this.quitDate = quitDate;
    }

    public String getTechniques() {
        return techniques;
    }

    public void setTechniques(String techniques) {
        this.techniques = techniques;
    }

    public String getPreviousAttempts() {
        return previousAttempts;
    }

    public void setPreviousAttempts(String previousAttempts) {
        this.previousAttempts = previousAttempts;
    }

    public ArrayList<Reminder> getReminders() {
        return reminders;
    }

    public void setReminders(ArrayList<Reminder> reminders) {
        this.reminders = reminders;
    }

    public ArrayList<DangerZone> getDangerZones() {
        return dangerZones;
    }

    public void setDangerZones(ArrayList<DangerZone> dangerZones) {
        this.dangerZones = dangerZones;
    }
}
