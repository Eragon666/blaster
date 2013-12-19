package com.matthijsweb.blaster;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.matthijsweb.blaster.database.TvGuideFunctions;


/**
 * Created by tj on 19-12-13.
 */
public class TvGuideInfo extends Activity{

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
        int image_id = i.getExtras().getInt("id");
        Log.i("Houston", "we have arrived in de nieuwe activity met id "+image_id);

        ImageView imageView = (ImageView) findViewById(R.id.tvGuide_channel_pic);
        String correctAnswer = "channel_" + image_id;
        imageView.setImageResource(getImageId(this, correctAnswer));

        int start = 0,
            end = 0,
            provider_id = 0,
            channel = 0;

        //get all info from the channel
        TvGuideFunctions tv = new TvGuideFunctions();
        tv.getTvGuide(start, end, provider_id, channel);
    }


}
