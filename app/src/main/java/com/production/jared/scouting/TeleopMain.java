package com.production.jared.scouting;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Jared on 9/18/2015.
 */
public class TeleopMain extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.teleop_main, container, false);
        return v;
    }

    public static Fragment newInstance() {
        TeleopMain f = new TeleopMain();
        Bundle b = new Bundle();
        f.setArguments(b);
        return f;
    }
}
