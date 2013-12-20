package com.matthijsweb.blaster.database;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.matthijsweb.blaster.MenuActivity;
import com.matthijsweb.blaster.TvGuideActivity;
import com.matthijsweb.blaster.database.model.Rooms;

/**
 * Created by Matthijs on 6-12-13.
 */
public class RoomsFunctions {

    SQLiteDatabase database;

    RoomsFunctions() {
        database = MenuActivity.db.getWritableDatabase();
    }

    /**
     * Get all available rooms
     * @return
     */
    private Rooms[] getRooms() {

        String SQL = "SELECT * FROM " + DatabaseHelper.TB_ROOMS;

        Cursor result = database.rawQuery(SQL, new String[]{});

        Rooms[] resultArray = new Rooms[result.getCount()];
        Rooms room;
        int i = 0;

        while (result.moveToNext()) {
            room = new Rooms();

            room.setId(result.getInt(result.getColumnIndex("id")));

            room.setName(result.getString(result.getColumnIndex("name")));

            room.setProvider_id(result.getInt(result.getColumnIndex("provider_id")));

            resultArray[i] = room;
        }

        return resultArray;

    }
}
