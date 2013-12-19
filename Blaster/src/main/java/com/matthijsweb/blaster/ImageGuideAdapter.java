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

import java.text.SimpleDateFormat;

/**
 * Created by Matthijs on 7-12-13.
 */
public class ImageGuideAdapter extends BaseAdapter {
    private Context mContext;

    public static Integer[] tvGuideImages = {
            R.drawable.channel_1, R.drawable.channel_2,
            R.drawable.channel_3, R.drawable.channel_4,
            R.drawable.channel_5, R.drawable.channel_6,
            R.drawable.channel_7, R.drawable.channel_8,
            R.drawable.channel_9, R.drawable.channel_10,
            R.drawable.channel_11,R.drawable.channel_13,
            R.drawable.channel_13,R.drawable.channel_14,
            R.drawable.channel_15,R.drawable.channel_16,
            R.drawable.channel_17,R.drawable.channel_18,
            R.drawable.channel_19,R.drawable.channel_20,
            R.drawable.channel_21
    };

    public static String[][] tvGuideInfo;

    public ImageGuideAdapter(Context c) {
        mContext = c;
        SyncDatabase sync = new SyncDatabase();
        sync.execute();
        TvGuideFunctions tvGuide = new TvGuideFunctions();
        tvGuide.setTvGuideOverviewVars();
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


        //Give information for each channel, if no info in database for the current time, show a message.
        if (tvGuideInfo.length > position && tvGuideInfo[position][1] != "") {
            holder.text.setText(tvGuideInfo[position][1]);
            holder.time.setText(new SimpleDateFormat("hh:mm").format(Integer.parseInt(tvGuideInfo[position][2])) + " - " + new SimpleDateFormat("hh:mm").format(Integer.parseInt(tvGuideInfo[position][3])));
        } else {
            holder.text.setText("Geen informatie beschikbaar");
        };

        /*try {
            holder.text.setText(tvGuideInfo[position][1]);
            holder.time.setText(tvGuideInfo[position][2] + " - " + tvGuideInfo[position][3]);
        } catch (Exception E) {
            Log.i("Blaster", "Error text " + E);
        }
*/

        return convertView;
    }

}

class ViewHolder {
    TextView text;
    TextView time;
    ImageButton image;
    int position;
}
