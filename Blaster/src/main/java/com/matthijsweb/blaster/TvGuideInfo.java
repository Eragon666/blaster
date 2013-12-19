package com.matthijsweb.blaster;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.MenuItem;
import android.widget.GridView;
import android.widget.ImageView;

import com.matthijsweb.blaster.database.SupportFunctions;
import com.matthijsweb.blaster.database.TvGuideFunctions;
import com.matthijsweb.blaster.database.model.TvGuide;


/**
 * Created by tj on 19-12-13.
 */
public class TvGuideInfo extends FragmentActivity {

    public static int getImageId(Context context, String imageName) {
        return context.getResources().getIdentifier("drawable/" + imageName, null, context.getPackageName());
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tv_guide_info);

        // get intent data
        Intent i = getIntent();

        // Selected button id
        int channel_id = i.getExtras().getInt("id");

        int provider_id = 1,
            channel = channel_id;

        //get all info from the channel
        TvGuideFunctions tv = new TvGuideFunctions();
        TvGuide guide[] = tv.getTvGuide(SupportFunctions.getCurrentTimestamp(), "0", provider_id, channel);

        for(TvGuide program : guide) {
        }

        GridView gridView = (GridView) findViewById(R.id.grid_view);

        // Instance of ImageAdapter Class
        gridView.setAdapter(new TvInfoAdapter(this, SupportFunctions.getCurrentTimestamp(), provider_id, channel));

        ImageView imageView = (ImageView) findViewById(R.id.tvGuide_channel_pic);
        String correctAnswer = "channel_" + channel_id;
        imageView.setImageResource(getImageId(this, correctAnswer));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpTo(this, new Intent(this, TvGuideActivity.class));
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
