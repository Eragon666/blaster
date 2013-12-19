package com.matthijsweb.blaster;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.matthijsweb.blaster.database.SyncDatabase;
import com.matthijsweb.blaster.database.TvGuideFunctions;
import com.matthijsweb.blaster.database.model.TvGuide;

import java.text.SimpleDateFormat;

/**
 * Created by Matthijs on 19-12-13.
 */
public class TvInfoAdapter extends BaseAdapter {
    private Context mContext;

    public TvInfoAdapter(Context c) {
        mContext = c;
        SyncDatabase sync = new SyncDatabase();
        sync.execute();
        TvGuideFunctions tvGuide = new TvGuideFunctions();
        tvGuide.setTvGuideOverviewVars();
    }

    TvGuide[] programs;

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
            ViewHolder holder;

            // if it's not recycled, initialize some attributes
            /*if (convertView == null) {*/
            convertView = inflater.inflate(R.layout.tv_guide_item, null, true);
            holder = new ViewHolder();
            holder.image = (ImageButton) convertView
                    .findViewById(R.id.tvGuide_button);
            holder.image.setId(Integer.parseInt(tvGuideInfo[position][0]));
            holder.text = (TextView) convertView
                    .findViewById(R.id.tvGuide_text);
            holder.time = (TextView) convertView
                    .findViewById(R.id.textView);
            convertView.setTag(holder);

           /* } else {
                holder = (ViewHolder) convertView.getTag();
            }*/

            holder.image.setImageResource(tvGuideImages[position]);
        } catch (Exception e) {
            Log.e("Blaster", "Error" + e);
        }

        return convertView;
    }

}

class ViewHolderInfo {
    TextView text;
    TextView time;
    ImageButton image;
    int position;
}

