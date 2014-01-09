package com.matthijsweb.blaster;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;

import com.matthijsweb.blaster.database.DatabaseHelper;
import com.matthijsweb.blaster.database.RemoteFunctions;

public class TvGuideActivity extends FragmentActivity {
    /**
     * Tag used on log messages.
     */
    static final String TAG = "Blaster";
    sendBlast blaster;

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tv_guide);

        // Show the Up button in the action bar.
        getActionBar().setDisplayHomeAsUpEnabled(true);


        if (savedInstanceState == null) {
            // Create the detail fragment and add it to the activity
            // using a fragment transaction.
            Bundle arguments = new Bundle();
            arguments.putString(TvGuideFragment.ARG_ITEM_ID,
                    getIntent().getStringExtra(TvGuideFragment.ARG_ITEM_ID));
            TvGuideFragment fragment = new TvGuideFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.item_detail_container, fragment)
                    .commit();
        }

        context = getApplicationContext();

        GridView gridView = (GridView) findViewById(R.id.grid_view);

        // Instance of ImageAdapter Class
        gridView.setAdapter(new ImageGuideAdapter(this));

        blaster = new sendBlast(context);
        blaster.irInit();
    }

    @Override
    /**
     * Catch the volume up en volume down buttons and send them directly to the blaster to control
     * your tv.
     */
    public boolean dispatchKeyEvent(KeyEvent event) {
        int action = event.getAction();
        int keyCode = event.getKeyCode();

        RemoteFunctions remote = new RemoteFunctions();

        switch (keyCode) {
            case KeyEvent.KEYCODE_VOLUME_UP:
                if (action == KeyEvent.ACTION_UP) {
                    blaster.irSend(blaster.hex2dec(remote.getRemoteCode(15)));
                }
                return true;
            case KeyEvent.KEYCODE_VOLUME_DOWN:
                if (action == KeyEvent.ACTION_DOWN) {
                    blaster.irSend(blaster.hex2dec(remote.getRemoteCode(12)));
                }
                return true;
            default:
                return super.dispatchKeyEvent(event);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpTo(this, new Intent(this, MenuActivity.class));
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void openTvGuideInfoActivity(View guideItem) {

        Intent i = new Intent(getApplicationContext(), TvGuideInfo.class);
        // passing array index
        i.putExtra("id", guideItem.getId());
        startActivity(i);

    }


}


