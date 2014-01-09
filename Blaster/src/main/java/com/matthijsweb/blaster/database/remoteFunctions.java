package com.matthijsweb.blaster.database;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.matthijsweb.blaster.MenuActivity;

/**
 * Created by Matthijs on 9-1-14.
 */
public class RemoteFunctions {

    SQLiteDatabase database;

    public RemoteFunctions() {
        database = MenuActivity.db.getWritableDatabase();
    }

    /**
     * Get the corresponding remote code for a button_id
     * @param button_id
     * @return
     */
    public String getRemoteCode(int button_id) {

        String code;

        String SQL = "SELECT code FROM " + DatabaseHelper.TB_REMOTECODES + " WHERE remote_id = '1' AND button_id = '" + button_id + "'";

        Cursor result = database.rawQuery(SQL, new String[]{});

        if (result.getCount() > 0) {

            result.moveToNext();

            code = result.getString(result.getColumnIndex("code"));
        }
        else
        {
            code = "0000 0000 0000 0000 0000 0000 0000 0000";
        }

        return code;

    }
}
