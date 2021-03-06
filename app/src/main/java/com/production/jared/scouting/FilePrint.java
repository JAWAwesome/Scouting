package com.production.jared.scouting;

import android.util.Log;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Jared on 9/19/2015.
 */
public class FilePrint {

    // Variables
    private final String TAG = this.getClass().getName();
    File location;
    FileWriter fileWriter;

    // Constructor for the file
    public FilePrint(File inputFile)
    {
        Log.i(TAG, "Constructing File Printer at location: " + inputFile.toString());
        // File path
        location = inputFile;
        // Steps to output to file and create it if it dne
        try {
            fileWriter = new FileWriter(location,true);
        } catch (Exception e) {
            Log.i(TAG,e.getMessage());
        }
    }

    // Method to print to the file desired
    public void print(ArrayList<String> text) {
        Log.i(TAG, "Printing String");
        for (String here: text) {
            try {
                fileWriter.write(here);
                fileWriter.flush();
            } catch (IOException e) {
                // Display error
                Log.i(TAG, e.getMessage() + "");
            }
        }
        try {
            fileWriter.write("\n");
            fileWriter.flush();
        } catch (IOException e) {
            // Display error
            Log.i(TAG, e.getMessage() + "");
        }
    }

    // Close the file when done
    public void close()
    {
        Log.i(TAG,"Closing the file");
        try
        {
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
