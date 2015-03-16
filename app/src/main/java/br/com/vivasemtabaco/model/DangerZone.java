package br.com.vivasemtabaco.model;


import java.io.Serializable;

public class DangerZone implements Serializable {

    private static final long serialVersionUID = 1L;

    private String title;
    private double latitude, longitude;

    public String toString(){
        return title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
