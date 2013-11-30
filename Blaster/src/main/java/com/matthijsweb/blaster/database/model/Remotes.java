package com.matthijsweb.blaster.database.model;

/**
 * Created by Matthijs on 30-11-13.
 */
public class Remotes {

    private int id;
    private int manufacturer_id;
    private int type_id;
    private int last_update;

    public Remotes() {}

    public Remotes(int id, int manufacturer_id, int type_id, int last_update) {
        this.id = id;
        this.manufacturer_id = manufacturer_id;
        this.type_id = type_id;
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

    public int getType_id() {
        return type_id;
    }

    public void setType_id(int type_id) {
        this.type_id = type_id;
    }

    public int getLast_update() {
        return last_update;
    }

    public void setLast_update(int last_update) {
        this.last_update = last_update;
    }
}
