package com.production.jared.scouting;

import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

/**
 * Created by Jared on 9/18/2015.
 */
public class AutoMain extends Fragment implements ChangeText{
    static Handler sender;
    Constants constants = new Constants();
    final String TAG = this.getClass().toString();
    public View auto;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Create the fragment
        View v = inflater.inflate(R.layout.auto_main, container, false);
        auto = v;
        return v;
    }

    public static Fragment newInstance(Handler handler) {
        // Used for the fragment
        sender = handler;
        AutoMain f = new AutoMain();
        Bundle b = new Bundle();
        f.setArguments(b);
        return f;
    }

    @Override
    public void setText(int i, String message) {

    }

    @Override
    public void log(String message) {

    }

    @Override
    public void sendHandlerMessage(String text, int arg) {

    }

    @Override
    public void changeView() {

    }

    @Override
    public void clear() {

    }
}
