package com.production.jared.scouting;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.os.Vibrator;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AlertDialog;
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
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static android.app.ActionBar.NAVIGATION_MODE_TABS;

public class MainActivity extends AppCompatActivity {

    // Objects
    SectionsPagerAdapter mSectionsPagerAdapter;
    ViewPager mViewPager;
    FilePrint printer;
    BackupFileClass backUp;
    SharedPreferences sharedPreferences;
    ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
    static Handler sender;

    // Constants
    public static final String fileName = "Robotics Scouting.csv";
    public static final String backUpFileName = "backup.txt";
    public final String TAG = this.getClass().getName();
    Constants constants = new Constants();

    // Variables
    public int currentPosition = 0;
    public File location;
    public File backUpLocation;
    public boolean fileAlreadyExists;
    public boolean fileAccessAvailable;
    ArrayList<String> parameters = new ArrayList<String>();

    // UI components
    SetupMain setup;
    PitScouting pits;
    AutoMain auto;
    TeleopMain teleop;

    // Function thing required for app by android
    protected void onCreate(Bundle savedInstanceState) {
        // Super Call
        super.onCreate(savedInstanceState);
        log("OnCreate");
        setContentView(R.layout.activity_main);





        // Tabbed Action Bar
        final android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(false);
        actionBar.setNavigationMode(NAVIGATION_MODE_TABS);





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
                findViewById(R.id.dummyLocation).requestFocus();
            }

