package com.production.jared.scouting;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceActivity;

/**
 * Created by Jared on 12/23/2015.
 */
public class Preferences extends PreferenceActivity{

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
    }
}
