package com.production.jared.scouting;

import java.util.Locale;

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
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // Objects
    SectionsPagerAdapter mSectionsPagerAdapter;
    ViewPager mViewPager;

    // Variables
    public final String TAG = this.getClass().getName();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);
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

    // Button press test
    public void go(View view) {
        makeToast("Button Pressed");
        mViewPager.setCurrentItem(0);
    }

    // Log a message
    public void log(String msg) {
        Log.i(TAG, msg);
    }

    // Display toast
    public void makeToast(String msg) {
        log(msg);
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }
}
