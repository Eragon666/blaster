package com.matthijsweb.blaster.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Matthijs on 30-11-13.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    private static DatabaseHelper sInstance = null;
    private static final int DB_VERSION = 1;
    public static final String DB_NAME = "Blaster";

    public static final String TB_ROOMS = "Rooms";
    public static final String TB_ROOMREMOTES = "roomremotes";
    public static final String TB_TASKS = "remotetasks";

    public static final String TB_REMOTES = "remotes";
    public static final String TB_MANUFACTURERS = "manufacturers";
    public static final String TB_TYPES = "types";
    public static final String TB_BUTTONS = "buttons";
    public static final String TB_REMOTECODES = "remotecodes";

    public static final String TB_GUIDE = "tvguide";
    public static final String TB_PROVIDERS = "providers";
    public static final String TB_CHANNELS = "channels";

    public static final String TB_SETTINGUSER = "usersettings";
    public static final String TB_TABLEUPDATES = "tableupdates";
    public static final String TB_COUNTRIES = "countries";

    public static DatabaseHelper getInstance(Context context) {

        // Use the application context, which will ensure that you
        // don't accidentally leak an Activity's context.
        // See this article for more information: http://bit.ly/6LRzfx
        if (sInstance == null) {
            sInstance = new DatabaseHelper(context.getApplicationContext());
        }
        return sInstance;
    }


    private DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    /**
     * Create all the necessary database tables for the application
     */
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TB_ROOMS + " (id INTEGER PRIMARY KEY, name TEXT, provider_id INTEGER)");
        db.execSQL("CREATE TABLE " + TB_ROOMREMOTES + " (id INTEGER PRIMARY KEY, room_id INTEGER, remote_id INTEGER, task_id INTEGER, name TEXT)");
        db.execSQL("CREATE TABLE " + TB_TASKS + " (id INTEGER PRIMARY KEY, name TEXT, description TEXT, buttonsarray TEXT, last_update INTEGER)");

        db.execSQL("CREATE TABLE " + TB_REMOTES + " (id INTEGER PRIMARY KEY, last_update INTEGER)");
        db.execSQL("CREATE TABLE " + TB_MANUFACTURERS + " (id INTEGER PRIMARY KEY, name TEXT, last_update INTEGER)");
        db.execSQL("CREATE TABLE " + TB_TYPES + " (id INTEGER PRIMARY KEY, manufacturer_id INTEGER, name TEXT, last_update INTEGER)");
        db.execSQL("CREATE TABLE " + TB_BUTTONS + " (id INTEGER PRIMARY KEY, name TEXT, description TEXT, image TEXT, last_update INTEGER)");
        db.execSQL("CREATE TABLE " + TB_REMOTECODES + " (id INTEGER PRIMARY KEY, button_id INTEGER, code TEXT, remote_id INTEGER, last_update INTEGER)");

        db.execSQL("CREATE TABLE " + TB_GUIDE + " (id INTEGER PRIMARY KEY, provider_id INTEGER, name TEXT, starttime INTEGER, endtime INTEGER, channel INTEGER, description TEXT ,last_update INTEGER)");
        db.execSQL("CREATE TABLE " + TB_PROVIDERS + " (id INTEGER PRIMARY KEY, name TEXT, country_id INTEGER, last_update INTEGER)");
        db.execSQL("CREATE TABLE " + TB_CHANNELS + " (id INTEGER PRIMARY KEY, provider_id INTEGER, name TEXT, description TEXT, image TEXT, channel INTEGER,last_update INTEGER)");
        db.execSQL("CREATE TABLE " + TB_COUNTRIES + " (id INTEGER PRIMARY KEY, name TEXT, last_update INTEGER)");

        db.execSQL("CREATE TABLE " + TB_SETTINGUSER + " (id INTEGER PRIMARY KEY, country_id INTEGER)");
        db.execSQL("CREATE TABLE " + TB_TABLEUPDATES + " (tablename TEXT, version INTEGER, last_update INTEGER)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TB_REMOTES);
        db.execSQL("DROP TABLE IF EXISTS " + TB_ROOMREMOTES);
        db.execSQL("DROP TABLE IF EXISTS " + TB_TASKS);
        db.execSQL("DROP TABLE IF EXISTS " + TB_ROOMS);
        db.execSQL("DROP TABLE IF EXISTS " + TB_MANUFACTURERS);
        db.execSQL("DROP TABLE IF EXISTS " + TB_TYPES);
        db.execSQL("DROP TABLE IF EXISTS " + TB_BUTTONS);
        db.execSQL("DROP TABLE IF EXISTS " + TB_REMOTECODES);
        db.execSQL("DROP TABLE IF EXISTS " + TB_GUIDE);
        db.execSQL("DROP TABLE IF EXISTS " + TB_PROVIDERS);
        db.execSQL("DROP TABLE IF EXISTS " + TB_CHANNELS);
        db.execSQL("DROP TABLE IF EXISTS " + TB_COUNTRIES);
        db.execSQL("DROP TABLE IF EXISTS " + TB_SETTINGUSER);
        db.execSQL("DROP TABLE IF EXISTS " + TB_TABLEUPDATES);
        onCreate(db);
    }

}
