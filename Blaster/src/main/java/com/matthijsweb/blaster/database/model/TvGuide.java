package com.matthijsweb.blaster.database.model;

import java.text.SimpleDateFormat;

/**
 * Created by Matthijs on 30-11-13.
 */
public class TvGuide {

    private int id;
    private int provider_id;
    private String name;
    private int starttime;
    private int endtime;
    private int channel;
    private String description;
    private int last_update;

    public TvGuide() {}

    public TvGuide(int id, int provider_id, String name, int starttime, int endtime, int channel, String description, int last_update) {
        this.id = id;
        this.provider_id = provider_id;
        this.name = name;
        this.starttime = starttime;
        this.endtime = endtime;
        this.channel = channel;
        this.description = description;
        this.last_update = last_update;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProvider_id() {
        return provider_id;
    }

    public void setProvider_id(int provider_id) {
        this.provider_id = provider_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStarttime() {
        return starttime;
    }

    public void setStarttime(int starttime) {
        this.starttime = starttime;
    }

    public int getEndtime() {
        return endtime;
    }

    public void setEndtime(int endtime) {
        this.endtime = endtime;
    }

    public int getChannel() {
        return channel;
    }

    public void setChannel(int channel) {
        this.channel = channel;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getLast_update() {
        return last_update;
    }

    public void setLast_update(int last_update) {
        this.last_update = last_update;
    }

    public String getTimeString() {
        return new SimpleDateFormat("DD-MM hh:mm").format(this.starttime) + " - " + new SimpleDateFormat("hh:mm").format(this.endtime);
    }
}
