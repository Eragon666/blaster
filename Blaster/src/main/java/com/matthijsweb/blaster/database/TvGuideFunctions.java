package com.matthijsweb.blaster.database;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.matthijsweb.blaster.ImageGuideAdapter;
import com.matthijsweb.blaster.ItemDetailActivity;
import com.matthijsweb.blaster.database.model.Providers;
import com.matthijsweb.blaster.database.model.TvGuide;

/**
 * Created by Matthijs on 6-12-13.
 */
public class TvGuideFunctions {

    SQLiteDatabase database;

    public TvGuideFunctions() {
        database = ItemDetailActivity.db.getReadableDatabase();
    }

    /**
     * Get the TV guide for a specific date with all the programs in a seperate TvGuide model
     * @param start
     * @param end
     * @param provider_id
     * @param channel
     * @return
     */
    public TvGuide[] getTvGuide(int start, int end, int provider_id, int channel) {

        String SQL = "SELECT * FROM " + DatabaseHelper.TB_GUIDE + " WHERE channel = '" + channel + "' AND provider_id = '" + provider_id + "' " +
                " AND starttime >= '" + start + "' AND endtime <='" + end + "'";

        Cursor result = database.rawQuery(SQL, new String[]{});

        TvGuide guide;
        TvGuide[] resultArray = new TvGuide[result.getCount()];

        int i = 0;

        while (result.moveToNext()) {
            guide = new TvGuide();

            guide.setId(result.getInt(result.getColumnIndex("id")));
            guide.setProvider_id(result.getInt(result.getColumnIndex("provider_id")));
            guide.setName(result.getString(result.getColumnIndex("name")));
            guide.setChannel(result.getInt(result.getColumnIndex("channel")));
            guide.setDescription(result.getString(result.getColumnIndex("description")));
            guide.setEndtime(result.getInt(result.getColumnIndex("endtime")));
            guide.setStarttime(result.getInt(result.getColumnIndex("starttime")));

            resultArray[i] = guide;
        }

        return resultArray;

    }

    public void setTvGuideOverviewVars() {
        Log.i("Blaster", "hallo test 2");

        String SQL = "SELECT * FROM " + DatabaseHelper.TB_CHANNELS;

        Cursor result = database.rawQuery(SQL, new String[]{});

        int i = 0;
        String timestamp = SupportFunctions.getCurrentTimestamp();
        Cursor guideResult;
        String channel, starttime, endtime;

        while (result.moveToNext()) {
            Log.i("Blaster", "Loop test 1");
            SQL = "SELECT id, name, starttime, endtime FROM tvguide WHERE channel = ? AND starttime <= ? AND endtime >= ? LIMIT 1";

            channel = "" + result.getInt(result.getColumnIndex("id"));
            starttime = "" + result.getInt(result.getColumnIndex("starttime"));
            endtime = "" + result.getInt(result.getColumnIndex("endtime"));

            guideResult = database.rawQuery(SQL, new String[]{channel, starttime, endtime});

            ImageGuideAdapter.tvGuideInfo[i][0] = "" + guideResult.getInt(guideResult.getColumnIndex("id"));
            ImageGuideAdapter.tvGuideInfo[i][1] = guideResult.getString(guideResult.getColumnIndex("name"));
            ImageGuideAdapter.tvGuideInfo[i][2] = "" + guideResult.getInt(guideResult.getColumnIndex("starttime"));
            ImageGuideAdapter.tvGuideInfo[i][3] = "" + guideResult.getInt(guideResult.getColumnIndex("endtime"));
            i++;
        }

    }


    /**
     * Get the available providers in a country. Returns providers in a model.
     * @param country_id
     */
    public Providers[] getAvailaibleProviders(int country_id) {

        String SQL = "SELECT * FROM " + DatabaseHelper.TB_PROVIDERS + " WHERE country_id = '" + country_id + "'";

        Cursor result = database.rawQuery(SQL, new String[]{});

        Providers provider;
        Providers[] resultArray = new Providers[result.getCount()];

        int i = 0;

        while (result.moveToNext()) {

            provider = new Providers();

            provider.setId(result.getInt(result.getColumnIndex("id")));
            provider.setName(result.getString(result.getColumnIndex("name")));
            provider.setCountry_id(result.getInt(result.getColumnIndex("country_id")));

            resultArray[i] = provider;

        }

        return resultArray;

    }

}
