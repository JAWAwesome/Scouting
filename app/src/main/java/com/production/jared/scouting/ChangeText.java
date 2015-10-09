package com.production.jared.scouting;

/**
 * Created by Jared on 10/7/2015.
 */
public interface ChangeText {
    public abstract void setText (int i, String message);
    public abstract void log (String message);
    public abstract void sendHandlerMessage (String text, int arg);
    public abstract void changeView ();
    public abstract void clear ();
}
