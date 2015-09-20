package com.production.jared.scouting;

import java.io.File;
import java.util.Locale;

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
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // Objects
    SectionsPagerAdapter mSectionsPagerAdapter;
    ViewPager mViewPager;
    FilePrint printer;

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
    EditText personName;
    EditText teamName;
    EditText teamNumber;

    protected void onCreate(Bundle savedInstanceState) {
        // Super Call
        super.onCreate(savedInstanceState);

        // Initializations
        setContentView(R.layout.activity_main);
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
            makeToast("The file storage is unavailable");
        }

        // UI components
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
                makeToast(mViewPager.getAdapter().getPageTitle(currentPosition) + "");
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
        // Set defaults
        personName = (EditText) findViewById(R.id.setupPersonNameEditText);
        teamName = (EditText) findViewById(R.id.setupTeamNameEditText);
        teamNumber = (EditText) findViewById(R.id.setupTeamNumberEditText);
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

    // Used to set up the csv file initially
    public void setUpScoutingCSV() {
        String outputText = "";
        outputText += "Person Name,";
        outputText += "Team Name,";
        outputText += "Team Number,";
        outputText += "Auto Action 1 Name,";
        outputText += "Auto Action 2 Name,";
        outputText += "Auto Action 3 Name,";
        outputText += "Auto Action 4 Name,";
        outputText += "Auto Action 5 Name,";
        outputText += "Teleop Action 1 Name,";
        outputText += "Teleop Action 2 Name,";
        outputText += "Teleop Action 3 Name,";
        outputText += "Teleop Action 4 Name,";
        outputText += "Teleop Action 5 Name,";
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
                    return SetupMain.newInstance();
                case 1:
                    return AutoMain.newInstance();
                case 2:
                    return TeleopMain.newInstance();
                default:
                    return SetupMain.newInstance();
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

    // Gather all the files
    public void gatherAll() {
        //print(personName.getText().toString()+"");
        //personName.setText("");
        print(constants.DEFAULTPERSONNAME+",");
        //print(teamName.getText().toString() + "");
        //teamName.setText("");
        print(constants.DEFAULTTEAMNAME+",");
        //print(teamNumber.getText().toString() + "");
        //teamNumber.setText("");
        print(constants.DEFAULTTEAMNUMBER+",");
        print("\n");
        makeToast("File Saved");
    }

    // Save file
    public void wrapUp(View view) {
        gatherAll();
        changePageDown(view);
        changePageDown(view);
    }

    // Close and save
    public void closeWrapUp(View view) {
        gatherAll();
        close();
        if (Build.VERSION.SDK_INT>=21) {
            finishAndRemoveTask();
        } else {
            finish();
        }
    }

    // Log a message
    public void log(String msg) {
        Log.i(TAG, msg);
    }

    // Display toast
    public void makeToast(String msg) {
        log("Toast: " + msg);
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }
}
