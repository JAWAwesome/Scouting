package com.production.jared.scouting;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Jared on 1/5/2016.
 */
public class PitScouting extends Fragment implements ChangeText {
    static Handler sender;
    Constants constants = new Constants();
    final String TAG = this.getClass().toString();
    public View scouting;
    ArrayList<String> parameters = new ArrayList<>();
    Spinner driveSystemType;
    Spinner driveConnectionType;
    ImageButton motorUpCount;
    ImageButton motorDownCount;
    TextView wheels;
    Spinner wheelType;
    ImageButton wheelUpCount;
    ImageButton wheelDownCount;
    TextView motors;
    RadioButton no;
    RadioButton yes;
    EditText weight;
    EditText height;
    EditText width;
    EditText length;
    Spinner quality;
    boolean shifters = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Create the fragment
        View v = inflater.inflate(R.layout.pit_scouting, container, false);
        scouting = v;
        parameters.add(",");
        parameters.add(",");
        parameters.add(",");
        parameters.add(",");
        parameters.add(",");
        parameters.add(",");
        parameters.add(",");
        parameters.add(",");
        parameters.add(",");
        parameters.add(",");
        parameters.add(",");
        return v;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        driveSystemType = (Spinner) getActivity().findViewById(R.id.drive_Type_Spinner);
        final ArrayAdapter<CharSequence> driveTypeAdapter  = ArrayAdapter.createFromResource(getActivity(), R.array.drive_Type_Options, android.R.layout.simple_spinner_item);
        driveTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        driveSystemType.setAdapter(driveTypeAdapter);
        driveSystemType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                parameters.set(0, driveTypeAdapter.getItem(position).toString() + ",");
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        driveConnectionType = (Spinner) getActivity().findViewById(R.id.drive_Connection_Type_Spinner);
        final ArrayAdapter<CharSequence> driveConnection = ArrayAdapter.createFromResource(getActivity(),R.array.connection_Type_Options,android.R.layout.simple_spinner_item);
        driveConnection.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        driveConnectionType.setAdapter(driveConnection);
        driveConnectionType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                parameters.set(1, driveConnection.getItem(position).toString() + ",");
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        wheelUpCount = (ImageButton) getActivity().findViewById(R.id.number_wheels_Up);
        wheelUpCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wheelUp(v);
            }
        });
        wheelDownCount = (ImageButton) getActivity().findViewById(R.id.number_Wheels_Down);
        wheelDownCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wheelDown(v);
            }
        });
        wheels = (TextView) getActivity().findViewById(R.id.wheel_Count);

        wheelType = (Spinner) getActivity().findViewById(R.id.wheel_Type_Spinner);
        final ArrayAdapter<CharSequence> wheelTypeAdapter  = ArrayAdapter.createFromResource(getActivity(),R.array.wheel_Type_Options,android.R.layout.simple_spinner_item);
        wheelTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        wheelType.setAdapter(wheelTypeAdapter);
        wheelType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                parameters.set(3, wheelTypeAdapter.getItem(position).toString() + ",");
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        motors = (TextView) getActivity().findViewById(R.id.motor_Count);
        motorUpCount = (ImageButton) getActivity().findViewById(R.id.number_Motors_Up);
        motorUpCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                motorUp(v);
            }
        });
        motorDownCount = (ImageButton) getActivity().findViewById(R.id.number_Motors_Down);
        motorDownCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                motorDown(v);
            }
        });

        no = (RadioButton) getActivity().findViewById(R.id.shifter_No_Radio);
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRadioButtonClicked(v);
            }
        });
        no.setChecked(true);
        yes = (RadioButton) getActivity().findViewById(R.id.shifter_Yes_Radio);
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRadioButtonClicked(v);
            }
        });

        weight = (EditText) getActivity().findViewById(R.id.width_EditText);
        weight.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                parameters.set(6, s.toString() + ",");
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        height = (EditText) getActivity().findViewById(R.id.height_EditText);
        height.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                parameters.set(7,s.toString()+",");
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        width = (EditText) getActivity().findViewById(R.id.width_EditText);
        width.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                parameters.set(8,s.toString()+",");
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        length = (EditText) getActivity().findViewById(R.id.length_EditText);
        length.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                parameters.set(9,s.toString()+",");
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        quality = (Spinner) getActivity().findViewById(R.id.quality_Spinner);
        final ArrayAdapter<CharSequence> qualityAdapter  = ArrayAdapter.createFromResource(getActivity(),R.array.quality_Options,android.R.layout.simple_spinner_item);
        qualityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        quality.setAdapter(qualityAdapter);
        quality.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                parameters.set(10,qualityAdapter.getItem(position).toString()+",");
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        try {
            InputMethodManager inputMethodManager = (InputMethodManager) getActivity().getSystemService(Activity.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);
        } catch (Exception e) {
        }
    }

    public static Fragment newInstance(Handler handler) {
        // Used for the fragment
        sender = handler;
        PitScouting f = new PitScouting();
        Bundle b = new Bundle();
        f.setArguments(b);
        return f;
    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.shifter_Yes_Radio:
                if (checked)
                    // Yes
                    shifters = true;
                break;
            case R.id.shifter_No_Radio:
                if (checked)
                    // No
                    shifters = false;
                break;
        }

        parameters.set(5, Boolean.toString(shifters) + ",");
    }

    public void wheelUp(View view) {
        wheels.setText(String.valueOf(Integer.parseInt(wheels.getText().toString()) + 1));
        parameters.set(2,wheels.getText().toString()+",");
    }

    public void wheelDown(View view) {
        String temp = String.valueOf(Integer.parseInt(wheels.getText().toString()) - 1);
        if (temp.equals("-1")) {
            temp = "0";
        }
        wheels.setText(String.valueOf(temp));
        parameters.set(2, wheels.getText().toString()+",");
    }

    public void motorUp(View view) {
        motors.setText(String.valueOf(Integer.parseInt(motors.getText().toString()) + 1));
        parameters.set(4, motors.getText().toString()+",");
    }

    public void motorDown(View view) {
        String temp = String.valueOf(Integer.parseInt(motors.getText().toString()) - 1);
        if (temp.equals("-1")) {
            temp = "0";
        }
        motors.setText(String.valueOf(temp));
        parameters.set(4, motors.getText().toString() + ",");
    }

    @Override
    public void otherOption() {

    }

    @Override
    public void log(String message) {
        Log.i(TAG, message);
    }

    @Override
    public ArrayList<String> get() {
        return parameters;
    }
}
