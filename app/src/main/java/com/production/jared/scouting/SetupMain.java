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
public class SetupMain extends Fragment implements ChangeText{
    static Handler sender;
    Constants constants = new Constants();
    final String TAG = this.getClass().toString();
    View thisView;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Create the fragment
        View v = inflater.inflate(R.layout.setup_main, container, false);
        thisView = v;
        return v;
    }

    public static Fragment newInstance(Handler handler) {
        // Used for the fragment
        sender = handler;
        SetupMain f = new SetupMain();
        Bundle b = new Bundle();
        f.setArguments(b);
        return f;
    }

    @Override
    public void onViewCreated(View v, Bundle b) {
        // Run after the fragment is made
        super.onViewCreated(v, b);
        // Listen for the person name text to change
        v.findViewById(R.id.setupPersonNameEditText).setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                log("Person name focus changed: " + hasFocus);
                if (!hasFocus) {
                    // Send the changed text
                    sendHandlerMessage(((EditText)v.findViewById(R.id.setupPersonNameEditText)).getText().toString(),constants.HANDLER_PERSON_NAME);
                }
            }
        });
        // Listen for the team name text to change
        v.findViewById(R.id.setupTeamNameEditText).setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                log("Team name focus changed: " + hasFocus);
                if(!hasFocus) {
                    // Send the changed text
                    sendHandlerMessage(((EditText)v.findViewById(R.id.setupTeamNameEditText)).getText().toString(),constants.HANDLER_TEAM_NAME);
                }
            }
        });
        // Listen for the team number text to change
        v.findViewById(R.id.setupTeamNumberEditText).setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                log("Team number focus changed: " + hasFocus);
                if (!hasFocus) {
                    // Send the changed text
                    sendHandlerMessage(((EditText) v.findViewById(R.id.setupTeamNumberEditText)).getText().toString(), constants.HANDLER_TEAM_NUMBER);
                }
            }
        });
    }

    @Override
    public void setText (int i,String msg) {
        ((EditText)thisView.findViewById(i)).setText(msg);
        log(((EditText) thisView.findViewById(i)).getText().toString());
    }

    @Override
    public void changeView() {
        (thisView.findViewById(R.id.setupPersonNameEditText)).clearFocus();
        (thisView.findViewById(R.id.setupTeamNameEditText)).clearFocus();
        (thisView.findViewById(R.id.setupTeamNumberEditText)).clearFocus();
        log(getActivity().findViewById(R.id.button_Open_Popup_Done).requestFocus() + " :focused");
    }

    @Override
    public void clear() {
        ((EditText)thisView.findViewById(R.id.setupPersonNameEditText)).setText("");
        ((EditText)thisView.findViewById(R.id.setupTeamNameEditText)).setText("");
        ((EditText)thisView.findViewById(R.id.setupTeamNumberEditText)).setText("");
    }

    @Override
    public void sendHandlerMessage (String text, int arg) {
        // Send message with handler
        Message msg = sender.obtainMessage();
        Bundle b = new Bundle();
        b.putString(constants.PERSON_NAME, text);
        msg.setData(b);
        msg.arg1 = arg;
        sender.sendMessage(msg);
    }

    @Override
    public void log(String msg) {
        Log.i(TAG,msg);
    }
}