            @Override
            public void onPageSelected(int position) {
                currentPosition = position;
                //toast(mViewPager.getAdapter().getPageTitle(currentPosition) + "");
                actionBar.setSelectedNavigationItem(position);
                findViewById(R.id.dummyLocation).requestFocus();
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });





        // Add tabs
        if (actionBar!=null) {
            actionBar.addTab(actionBar.newTab().setText("Setup").setTabListener(new android.support.v7.app.ActionBar.TabListener() {
                @Override
                public void onTabSelected(android.support.v7.app.ActionBar.Tab tab, android.support.v4.app.FragmentTransaction ft) {
                    mViewPager.setCurrentItem(0);
                }

                @Override
                public void onTabUnselected(android.support.v7.app.ActionBar.Tab tab, android.support.v4.app.FragmentTransaction ft) {

                }

                @Override
                public void onTabReselected(android.support.v7.app.ActionBar.Tab tab, android.support.v4.app.FragmentTransaction ft) {

                }
            }));
            actionBar.addTab(actionBar.newTab().setText("Pits").setTabListener(new android.support.v7.app.ActionBar.TabListener() {
                @Override
                public void onTabSelected(android.support.v7.app.ActionBar.Tab tab, android.support.v4.app.FragmentTransaction ft) {
                    mViewPager.setCurrentItem(1);
                }

                @Override
                public void onTabUnselected(android.support.v7.app.ActionBar.Tab tab, android.support.v4.app.FragmentTransaction ft) {

                }

                @Override
                public void onTabReselected(android.support.v7.app.ActionBar.Tab tab, android.support.v4.app.FragmentTransaction ft) {

                }
            }));
            actionBar.addTab(actionBar.newTab().setText("Auto").setTabListener(new android.support.v7.app.ActionBar.TabListener() {
                @Override
                public void onTabSelected(android.support.v7.app.ActionBar.Tab tab, android.support.v4.app.FragmentTransaction ft) {
                    mViewPager.setCurrentItem(2);
                }

                @Override
                public void onTabUnselected(android.support.v7.app.ActionBar.Tab tab, android.support.v4.app.FragmentTransaction ft) {

                }

                @Override
                public void onTabReselected(android.support.v7.app.ActionBar.Tab tab, android.support.v4.app.FragmentTransaction ft) {

                }
            }));
            actionBar.addTab(actionBar.newTab().setText("Teleop").setTabListener(new android.support.v7.app.ActionBar.TabListener() {
                @Override
                public void onTabSelected(android.support.v7.app.ActionBar.Tab tab, android.support.v4.app.FragmentTransaction ft) {
                    mViewPager.setCurrentItem(3);
                }

                @Override
                public void onTabUnselected(android.support.v7.app.ActionBar.Tab tab, android.support.v4.app.FragmentTransaction ft) {

                }

                @Override
                public void onTabReselected(android.support.v7.app.ActionBar.Tab tab, android.support.v4.app.FragmentTransaction ft) {

                }
            }));
        }





        // Listener for changes in preferences
        updatePreferences();
        sharedPreferences.registerOnSharedPreferenceChangeListener(new SharedPreferences.OnSharedPreferenceChangeListener() {
            @Override
            public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
                log("preferences changed");
                updatePreferences();
            }
        });





        // Create a save based on time thread and setup
        fileSetup();
        scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                log("Thread running");
                backUp();
                log("Loaded to preferences");
            }
        },15,15, TimeUnit.SECONDS);
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
            Intent settings = new Intent(getApplicationContext(),Preferences.class);
            startActivityForResult(settings, 1);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onPause() {
        super.onPause();
        log("OnPause");
        backUp();
    }

    @Override
    public void onResume() {
        super.onResume();
        log("OnResume");
        fileSetup();
    }

    @Override
    public void onStop() {
        super.onStop();
        log("OnStop");
        backUp();
        print();
        close();
    }

    @Override
    public void onStart() {
        super.onStart();
        log("OnStart");
    }

    @Override
    public void onRestart() {
        super.onRestart();
        log("OnRestart");
        backUp();
    }

    @Override
    public void onBackPressed() {
        log("On back called, confirming close");
        vibrate(1000);
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Exit")
                .setMessage("Are you sure you want to close the app?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //loadToSharedPreferences();
                        print();
                        close();
                        finish();
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }

    @Override
    public void onActivityResult(int request, int result, Intent data) {
        if (request == 1) {
            log("preferences activity resulted, updating");
            updatePreferences();
        }
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
            backUpLocation = new File(this.getFilesDir(), backUpFileName);
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
            backUp = new BackupFileClass(backUpLocation);
        } else {
            toast("The file storage is unavailable");
        }
    }

    // Used to set up the csv file initially
    public void setUpScoutingCSV() {
        String outputText = "";
        outputText += constants.TIMESTAMP+",";
        outputText += constants.PERSON_NAME_PREFERENCE+",";
        outputText += constants.TEAM_NAME+",";
        outputText += constants.TEAM_NUMBER+",";
        outputText += constants.ROBOT_DRIVE_SYSTEM_TYPE+",";
        outputText += constants.ROBOT_DRIVE_SYSTEM_CONNECTION+",";
        outputText += constants.ROBOT_DRIVE_SYSTEM_WHEEL_COUNT+",";
        outputText += constants.ROBOT_DRIVE_SYSTEM_WHEEL_TYPE+",";
        outputText += constants.ROBOT_DRIVE_SYSTEM_MOTOR_COUNT+",";
        outputText += constants.ROBOT_DRIVE_SYSTEM_SHIFTER_USE+",";
        outputText += constants.ROBOT_WEIGHT+",";
        outputText += constants.ROBOT_HEIGTH+",";
        outputText += constants.ROBOT_WIDTH+",";
        outputText += constants.ROBOT_LENGTH+",";
        outputText += constants.ROBOT_QUALITY+",";
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
        ArrayList<String> temp = new ArrayList<>();
        temp.add(outputText);
        if (fileAccessAvailable) {
            try {
                printer.print(temp);
            } catch (Exception e) {
                log("Unable to print");
                log(e.getMessage() + "");
            }
        } else {
            log("Can't print because no file access");
        }
    }

    // Get values
    public void getValues() {
        // 12/01/2011 4:48:16 PM
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy h:mm:ss a");
        String formattedDate = sdf.format(date);
        parameters.add(formattedDate + ",");

        try {
            for (String here : setup.get()) {
                if (here != null) {
                    parameters.add(here);
                }
            }
        } catch (Exception e) {
            log(e.getStackTrace().toString());
        }

        try {
            for (String here : pits.get()) {
                if (here != null) {
                    parameters.add(here);
                }
            }
        } catch (Exception e) {
            log(e.getStackTrace().toString());
        }

        try {
            for (String here : auto.get()) {
                if (here != null) {
                    parameters.add(here);
                }
            }
        } catch (Exception e) {
            log(e.getStackTrace().toString());
        }

        try {
            for (String here : teleop.get()) {
                if (here != null) {
                    parameters.add(here);
                }
            }
        } catch (Exception e) {
            log(e.getStackTrace().toString());
        }

    }

    // Used to safely print text and save the ui
    public void print() {
        parameters.clear();
        getValues();
        if (fileAccessAvailable) {
            try {
                printer.print(parameters);
            } catch (Exception e) {
                log("Unable to print");
                log(e.getMessage() + "");
            }
        } else {
            log("Can't print because no file access");
        }
    }

    // Used to save the current state
    public void backUp() {
        backUp = new BackupFileClass(backUpLocation);
        getValues();
        if (fileAccessAvailable) {
            try {
                backUp.print(parameters);
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
        backUp.close();
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
                    setup = (SetupMain)SetupMain.newInstance(sender);
                    Log.i(TAG,setup.getTag()+setup.getId());
                    return setup;
                case 1:
                    pits = (PitScouting)PitScouting.newInstance(sender);
                    Log.i(TAG,pits.getTag()+pits.getId());
                    return pits;
                case 2:
                    auto = (AutoMain)AutoMain.newInstance(sender);
                    Log.i(TAG,auto.getTag()+auto.getId());
                    return auto;
                case 3:
                    teleop = (TeleopMain)TeleopMain.newInstance(sender);
                    Log.i(TAG,teleop.getTag()+teleop.getId());
                    return teleop;
                default:
                    setup = (SetupMain)SetupMain.newInstance(sender);
                    Log.i(TAG,setup.getTag()+setup.getId());
                    return setup;
            }
        }

        // Give the count of pages
        @Override
        public int getCount() {
            // Show 4 total pages.
            return 4;
        }

        // Give section title
        @Override
        public CharSequence getPageTitle(int position) {
            Locale l = Locale.getDefault();
            switch (position) {
                case 0:
                    return getString(R.string.title_Identification).toUpperCase(l);
                case 1:
                    return getString(R.string.title_Pits).toUpperCase(l);
                case 2:
                    return getString(R.string.title_Auto).toUpperCase(l);
                case 3:
                    return getString(R.string.title_Teleop).toUpperCase(l);
            }
            return null;
        }
    }

    // Used to update preference reference
    public void updatePreferences() {
        log("Updating preferences");
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
    }

    // Clear the text box values
    public void clearValues(View view) {
        recreate();
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
        Button open= (Button)dialog.findViewById(R.id.button_Open_Popup_Done);
        Button share= (Button)dialog.findViewById(R.id.button_Share_File_Popup_Done);
        Button delete= (Button)dialog.findViewById(R.id.button_Delete_File_Popup_Done);
        // Call the button methods for their actions
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
        open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                openFile(v);
            }
        });
        dialog.show();
        vibrate(1000);
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
        vibrate(1000);
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
        fileSetup();
    }

    // Button to share file
    public void shareFile(View view) {
        // Close to send
        print();
        close();
        // Create chooser option
        Intent share = new Intent(Intent.ACTION_SEND);
        share.setType("text/plain");
        share.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(location));
        // Display
        startActivity(Intent.createChooser(share, "Share a CSV"));
        // Restart the file writer
        fileSetup();
    }

    // Button to open the file
    public void openFile(View view) {
        print();
        close();
        log("Opening file with default");
        // Create the open intent
        Intent open = new Intent();
        open.setType(Intent.ACTION_VIEW);
        open.setDataAndType(Uri.fromFile(location), "text/csv");
        // Find the apps that can open it
        PackageManager pm = getPackageManager();
        List<ResolveInfo> apps = pm.queryIntentActivities(open, PackageManager.MATCH_DEFAULT_ONLY);
        // Open if can
        if(apps.size() > 0) {
            log("Found the app: " + apps.get(0).toString());
            startActivity(open);
        }
        else {
            log("Not app found");
        }
        // Restart the file writer
        fileSetup();
    }

    // Button to increase position
    public void changePageUp(View view) {
        hideKeyboard(this);
        if (currentPosition<3) {
            currentPosition ++;
        }
        mViewPager.setCurrentItem(currentPosition);
        findViewById(R.id.dummyLocation).requestFocus();
        log("Position Changed Up");
    }

    // Button to decrease position
    public void changePageDown(View view) {
        hideKeyboard(this);
        if (currentPosition > 0) {
            currentPosition--;
        }
        mViewPager.setCurrentItem(currentPosition);
        findViewById(R.id.dummyLocation).requestFocus();
        log("Position Changed Down");
    }

    // Hide keyboard
    public void hideKeyboard(Activity activity) {
        InputMethodManager inputMethodManager = (InputMethodManager)  activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
    }

    // Save file
    public void wrapUp(View view) {
        print();
        clearValues(view);
        changePageDown(view);
        changePageDown(view);
        changePageDown(view);
    }

    // Close and save
    public void closeWrapUp(View view) {
        print();
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

    // Vibrate for a time
    public void vibrate(long millis) {
        Vibrator v = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        v.vibrate(millis);
    }

    public void onButtonPress(View view) {

    }
}
