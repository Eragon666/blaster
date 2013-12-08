package com.matthijsweb.blaster;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

/**
 * Created by Matthijs on 7-12-13.
 */
public class ImageGuideAdapter extends BaseAdapter {
    private Context mContext;

    public Integer[] mThumbIds = {
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

    public ImageGuideAdapter(Context c) {
        mContext = c;

    }

    @Override
    public int getCount() {
        return mThumbIds.length;
    }

    @Override
    public Object getItem(int position) {
        return mThumbIds[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView = new ImageView(mContext);
        imageView.setImageResource(mThumbIds[position]);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setLayoutParams(new GridView.LayoutParams(250, 250));
        return imageView;
    }

}
