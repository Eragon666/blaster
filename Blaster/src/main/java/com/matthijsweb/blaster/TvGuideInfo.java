package com.matthijsweb.blaster;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;


/**
 * Created by tj on 19-12-13.
 */
public class TvGuideInfo extends Activity{

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tv_guide_info);

        // get intent data
        Intent i = getIntent();

        // Selected button id
        //int position = i.getExtras().getInt("id");

    }


}
