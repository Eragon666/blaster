package com.matthijsweb.blaster.database.model;

/**
 * Created by Matthijs on 30-11-13.
 */
public class Tasks {
    //id INTEGER PRIMARY KEY, name STRING, description TEXT, buttonsarray TEXT

    private int id;
    private String name;
    private String description;
    private String buttonsarray;

    public Tasks() {}

    public Tasks(int id, String name, String description, String buttonsarray) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.buttonsarray = buttonsarray;
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

    public String getButtonsarray() {
        return buttonsarray;
    }

    public void setButtonsarray(String buttonsarray) {
        this.buttonsarray = buttonsarray;
    }
}
