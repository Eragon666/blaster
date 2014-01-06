package com.matthijsweb.blaster;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.matthijsweb.blaster.database.TvGuideFunctions;

import java.text.SimpleDateFormat;

/**
 * Created by Matthijs on 7-12-13.
 */
public class ImageGuideAdapter extends BaseAdapter {
    private Context mContext;

    public static Integer[] tvGuideImages;

    public static String[][] tvGuideInfo;

    public ImageGuideAdapter(Context c) {
        mContext = c;
        TvGuideFunctions tvGuide = new TvGuideFunctions();
        //fill the tv guide vars with info from the database
        tvGuide.setTvGuideOverviewVars(c);
    }

    @Override
    public int getCount() {
        return tvGuideImages.length;
    }

    @Override
    public Object getItem(int position) {
        return tvGuideImages[position];
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

            //Find all views and save them in the holder
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

            holder.image.setImageResource(tvGuideImages[position]);

            //Give information for each channel, if no info in database for the current time, show a message.
            if (tvGuideInfo[position][1] != "") {
                holder.text.setText(tvGuideInfo[position][1]);
                holder.time.setText(new SimpleDateFormat("hh:mm").format(Integer.parseInt(tvGuideInfo[position][2])) + " - " + new SimpleDateFormat("hh:mm").format(Integer.parseInt(tvGuideInfo[position][3])));
            } else {
                holder.text.setText("Geen informatie beschikbaar");
            };
        } catch (Exception e) {
            Log.e("Blaster", "Error" + e);
        }

        return convertView;
    }

}

class ViewHolder {
    TextView text;
    TextView time;
    ImageButton image;
    int position;
}
