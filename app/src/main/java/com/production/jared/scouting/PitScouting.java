package com.production.jared.scouting;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
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
        log("View being created");
        // Create the fragment
        View v = inflater.inflate(R.layout.pit_scouting, container, false);
        scouting = v;
        parameters.add(constants.DEFAULT_ROBOT_DRIVE_SYSTEM_TYPE+",");
        parameters.add(constants.DEFAULT_ROBOT_DRIVE_SYSTEM_CONNECTION+",");
        parameters.add(constants.DEFAULT_ROBOT_DRIVE_SYSTEM_WHEEL_COUNT+",");
        parameters.add(constants.DEFAULT_ROBOT_DRIVE_SYSTEM_WHEEL_TYPE+",");
        parameters.add(constants.DEFAULT_ROBOT_DRIVE_SYSTEM_MOTOR_COUNT+",");
        parameters.add(constants.DEFAULT_ROBOT_DRIVE_SYSTEM_SHIFTER_USE+",");
        parameters.add(constants.DEFAULT_ROBOT_WEIGHT+",");
        parameters.add(constants.DEFAULT_ROBOT_HEIGHT+",");
        parameters.add(constants.DEFAULT_ROBOT_WIDTH+",");
        parameters.add(constants.DEFAULT_ROBOT_LENGTH+",");
        parameters.add(constants.DEFAULT_ROBOT_QUALITY+",");
        return v;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        log("View created");
        driveSystemType = (Spinner) getActivity().findViewById(R.id.drive_Type_Spinner);
        final ArrayAdapter<CharSequence> driveTypeAdapter  = ArrayAdapter.createFromResource(getActivity(), R.array.pits_Drive_Type_Options, android.R.layout.simple_spinner_item);
        driveTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        driveSystemType.setAdapter(driveTypeAdapter);
        driveSystemType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                parameters.set(0, driveTypeAdapter.getItem(position).toString() + ",");
                log("System type changed: "+driveTypeAdapter.getItem(position));
                if(driveTypeAdapter.getItem(position).equals("Other")) {
                    otherOption(0);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        driveConnectionType = (Spinner) getActivity().findViewById(R.id.drive_Connection_Type_Spinner);
        final ArrayAdapter<CharSequence> driveConnection = ArrayAdapter.createFromResource(getActivity(),R.array.pits_Connection_Type_Options,android.R.layout.simple_spinner_item);
        driveConnection.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        driveConnectionType.setAdapter(driveConnection);
        driveConnectionType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                parameters.set(1, driveConnection.getItem(position).toString() + ",");
                if(driveTypeAdapter.getItem(position).equals("Other")) {
                    otherOption(1);
                }
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
        final ArrayAdapter<CharSequence> wheelTypeAdapter  = ArrayAdapter.createFromResource(getActivity(),R.array.puts_Wheel_Type_Options,android.R.layout.simple_spinner_item);
        wheelTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        wheelType.setAdapter(wheelTypeAdapter);
        wheelType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                parameters.set(3, wheelTypeAdapter.getItem(position).toString() + ",");
                if(driveTypeAdapter.getItem(position).equals("Other")) {
                    otherOption(3);
                }
                log("Wheel type changed: "+wheelTypeAdapter.getItem(position));
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
                log("Weight changed: " + s);
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
                log("Height changed: " + s);
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
                log("Width changed: " + s);
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
                parameters.set(9, s.toString() + ",");
                log("Length changed: " + s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        quality = (Spinner) getActivity().findViewById(R.id.quality_Spinner);
        final ArrayAdapter<CharSequence> qualityAdapter  = ArrayAdapter.createFromResource(getActivity(),R.array.pits_Quality_Options,android.R.layout.simple_spinner_item);
        qualityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        quality.setAdapter(qualityAdapter);
        quality.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                parameters.set(10,qualityAdapter.getItem(position).toString()+",");
                if(driveTypeAdapter.getItem(position).equals("Other")) {
                    otherOption(10);
                }
                log("Quality changed: " + qualityAdapter.getItem(position));
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
                log("Shifter use changed: " + shifters);
                break;
            case R.id.shifter_No_Radio:
                if (checked)
                    // No
                    shifters = false;
                log("Shifter use changed: " + shifters);
                break;
        }

        parameters.set(5, Boolean.toString(shifters) + ",");
    }

    public void wheelUp(View view) {
        wheels.setText(String.valueOf(Integer.parseInt(wheels.getText().toString()) + 1));
        log("Wheel count changed: " + wheels.getText());
        parameters.set(2, wheels.getText().toString() + ",");
    }

    public void wheelDown(View view) {
        String temp = String.valueOf(Integer.parseInt(wheels.getText().toString()) - 1);
        if (temp.equals("-1")) {
            temp = "0";
        }
        wheels.setText(String.valueOf(temp));
        log("Wheel count changed: " + wheels.getText());
        parameters.set(2, wheels.getText().toString() + ",");
    }

    public void motorUp(View view) {
        motors.setText(String.valueOf(Integer.parseInt(motors.getText().toString()) + 1));
        log("Motor count changed: " + motors.getText());
        parameters.set(4, motors.getText().toString()+",");
    }

    public void motorDown(View view) {
        String temp = String.valueOf(Integer.parseInt(motors.getText().toString()) - 1);
        if (temp.equals("-1")) {
            temp = "0";
        }
        motors.setText(String.valueOf(temp));
        log("Motor count changed: " + motors.getText());
        parameters.set(4, motors.getText().toString() + ",");
    }

    @Override
    public void otherOption(final int place) {
        log("Other option needed");
        // Ask what to do
        final Dialog dialog = new Dialog(getActivity());
        // use the layout file created
        dialog.setContentView(R.layout.other_option);
        dialog.setTitle("Other Option!");
        Button submit = (Button)dialog.findViewById(R.id.otherOptionButton);
        final EditText value = (EditText) dialog.findViewById(R.id.otherOptionEditText);
        // Call the button methods for their actions
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                parameters.set(place, value.getText().toString());
                value.getText().toString();
            }
        });
        dialog.show();
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
