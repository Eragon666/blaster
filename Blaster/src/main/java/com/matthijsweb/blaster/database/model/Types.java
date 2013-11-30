package com.matthijsweb.blaster.database.model;

/**
 * Created by Matthijs on 30-11-13.
 */
public class Types {
    //id INTEGER PRIMARY KEY, manufacturer_id INTEGER, typenr STRING, last_update INTEGER

    private int id;
    private int manufacturer_id;
    private String name;
    private int last_update;

    public Types(){}

    public Types(int id, int manufacturer_id, String name, int last_update) {
        this.id = id;
        this.manufacturer_id = manufacturer_id;
        this.name = name;
        this.last_update = last_update;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getManufacturer_id() {
        return manufacturer_id;
    }

    public void setManufacturer_id(int manufacturer_id) {
        this.manufacturer_id = manufacturer_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLast_update() {
        return last_update;
    }

    public void setLast_update(int last_update) {
        this.last_update = last_update;
    }
}
