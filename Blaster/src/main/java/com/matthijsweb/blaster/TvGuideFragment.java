package com.matthijsweb.blaster;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.matthijsweb.blaster.content.Content;

/**
 * A fragment representing a single Item detail screen.
 * This fragment is either contained in a {@link MenuActivity}
 * in two-pane mode (on tablets) or a {@link TvGuideActivity}
 * on handsets.
 */
public class TvGuideFragment extends Fragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";

    private Content.MenuItem mItem;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public TvGuideFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            mItem = Content.ITEM_MAP.get(getArguments().getString(ARG_ITEM_ID));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_item_detail, container, false);

        if (mItem != null) {
            //((TextView) rootView.findViewById(R.id.item_detail)).setText(mItem.content);
        }

        return rootView;
    }
}
