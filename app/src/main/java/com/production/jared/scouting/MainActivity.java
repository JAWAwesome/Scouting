package com.production.jared.scouting;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // Objects
    SectionsPagerAdapter mSectionsPagerAdapter;
    ViewPager mViewPager;
    FilePrint printer;
    SharedPreferences sharedPreferences;
    ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();

    // Constants
    public static final String fileName = "Robotics Scouting.csv";
    public final String TAG = this.getClass().getName();
    Constants constants = new Constants();

    // Variables
    public int currentPosition = 0;
    public File location;
    public boolean fileAlreadyExists;
    public boolean fileAccessAvailable;

    // UI components
    Fragment setup;
    Fragment auto;
    Fragment teleop;
    EditText personName;
    EditText teamName;
    EditText teamNumber;





    // Function thing required for app by android
    protected void onCreate(Bundle savedInstanceState) {
        // Super Call
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Pager thing
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        // Listener for when page changes
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                currentPosition = position;
                toast(mViewPager.getAdapter().getPageTitle(currentPosition) + "");
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });





        // Listener for changes in preferences
        sharedPreferences = getPreferences(MODE_PRIVATE);
        sharedPreferences.registerOnSharedPreferenceChangeListener(new SharedPreferences.OnSharedPreferenceChangeListener() {
            @Override
            public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
                if (key.equals(constants.PERSON_NAME_PREFERENCE)) {
                    // Update person name from settings
                }
            }
        });





        // Create a save based on time thread and setup
        fileSetup();
        scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                log("Thread running");
                loadToSharedPreferences();
                log("Loaded to preferences");
            }
        },15,15, TimeUnit.SECONDS);





        // Initializer the UI components for use in app
        /*
        personName = (EditText) findViewById(R.id.setupPersonNameEditText);
        teamName = (EditText) findViewById(R.id.setupTeamNameEditText);
        teamNumber = (EditText) findViewById(R.id.setupTeamNumberEditText);
        */
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onPause() {
        super.onPause();
        loadToSharedPreferences();
    }

    @Override
    public void onResume() {
        super.onResume();
        fileSetup();
        pullFromPreferencesAndLoadToUI();
    }

    @Override
    public void onStop() {
        super.onStop();
        loadToSharedPreferences();
        pullFromPreferencesAndPrint();
        close();
    }

    @Override
    public void onStart() {
        super.onStart();
    }





    // App code that is not UI related
    // Setup the file printing stuff
    public void fileSetup () {
        // File printing related stuff
        // Hide keyboard from opening when app opens
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        // Check media mounted state
        fileAccessAvailable = Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
        // Check file access
        if (fileAccessAvailable) {
            log("File storage is available");
            // Create file
            location = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MOVIES), fileName);
            // Check if the file already exists before printing to it
            fileAlreadyExists = location.exists();
            log("The file: " + location.toString() + "  exists: " + fileAlreadyExists);
            // Create the printer
            printer = new FilePrint(location);
            if (!fileAlreadyExists) {
                try {
                    location.createNewFile();
                } catch (Exception e) {
                    log(e.getMessage()+"");
                }
                setUpScoutingCSV();
            }
        } else {
            toast("The file storage is unavailable");
        }
    }

    // Used to set up the csv file initially
    public void setUpScoutingCSV() {
        String outputText = "";
        outputText += constants.TIMESTAMP+";";
        outputText += constants.PERSON_NAME+",";
        outputText += constants.TEAM_NAME+",";
        outputText += constants.TEAM_NUMBER+",";
        outputText += constants.AUTO_ACTION_1+",";
        outputText += constants.AUTO_ACTION_2+",";
        outputText += constants.AUTO_ACTION_3+",";
        outputText += constants.AUTO_ACTION_4+",";
        outputText += constants.AUTO_ACTION_5+",";
        outputText += constants.TELEOP_ACTION_1+",";
        outputText += constants.TELEOP_ACTION_2+",";
        outputText += constants.TELEOP_ACTION_3+",";
        outputText += constants.TELEOP_ACTION_4+",";
        outputText += constants.TELEOP_ACTION_5+",";
        outputText += "\n";
        print(outputText);
    }

    // Used to safely print text and save the ui
    public void print(String textToPrint) {
        if (fileAccessAvailable) {
            try {
                printer.print(textToPrint);
            } catch (Exception e) {
                log("Unable to print");
                log(e.getMessage() + "");
            }
        } else {
            log("Can't print because no file access");
        }
    }

    // Close the file because done writing
    public void close() {
        printer.close();
    }

    // Log a message
    public void log(String msg) {
        Log.i(TAG, msg);
    }




    // UI things
    // Used to do the fragment launching
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        // Construct super
        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        // Choose the fragment to launch
        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a Fragment (defined as a static inner class below).
            switch (position) {
                case 0:
                    setup = SetupMain.newInstance();
                    return setup;
                case 1:
                    auto = AutoMain.newInstance();
                    return auto;
                case 2:
                    teleop = TeleopMain.newInstance();
                    return teleop;
                default:
                    setup = SetupMain.newInstance();
                    return setup;
            }
        }

        // Give the count of pages
        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        // Give section title
        @Override
        public CharSequence getPageTitle(int position) {
            Locale l = Locale.getDefault();
            switch (position) {
                case 0:
                    return getString(R.string.title_Identification).toUpperCase(l);
                case 1:
                    return getString(R.string.title_Auto).toUpperCase(l);
                case 2:
                    return getString(R.string.title_Teleop).toUpperCase(l);
            }
            return null;
        }
    }

    // Button to open the finishing dialog
    public void done(View view) {
        // Ask what to do
        final Dialog dialog = new Dialog(this);
        // use the layout file created
        dialog.setContentView(R.layout.done_popup_window);
        dialog.setTitle("What next!");
        Button save= (Button)dialog.findViewById(R.id.button_Save_Popup_Done);
        Button saveClose= (Button)dialog.findViewById(R.id.button_Close_Save_Popup_Done);
        Button share= (Button)dialog.findViewById(R.id.button_Share_File_Popup_Done);
        Button delete= (Button)dialog.findViewById(R.id.button_Delete_File_Popup_Done);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                wrapUp(v);
            }
        });
        saveClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                closeWrapUp(v);
            }
        });
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                shareFile(v);
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                deleteFile(v);
            }
        });
        dialog.show();
    }

    // Button to delete file
    public void deleteFile(View view) {
        // Verify that the file was supposed to be deleted
        final Dialog dialog = new Dialog(this);
        // use the layout file created
        dialog.setContentView(R.layout.popup_window);
        dialog.setTitle("Warning!");
        // Find the buttons
        Button delete=(Button)dialog.findViewById(R.id.button_PopUp_Delete);
        Button cancel=(Button)dialog.findViewById(R.id.button_PopUp_Cancel);
        // Show
        dialog.show();
        // Look to make sure the buttons are intentional
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Delete file
                dialog.dismiss();
                log("deleting file");
                location.delete();
                toast("File Deleted");
                fileSetup();
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Cancel delete
                dialog.dismiss();
                log("file delete cancelled");
            }
        });

    }

    // Button to share file
    public void shareFile(View view) {
        // Close to send
        loadToSharedPreferences();
        pullFromPreferencesAndPrint();
        close();
        // Create chooser option
        Intent share = new Intent(Intent.ACTION_SEND);
        share.setType("text/plain");
        share.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(location));
        // Display
        startActivity(Intent.createChooser(share,"Share a CSV"));
        // Restart the file writer
        fileSetup();
    }

    // Button to increase position
    public void changePageUp(View view) {
        hideKeyboard(view);
        if (currentPosition<2) {
            currentPosition ++;
        }
        mViewPager.setCurrentItem(currentPosition);
        log("Position Changed Up");
    }

    // Button to decrease position
    public void changePageDown(View view) {
        hideKeyboard(view);
        if (currentPosition > 0) {
            currentPosition--;
        }
        mViewPager.setCurrentItem(currentPosition);
        log("Position Changed Down");
    }

    // Hide keyboard
    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(),
                InputMethodManager.RESULT_UNCHANGED_SHOWN);
    }

    // Back up the inputs to the shared preferences location
    public void loadToSharedPreferences() {
        // Initializer the UI components for use in app
        /*
        teamName = (EditText) findViewById(R.id.setupTeamNameEditText);
        teamNumber = (EditText) findViewById(R.id.setupTeamNumberEditText);
        */
       // personName = (EditText) .findViewById(R.id.setupPersonNameEditText);


        // 12/01/2011 4:48:16 PM
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy h:mm:ss a");
        String formattedDate = sdf.format(date);

        sharedPreferences.edit().putString(constants.TIMESTAMP,formattedDate).apply();
        sharedPreferences.edit().putString(constants.PERSON_NAME,personName.getText().toString()).apply();

        /*
        sharedPreferences.edit().putString(constants.TEAM_NAME,teamName.getText().toString()).apply();
        sharedPreferences.edit().putString(constants.TEAM_NUMBER,teamNumber.getText().toString()).apply();
        */

        sharedPreferences.edit().putString(constants.TEAM_NAME,constants.TEAM_NAME).apply();
        sharedPreferences.edit().putString(constants.TEAM_NUMBER,constants.TEAM_NUMBER).apply();

        sharedPreferences.edit().putString(constants.AUTO_ACTION_1,constants.AUTO_ACTION_1).apply();
        sharedPreferences.edit().putString(constants.AUTO_ACTION_2,constants.AUTO_ACTION_2).apply();
        sharedPreferences.edit().putString(constants.AUTO_ACTION_3,constants.AUTO_ACTION_3).apply();
        sharedPreferences.edit().putString(constants.AUTO_ACTION_4,constants.AUTO_ACTION_4).apply();
        sharedPreferences.edit().putString(constants.AUTO_ACTION_5,constants.AUTO_ACTION_5).apply();
        sharedPreferences.edit().putString(constants.TELEOP_ACTION_1,constants.TELEOP_ACTION_1).apply();
        sharedPreferences.edit().putString(constants.TELEOP_ACTION_2,constants.TELEOP_ACTION_2).apply();
        sharedPreferences.edit().putString(constants.TELEOP_ACTION_3,constants.TELEOP_ACTION_3).apply();
        sharedPreferences.edit().putString(constants.TELEOP_ACTION_4,constants.TELEOP_ACTION_4).apply();
        sharedPreferences.edit().putString(constants.TELEOP_ACTION_5,constants.TELEOP_ACTION_5).apply();
        log("Shared Preferences Saved");
    }

    // Grab all the shared preferences to put back to UI
    public void pullFromPreferencesAndLoadToUI() {
        sharedPreferences.getString(constants.TIMESTAMP,constants.DEFAULT_TIMESTAMP);
        sharedPreferences.getString(constants.PERSON_NAME,constants.DEFAULT_PERSON_NAME);
        sharedPreferences.getString(constants.TEAM_NAME,constants.DEFAULT_TEAM_NAME);
        sharedPreferences.getString(constants.TEAM_NUMBER,constants.DEFAULT_TEAM_NUMBER);
        sharedPreferences.getString(constants.AUTO_ACTION_1,constants.DEFAULT_AUTO_ACTION_1);
        sharedPreferences.getString(constants.AUTO_ACTION_2,constants.DEFAULT_AUTO_ACTION_2);
        sharedPreferences.getString(constants.AUTO_ACTION_3,constants.DEFAULT_AUTO_ACTION_3);
        sharedPreferences.getString(constants.AUTO_ACTION_4,constants.DEFAULT_AUTO_ACTION_4);
        sharedPreferences.getString(constants.AUTO_ACTION_5,constants.DEFAULT_AUTO_ACTION_5);
        sharedPreferences.getString(constants.TELEOP_ACTION_1,constants.DEFAULT_TELEOP_ACTION_1);
        sharedPreferences.getString(constants.TELEOP_ACTION_2,constants.DEFAULT_TELEOP_ACTION_2);
        sharedPreferences.getString(constants.TELEOP_ACTION_3,constants.DEFAULT_TELEOP_ACTION_3);
        sharedPreferences.getString(constants.TELEOP_ACTION_4,constants.DEFAULT_TELEOP_ACTION_4);
        sharedPreferences.getString(constants.TELEOP_ACTION_5, constants.DEFAULT_TELEOP_ACTION_5);
    }

    // Gather all the files from the preferences and print
    public void pullFromPreferencesAndPrint() {
        print(sharedPreferences.getString(constants.TIMESTAMP,constants.DEFAULT_TIMESTAMP)+",");
        print(sharedPreferences.getString(constants.PERSON_NAME,constants.DEFAULT_PERSON_NAME)+",");
        print(sharedPreferences.getString(constants.TEAM_NAME,constants.DEFAULT_TEAM_NAME)+",");
        print(sharedPreferences.getString(constants.TEAM_NUMBER,constants.DEFAULT_TEAM_NUMBER)+",");
        print(sharedPreferences.getString(constants.AUTO_ACTION_1,constants.DEFAULT_AUTO_ACTION_1)+",");
        print(sharedPreferences.getString(constants.AUTO_ACTION_2,constants.DEFAULT_AUTO_ACTION_2)+",");
        print(sharedPreferences.getString(constants.AUTO_ACTION_3,constants.DEFAULT_AUTO_ACTION_3)+",");
        print(sharedPreferences.getString(constants.AUTO_ACTION_4,constants.DEFAULT_AUTO_ACTION_4)+",");
        print(sharedPreferences.getString(constants.AUTO_ACTION_5,constants.DEFAULT_AUTO_ACTION_5)+",");
        print(sharedPreferences.getString(constants.TELEOP_ACTION_1,constants.DEFAULT_TELEOP_ACTION_1)+",");
        print(sharedPreferences.getString(constants.TELEOP_ACTION_2,constants.DEFAULT_TELEOP_ACTION_2)+",");
        print(sharedPreferences.getString(constants.TELEOP_ACTION_3,constants.DEFAULT_TELEOP_ACTION_3)+",");
        print(sharedPreferences.getString(constants.TELEOP_ACTION_4,constants.DEFAULT_TELEOP_ACTION_4)+",");
        print(sharedPreferences.getString(constants.TELEOP_ACTION_5,constants.DEFAULT_TELEOP_ACTION_5));
        print("\n");
        toast("File Saved");
    }

    // Save file
    public void wrapUp(View view) {
        loadToSharedPreferences();
        pullFromPreferencesAndPrint();
        changePageDown(view);
        changePageDown(view);
    }

    // Close and save
    public void closeWrapUp(View view) {
        loadToSharedPreferences();
        pullFromPreferencesAndPrint();
        close();
        if (Build.VERSION.SDK_INT>=21) {
            finishAndRemoveTask();
        } else {
            finish();
        }
    }

    // Display toast
    public void toast(String msg) {
        log("Toast: " + msg);
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }
}
