package com.matthijsweb.blaster.database;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.matthijsweb.blaster.ItemDetailActivity;

/**
 * Created by Matthijs on 6-12-13.
 */
public class configureRemotes {

    SQLiteDatabase database;

    public configureRemotes() {
        database = ItemDetailActivity.db.getWritableDatabase();
    }

    /**
     * Add a new remote to a room
     * @param remote_id
     * @param task_id
     * @param room_id
     * @param name
     */
    private void newRoomRemote(int remote_id, int task_id, int room_id, String name) {

        String SQL = "INSERT INTO " + DatabaseHelper.TB_ROOMREMOTES + "(remote_id, task_id, room_id, name) VALUES ('" + remote_id + "', '" + task_id + "', '" + room_id + "', '" + name + "')";

        database.rawQuery(SQL, new String[]{name});


    }

}
