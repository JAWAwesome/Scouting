package com.production.jared.scouting;

import android.app.Activity;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import java.util.ArrayList;

/**
 * Created by Jared on 9/18/2015.
 */
public class AutoMain extends Fragment implements ChangeText{
    static Handler sender;
    Constants constants = new Constants();
    final String TAG = this.getClass().toString();
    public View auto;
    ArrayList<String> parameters = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Create the fragment
        View v = inflater.inflate(R.layout.auto_main, container, false);
        auto = v;
        parameters.add(",");
        parameters.add(",");
        parameters.add(",");
        parameters.add(",");
        parameters.add(",");
        return v;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        try {
            InputMethodManager inputMethodManager = (InputMethodManager) getActivity().getSystemService(Activity.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);
        } catch (Exception e) {
        }
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
    public void log(String message) {
        Log.i(TAG, message);
    }

    @Override
    public ArrayList<String> get() {
        return parameters;
    }
}
