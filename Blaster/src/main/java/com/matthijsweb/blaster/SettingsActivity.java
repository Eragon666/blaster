package com.matthijsweb.blaster;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;

/**
 * Created by Matthijs on 19-12-13.
 */
public class SettingsActivity extends FragmentActivity {
    static final String TAG = "Blaster";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tv_guide);

        getActionBar().setDisplayHomeAsUpEnabled(true);

        if (savedInstanceState == null) {

            Bundle arguments = new Bundle();
            arguments.putString(TvGuideFragment.ARG_ITEM_ID,
                    getIntent().getStringExtra(TvGuideFragment.ARG_ITEM_ID));
            TvGuideFragment fragment = new TvGuideFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.item_detail_container, fragment)
                    .commit();
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
}
