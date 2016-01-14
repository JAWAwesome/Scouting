package com.production.jared.scouting;

import android.app.Activity;
import android.app.Dialog;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

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
        log("View being created");
        // Create the fragment
        View v = inflater.inflate(R.layout.auto_main, container, false);
        auto = v;
        parameters.add(constants.DEFAULT_AUTO_ACTION_1+",");
        parameters.add(constants.DEFAULT_AUTO_ACTION_2+",");
        parameters.add(constants.DEFAULT_AUTO_ACTION_3+",");
        parameters.add(constants.DEFAULT_AUTO_ACTION_4+",");
        parameters.add(constants.DEFAULT_AUTO_ACTION_5+",");
        return v;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        log("View created");
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
    public void otherOption(final int place) {
        log("Other option needed");
        // Ask what to do
        final Dialog dialog = new Dialog(getActivity());
        // use the layout file created
        dialog.setContentView(R.layout.other_option);
        dialog.setTitle("Other Option!");
        Button submit = (Button)dialog.findViewById(R.id.otherOptionButton);
        final EditText value = (EditText) dialog.findViewById(R.id.otherOptionEditText);
        // Call the button methods for their actions
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                parameters.set(place,value.getText().toString());
                value.getText().toString();
            }
        });
        dialog.show();
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
