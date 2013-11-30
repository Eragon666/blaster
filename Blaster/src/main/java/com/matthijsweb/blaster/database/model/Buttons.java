package com.matthijsweb.blaster.database.model;

/**
 * Created by Matthijs on 30-11-13.
 */
public class Buttons {

    //id INTEGER PRIMARY KEY, name STRING, description TEXT, image STRING, last_update INTEGER

    private int id;
    private String name;
    private String description;
    private String image;
    private int last_update;

    public Buttons() {}

    public Buttons(int id, String name, String description, String image, int last_update) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.image = image;
        this.last_update = last_update;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getLast_update() {
        return last_update;
    }

    public void setLast_update(int last_update) {
        this.last_update = last_update;
    }
}
