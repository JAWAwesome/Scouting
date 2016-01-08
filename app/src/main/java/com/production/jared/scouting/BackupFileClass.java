package com.production.jared.scouting;

import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Jared on 1/5/2016.
 */
public class BackupFileClass {
    // Variables
    private final String TAG = this.getClass().getName();
    File location;
    FileWriter fileWriter;

    // Constructor for the file
    public BackupFileClass(File inputFile) {
        // File path
        location = inputFile;
        Log.i(TAG, "Constructing File Printer at location: " + inputFile.toString());
        // Steps to output to file and create it if it dne
        try {
            fileWriter = new FileWriter(location,false);
        } catch (Exception e) {
            Log.i(TAG,e.getMessage());
        }
    }

    // Method to add to the file
    public void print(ArrayList<String> text) {
        for (String here: text) {
            try {
                fileWriter.write(here + "\n");
                fileWriter.flush();
            } catch (IOException e) {
                // Display error
                Log.i(TAG, e.getMessage() + "");
            }
        }
    }

    // Close the file when done
    public void close() {
        Log.i(TAG,"Closing the file");
        try {
            // Close the file
            fileWriter.close();
        }
        catch (IOException e)
        {
            // Display error
            Log.i(TAG, e.getMessage()+"");
        }
    }
}
