package br.com.vivasemtabaco.model;


import java.io.Serializable;

public class Settings implements Serializable {

    private static final long serialVersionUID = 1L;

    private User user;
    private boolean loggedIn, remindersOn, dangerZonesOn;

    public Settings(){
        user = new User();
        loggedIn = false;
        remindersOn = true;
        dangerZonesOn = true;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public boolean isRemindersOn() {
        return remindersOn;
    }

    public void setRemindersOn(boolean remindersOn) {
        this.remindersOn = remindersOn;
    }

    public boolean isDangerZonesOn() {
        return dangerZonesOn;
    }

    public void setDangerZonesOn(boolean dangerZonesOn) {
        this.dangerZonesOn = dangerZonesOn;
    }
}
