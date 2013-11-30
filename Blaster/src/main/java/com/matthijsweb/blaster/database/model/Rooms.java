package com.matthijsweb.blaster.database.model;

/**
 * Created by Matthijs on 30-11-13.
 */
public class Rooms {

    private int id;
    private String name;
    private int provider_id;

    public Rooms(){}

    public Rooms(String name, int provider_id) {
        this.name = name;
        this.provider_id = provider_id;
    }

    public Rooms(int id, String name, int provider_id) {
        this.id = id;
        this.name = name;
        this.provider_id = provider_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getProvider_id() {
        return provider_id;
    }

    public void setProvider_id(int provider_id) {
        this.provider_id = provider_id;
    }

}
