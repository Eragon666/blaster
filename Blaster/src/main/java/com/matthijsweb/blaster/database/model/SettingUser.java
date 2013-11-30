package com.matthijsweb.blaster.database.model;

/**
 * Created by Matthijs on 30-11-13.
 */
public class SettingUser {

    private int id;
    private int country_id;

    public SettingUser(int id, int country_id) {
        this.id = id;
        this.country_id = country_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCountry_id() {
        return country_id;
    }

    public void setCountry_id(int country_id) {
        this.country_id = country_id;
    }
}
