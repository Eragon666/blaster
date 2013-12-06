package com.matthijsweb.blaster.database;

import android.database.sqlite.SQLiteDatabase;

import com.matthijsweb.blaster.ItemDetailActivity;

/**
 * Created by Matthijs on 7-12-13.
 */
public class getRemote {

    SQLiteDatabase database;

    getRemote() {
        database = ItemDetailActivity.db.getReadableDatabase();
    }

    private void getRemoteCodes(int remote_id) {

    }

}
