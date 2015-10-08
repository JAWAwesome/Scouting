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
public class TeleopMain extends Fragment {
    static Handler sender;
    Constants constants = new Constants();
    final String TAG = this.getClass().toString();

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Create the Fragment
        View v = inflater.inflate(R.layout.teleop_main, container, false);
        return v;
    }

    /*
    @Override
    public void onViewCreated(View v, Bundle b) {
        // Run after the fragment is made
        super.onViewCreated(v,b);
        // Listen for the text to change
        v.findViewById(R.id.setupPersonNameEditText).setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    // Send the changed text
                    sendHandlerMessage(((EditText)v.findViewById(R.id.setupPersonNameEditText)).getText().toString());
                }
            }
        });
    }
    */

    public static Fragment newInstance(Handler handler) {
        // Used for the fragment
        sender = handler;
        TeleopMain f = new TeleopMain();
        Bundle b = new Bundle();
        f.setArguments(b);
        return f;
    }

    /*
    public void sendHandlerMessage (String text) {
        // Send message with handler
        Message msg = sender.obtainMessage();
        Bundle b = new Bundle();
        b.putString(constants.PERSON_NAME,text);
        msg.setData(b);
        msg.arg1 = constants.HANDLER_PERSON_NAME;
        sender.sendMessage(msg);
    }
    */

    public void log(String msg) {
        Log.i(TAG, msg);
    }
}
