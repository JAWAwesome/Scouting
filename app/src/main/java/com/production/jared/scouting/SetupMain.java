package com.production.jared.scouting;

import android.content.Intent;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

/**
 * Created by Jared on 9/18/2015.
 */
public class SetupMain extends Fragment implements ChangeText{
    static Handler sender;
    Constants constants = new Constants();
    final String TAG = this.getClass().toString();
    public View setup;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.setup_main, container, false);
        setup = v;
        return v;
    }

    public static Fragment newInstance(Handler handler) {
        // Used for the fragments
        sender = handler;
        SetupMain f = new SetupMain();
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
        ((EditText) setup.findViewById(R.id.setupPersonNameEditText)).setText("");
        ((EditText) setup.findViewById(R.id.setupTeamNameEditText)).setText("");
        ((EditText) setup.findViewById(R.id.setupTeamNumberEditText)).setText("");
    }
}
