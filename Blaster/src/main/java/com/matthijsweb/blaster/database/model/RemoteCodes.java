package com.matthijsweb.blaster.database.model;

/**
 * Created by Matthijs on 30-11-13.
 */
public class RemoteCodes {

    private int id;
    private int button_id;
    private String code;
    private int remote_id;
    private int last_update;

    public RemoteCodes() {}

    public RemoteCodes(int id, int button_id, String code, int remote_id, int last_update) {
        this.id = id;
        this.button_id = button_id;
        this.code = code;
        this.remote_id = remote_id;
        this.last_update = last_update;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getButton_id() {
        return button_id;
    }

    public void setButton_id(int button_id) {
        this.button_id = button_id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getRemote_id() {
        return remote_id;
    }

    public void setRemote_id(int remote_id) {
        this.remote_id = remote_id;
    }

    public int getLast_update() {
        return last_update;
    }

    public void setLast_update(int last_update) {
        this.last_update = last_update;
    }
}
