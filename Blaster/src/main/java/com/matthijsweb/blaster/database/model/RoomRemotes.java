package com.matthijsweb.blaster.database.model;

/**
 * Created by Matthijs on 30-11-13.
 */
public class RoomRemotes {

    private int id;
    private int room_id;
    private int remote_id;
    private int task_id;

    public RoomRemotes() {}

    public RoomRemotes(int id, int room_id, int remote_id, int task_id) {
        this.id = id;
        this.room_id = room_id;
        this.remote_id = remote_id;
        this.task_id = task_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRoom_id() {
        return room_id;
    }

    public void setRoom_id(int room_id) {
        this.room_id = room_id;
    }

    public int getRemote_id() {
        return remote_id;
    }

    public void setRemote_id(int remote_id) {
        this.remote_id = remote_id;
    }

    public int getTask_id() {
        return task_id;
    }

    public void setTask_id(int task_id) {
        this.task_id = task_id;
    }
}
