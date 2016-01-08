package com.production.jared.scouting;

import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by Jared on 10/7/2015.
 */
public interface ChangeText {
    public abstract void log (String message);
    public abstract ArrayList<String> get ();
}
