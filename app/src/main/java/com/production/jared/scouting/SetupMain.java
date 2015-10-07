package com.production.jared.scouting;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

/**
 * Created by Jared on 9/18/2015.
 */
public class SetupMain extends Fragment {
    EditText name;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.setup_main, container, false);
        return v;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view,savedInstanceState);
        name = (EditText) view.findViewById(R.id.setupPersonNameEditText);
    }

    public static Fragment newInstance() {
        SetupMain f = new SetupMain();
        Bundle b = new Bundle();
        f.setArguments(b);
        return f;
    }

    public EditText getEditText() {
        return name;
    }
}
