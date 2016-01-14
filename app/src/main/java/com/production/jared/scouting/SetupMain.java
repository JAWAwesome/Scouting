package com.production.jared.scouting;

import android.app.Activity;
import android.app.Dialog;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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
public class SetupMain extends Fragment implements ChangeText{
    static Handler sender;
    Constants constants = new Constants();
    final String TAG = this.getClass().toString();
    public View setup;
    ArrayList<String> parameters = new ArrayList<>();
    EditText personName;
    EditText teamName;
    EditText teamNumber;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Create the fragment
        View v = inflater.inflate(R.layout.setup_main, container, false);
        setup = v;
        log("View being created");
        parameters.add(constants.DEFAULT_PERSON_NAME + ",");
        parameters.add(constants.DEFAULT_TEAM_NAME+",");
        parameters.add(constants.DEFAULT_TEAM_NUMBER + ",");
        return v;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        log("View created");
        personName = (EditText) setup.findViewById(R.id.setupPersonNameEditText);
        personName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                parameters.set(0, s.toString() + ",");
                log("Person name chenged: " + s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        teamName = (EditText) setup.findViewById(R.id.setupTeamNameEditText);
        teamName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                parameters.set(1,s.toString()+",");
                log("Team name changed: " + s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        teamNumber = (EditText) setup.findViewById(R.id.setupTeamNumberEditText);
        teamNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                parameters.set(2, s.toString() + ",");
                log("Team number changed: " + s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        try {
            InputMethodManager inputMethodManager = (InputMethodManager) getActivity().getSystemService(Activity.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);
        } catch (Exception e) {
        }
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

    public void log(String message) {
        Log.i(TAG, message);
    }

    @Override
    public ArrayList<String> get() {
        return parameters;
    }
}
