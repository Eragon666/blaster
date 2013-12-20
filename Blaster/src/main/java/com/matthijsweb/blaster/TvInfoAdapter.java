package com.matthijsweb.blaster;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.matthijsweb.blaster.database.SyncDatabase;
import com.matthijsweb.blaster.database.TvGuideFunctions;
import com.matthijsweb.blaster.database.model.TvGuide;

/**
 * Created by Matthijs on 19-12-13.
 */
public class TvInfoAdapter extends BaseAdapter {
    private Context mContext;

    public TvInfoAdapter(Context c, String start, int provider, int channel) {
        mContext = c;
        SyncDatabase sync = new SyncDatabase();
        sync.execute();
        TvGuideFunctions tvGuide = new TvGuideFunctions();
        tvGuide.setTvGuideProgram(start, provider, channel);
    }

    public static TvGuide[] programs;

    @Override
    public int getCount() {
        return programs.length;
    }

    @Override
    public Object getItem(int position) {
        return programs[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        try {

            LayoutInflater inflater = LayoutInflater.from(mContext);
            ViewHolderInfo holder;

            // if it's not recycled, initialize some attributes
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.tv_guide_info_item, null, true);
                holder = new ViewHolderInfo();
                holder.title = (TextView) convertView
                        .findViewById(R.id.tvGuide_title);
                holder.time = (TextView) convertView
                        .findViewById(R.id.tvGuide_time);
                holder.description = (TextView) convertView
                        .findViewById(R.id.tvGuide_description);
                holder.position = position;
                convertView.setTag(holder);

            } else {
                holder = (ViewHolderInfo) convertView.getTag();
            }

            if (programs[position] != null) {
                holder.title.setText(programs[position].getName());
                holder.time.setText("" + programs[position].getTimeString());
                holder.description.setText(programs[position].getDescription());
            }

        } catch (Exception e) {
            Log.e("Blaster", "Error" + e);
        }

        return convertView;
    }

}

class ViewHolderInfo {
    TextView title;
    TextView time;
    TextView description;
    int position;
}

