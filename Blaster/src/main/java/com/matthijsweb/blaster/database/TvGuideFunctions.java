package com.matthijsweb.blaster.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.matthijsweb.blaster.ImageGuideAdapter;
import com.matthijsweb.blaster.MenuActivity;
import com.matthijsweb.blaster.TvGuideActivity;
import com.matthijsweb.blaster.TvInfoAdapter;
import com.matthijsweb.blaster.database.model.Providers;
import com.matthijsweb.blaster.database.model.TvGuide;

/**
 * Created by Matthijs on 6-12-13.
 */
public class TvGuideFunctions {

    SQLiteDatabase database;

    public TvGuideFunctions() {
        database = MenuActivity.db.getReadableDatabase();
    }

    /**
     * Get the TV guide for a specific date with all the programs in a seperate TvGuide model
     * @param start
     * @param end
     * @param provider_id
     * @param channel
     * @return
     */
    public TvGuide[] getTvGuide(String start, String end, int provider_id, int channel) {

        //Set time to 2030, and show all programs with a limit of 5
        if (end.equals("0")) {
            end = "1923936275";
        }

        String SQL = "SELECT * FROM " + DatabaseHelper.TB_GUIDE + " WHERE channel = '" + channel + "' AND provider_id = '" + provider_id + "' " +
                " AND endtime >= '" + start + "' AND endtime <='" + end + "' LIMIT 5";

        Cursor result = database.rawQuery(SQL, new String[]{});

        TvGuide guide;
        TvGuide[] resultArray = new TvGuide[result.getCount()];

        int i = 0;

        //Fill the model with all the info from the database
        while (result.moveToNext()) {
            guide = new TvGuide();

            guide.setId(result.getInt(result.getColumnIndex("id")));
            guide.setProvider_id(result.getInt(result.getColumnIndex("provider_id")));
            guide.setName(result.getString(result.getColumnIndex("name")));
            guide.setChannel(result.getInt(result.getColumnIndex("channel")));
            guide.setDescription(result.getString(result.getColumnIndex("description")));
            guide.setEndtime(result.getInt(result.getColumnIndex("endtime")));
            guide.setStarttime(result.getInt(result.getColumnIndex("starttime")));
            guide.setStarttime(result.getInt(result.getColumnIndex("starttime")));

            resultArray[i] = guide;
        }

        result.close();

        return resultArray;

    }

    /**
     * Fill the tv guide overview vars in the imageGuideAdapter with data from the database
     * @param context
     */
    public void setTvGuideOverviewVars(Context context) {

        String SQL = "SELECT * FROM " + DatabaseHelper.TB_CHANNELS;

        Cursor result = database.rawQuery(SQL, new String[]{});

        int i = 0;
        String timestamp = SupportFunctions.getCurrentTimestamp();
        Cursor guideResult;
        String channel, starttime, endtime;
        starttime = timestamp;
        endtime = timestamp;

        result.getCount();

        String[][] temp = new String[result.getCount()][4];
        Integer[] images = new Integer[result.getCount()];

        //Search program info for each channel
        while (result.moveToNext()) {
            SQL = "SELECT id, name, starttime, endtime FROM " + DatabaseHelper.TB_GUIDE + " WHERE channel = ? AND starttime <= ? AND endtime >= ? LIMIT 1";

            channel = "" + result.getInt(result.getColumnIndex("id"));

            guideResult = database.rawQuery(SQL, new String[]{channel, starttime, endtime});

            if (guideResult.moveToNext()) {

                temp[i][0] = "" + channel;
                temp[i][1] = guideResult.getString(guideResult.getColumnIndex("name"));
                temp[i][2] = "" + guideResult.getInt(guideResult.getColumnIndex("starttime"));
                temp[i][3] = "" + guideResult.getInt(guideResult.getColumnIndex("endtime"));
            } else {
                temp[i][0] = "" + channel;
                temp[i][1] = "";
                temp[i][2] = "";
                temp[i][3] = "";
            }

            images[i] = getImageId(context, result.getString(result.getColumnIndex("image")));

            i++;
        }
        ImageGuideAdapter.tvGuideInfo = new String[result.getCount()][3];

        ImageGuideAdapter.tvGuideInfo = temp;

        ImageGuideAdapter.tvGuideImages = new Integer[result.getCount()];

        ImageGuideAdapter.tvGuideImages = images;

        result.close();

    }

    public void setTvGuideProgram(String start, int provider_id, int channel) {

        TvInfoAdapter.programs = getTvGuide(start, "0", provider_id, channel);

    }

    /**
     * Get the image id from the R folder
     * @param context
     * @param imageName
     * @return
     */
    public static Integer getImageId(Context context, String imageName) {
        return context.getResources().getIdentifier("drawable/" + imageName, null, context.getPackageName());
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

        result.close();

        return resultArray;

    }

}
