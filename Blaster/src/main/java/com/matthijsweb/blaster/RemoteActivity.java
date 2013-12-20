package com.matthijsweb.blaster;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;
import android.view.View;

/**
 * Created by Matthijs on 19-12-13.
 */
public class RemoteActivity extends FragmentActivity{
    static final String TAG = "Blaster";
    sendBlast blaster;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_remote);

        blaster = new sendBlast(this);
        blaster.irInit();

        getActionBar().setDisplayHomeAsUpEnabled(true);

        blaster.setButtons();
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

    public void irSend(View view) {
        blaster.irSend(view);
    }
}
