package com.matthijsweb.blaster.database;

import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.util.Log;

import com.matthijsweb.blaster.MenuActivity;
import com.matthijsweb.blaster.TvGuideActivity;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Matthijs on 1-12-13.
 */
public class SyncDatabase extends AsyncTask<String, Integer, String> {

    String TAG = "Blaster";
    SQLiteDatabase database;

    public SyncDatabase() {

    }

    @Override
    protected String doInBackground(String... params) {
        try {
            String tables[] = new String[]{DatabaseHelper.TB_BUTTONS, DatabaseHelper.TB_COUNTRIES, DatabaseHelper.TB_PROVIDERS, DatabaseHelper.TB_REMOTECODES,
                    DatabaseHelper.TB_REMOTES, DatabaseHelper.TB_TASKS, DatabaseHelper.TB_GUIDE, DatabaseHelper.TB_TYPES, DatabaseHelper.TB_MANUFACTURERS, DatabaseHelper.TB_CHANNELS};
            database = MenuActivity.db.getWritableDatabase();
            syncDatabases(tables);
        } catch (Exception e) {
            Log.e(TAG, "Error while syncing database: " + e);
        }

        database.close();

        return "Done";
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate();
    }

    /**
     * Synchronise all local databases with the external database
     * @param tables
     */
    private void syncDatabases(String tables[]) {

        String[] result;
        String JSONresult;

        for(String tablename: tables) {
            result = getLatestUpdate(tablename);

            JSONresult = connectToSyncServer(tablename, result[1]);

            //Save the data to the database. If there was a error, don't change the latest update table.
            if (!saveToDatabase(JSONresult, tablename).equals("error")) {
                setLatestUpdate(tablename);
            }
            else
            {
                Log.e(TAG, "there is a problem, doesnt set lastupdate time " + tablename);
            }
        }
    }

    /**
     * Get the latest update time and the current database version from the local database.
     * @param tablename
     * @return
     */
    private String[] getLatestUpdate(String tablename) {

        String SQL = "SELECT last_update, version FROM " + DatabaseHelper.TB_TABLEUPDATES + " WHERE tablename = ? LIMIT 1";

        Cursor cursor = database.rawQuery(SQL, new String[]{tablename});

        int version, last_update;

        //Get latest update date, if there is no info available, add a new record for this table.
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();

            version = cursor.getInt(cursor.getColumnIndex("version"));
            last_update = cursor.getInt(cursor.getColumnIndex("last_update"));

            return new String[]{"" + version, "" + last_update};
        } else {
            SQL = "INSERT INTO " + DatabaseHelper.TB_TABLEUPDATES + " (tablename, version, last_update) VALUES ('" + tablename + "', '1', '0')";
            database.execSQL(SQL);
            return new String[]{"1", "0"};
        }
    }

    /**
     * If sync was succeful, update the latest update time in the local database
     * @param tablename
     */
    public void setLatestUpdate(String tablename) {

        Long tsLong = System.currentTimeMillis()/1000;
        String ts = tsLong.toString();

        String SQL = "UPDATE " + DatabaseHelper.TB_TABLEUPDATES + " SET last_update = '" + ts + "' WHERE tablename = '" + tablename + "'";

        database.execSQL(SQL);
    }

    /**
     * Connect to the synchronisation server and get the rows where the last_update is higher than
     * the given timestamp. Data is returned in json format.
     *
     * @param table
     * @param lastUpdate
     */
    private String connectToSyncServer(String table, String lastUpdate) {
        // Create a new HttpClient and Post Header
        HttpClient httpclient = new DefaultHttpClient();
        HttpPost httppost = new HttpPost("http://www.loginweb.nl/android/syncdatabase.php");

        try {
            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
            nameValuePairs.add(new BasicNameValuePair("db", table));
            nameValuePairs.add(new BasicNameValuePair("last", lastUpdate));

            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

            // Execute HTTP Post Request
            ResponseHandler<String> responseHandler = new BasicResponseHandler();
            String response = httpclient.execute(httppost, responseHandler);

            return response;

        } catch (ClientProtocolException e) {
            Log.e(TAG, "CPE response " + e.toString());
            return "error";
        } catch (IOException e) {
            Log.e(TAG, "IOE response " + e.toString());
            return "error";
        }
    }


    /**
     * Save all JSON data to the database.
     * @param response
     */
    private String saveToDatabase(String response, String tablename) {

        database = MenuActivity.db.getWritableDatabase();

        try {
            JSONArray array = new JSONArray(response);
            //For every JSON row, search all fields and there values and make a SQL query
            for (int i = 0; i < array.length(); i++) {
                JSONObject row = array.getJSONObject(i);
                Iterator<String> myIter = row.keys();

                String fields = "";
                String values = "";
                String id = "";
                String tmp;

                while(myIter.hasNext()){
                    try {
                        if (fields != "") {
                            fields += ",";
                            values += ",";
                        }
                        tmp = myIter.next();
                        fields += "" + tmp;
                        values += "'" + row.get(tmp) + "'";

                        if (tmp.equals("id")) {
                            id = "" + row.get(tmp);
                        }

                    } catch (Exception e) {
                        Log.e("Blaster", "Error 1 = " + e);
                        return "error";
                    }
                }

                String SQLinsert = "INSERT INTO " + tablename + " (" + fields + ") VALUES (" + values + ")";

                // Try to insert new row into the database. If already exists (duplicate entry error), remove existing row and insert a new row
                try {
                    database.execSQL(SQLinsert);
                } catch (SQLiteConstraintException e) {

                    if (!id.equals("")) {
                        String SQLdelete = "DELETE FROM " + tablename + " WHERE id = '" + id + "'";
                        database.execSQL(SQLdelete);

                        database.execSQL(SQLinsert);
                    } else {
                        return "error";
                    }
                }
            }
        } catch (JSONException e) {
            Log.e(TAG, "JSON response " + e.toString());
            return "error";
        }

        return "done";
    }


}
