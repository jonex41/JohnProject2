package com.example.johnproject2;

import java.io.Serializable;

public class ModelClass implements Serializable {

    private String name, lat, longi;

    public ModelClass() {
    }

    public ModelClass(String name, String lat, String longi) {
        this.name = name;
        this.lat = lat;
        this.longi = longi;
    }

    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLongi() {
        return longi;
    }

    public void setLongi(String longi) {
        this.longi = longi;
    }
}
