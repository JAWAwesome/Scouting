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
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
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
    CheckBox sallyPortC;
    ImageView sallyPortI;
    CheckBox drawbridgeC;
    ImageView drawbridgeI;
    CheckBox moatC;
    ImageView moatI;
    CheckBox rockWallC;
    ImageView rockWallI;
    CheckBox roughTerrainC;
    ImageView roughTerrainI;
    CheckBox portcullisC;
    ImageView portcullisI;
    CheckBox rampartsC;
    ImageView rampartsI;
    CheckBox chevalDeFriseC;
    ImageView chevalDeFriseI;
    CheckBox secretPassageC;
    ImageView secretPassageI;
    CheckBox upperLeftPitsClaimC;
    ImageView upperLeftPitsClaimI;
    CheckBox upperMiddlePitsClaimC;
    ImageView upperMiddlePitsClaimI;
    CheckBox upperRightPitsClaimC;
    ImageView upperRightPitsClaimI;
    CheckBox lowerLeftPitsClaimC;
    ImageView lowerLeftPitsClaimI;
    CheckBox noGoalPitsClaimC;
    ImageView noGoalPitsClaimI;
    CheckBox lowerRightPitsClaimC;
    ImageView lowerRightPitsClaimI;
    RadioButton noAssistedShooter;
    RadioButton yesAssistedShooter;
    ImageView shooterTypeImage;
    Spinner shooterType;

    ImageView driveType;
    Spinner driveSystemType;
    ImageView connectionType;
    Spinner driveConnectionType;
    ImageButton motorUpCount;
    ImageButton motorDownCount;
    TextView wheels;
    ImageView wheelTypeImage;
    Spinner wheelType;
    ImageButton wheelUpCount;
    ImageButton wheelDownCount;
    TextView motors;
    RadioButton noShifters;
    RadioButton yesShifters;
    EditText weight;
    EditText height;
    EditText width;
    EditText length;
    Spinner quality;
    boolean shifters = false;
    boolean assisted = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        log("View being created");
        // Create the fragment
        View v = inflater.inflate(R.layout.pit_scouting, container, false);
        scouting = v;
        parameters.add(constants.DEFAULT_PITS_SALLY_PORT+",");
        parameters.add(constants.DEFAULT_PITS_DRAWBRIDGE+",");
        parameters.add(constants.DEFAULT_PITS_MOAT+",");
        parameters.add(constants.DEFAULT_PITS_ROCK_WALL+",");
        parameters.add(constants.DEFAULT_PITS_ROUGH_TERRAIN+",");
        parameters.add(constants.DEFAULT_PITS_PORTCULLIS+",");
        parameters.add(constants.DEFAULT_PITS_RAMPARTS+",");
        parameters.add(constants.DEFAULT_PITS_CHEVAL_DE_FRISE+",");
        parameters.add(constants.DEFAULT_PITS_HIDDEN_PASSAGE+",");
        parameters.add(constants.DEFAULT_PITS_UPPER_LEFT_GOAL+",");
        parameters.add(constants.DEFAULT_PITS_UPPER_MIDDLE_GOAL+",");
        parameters.add(constants.DEFAULT_PITS_UPPER_RIGHT_GOAL+",");
        parameters.add(constants.DEFAULT_PITS_BOTTOM_LEFT_GOAL+",");
        parameters.add(constants.DEFAULT_PITS_NO_GOAL+",");
        parameters.add(constants.DEFAULT_PITS_BOTTOM_RIGHT_GOAL+",");
        parameters.add(constants.DEFAULT_PITS_ASSISTED_SHOOTER+",");
        parameters.add(constants.DEFAULT_PITS_SHOOTER_TYPE+",");

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

        sallyPortC = (CheckBox) getActivity().findViewById(R.id.pits_Sally_Port_CheckBox);
        sallyPortC.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                parameters.set(constants.PITS_SALLY_PORT_CLAIM_INT, isChecked + ",");
            }
        });
        sallyPortI = (ImageView) getActivity().findViewById(R.id.pits_Sally_Port_Image);
        sallyPortI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                log("Sally port clicked");
                sallyPortC.setChecked(!sallyPortC.isChecked());
            }
        });

        drawbridgeC = (CheckBox) getActivity().findViewById(R.id.pits_Drawbridge_CheckBox);
        drawbridgeC.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                parameters.set(constants.PITS_DRAWBRIDGE_CLAIM_INT, isChecked + ",");
            }
        });
        drawbridgeI = (ImageView) getActivity().findViewById(R.id.pits_Drawbridge_Image);
        drawbridgeI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                log("Drawbridge clicked");
                drawbridgeC.setChecked(!drawbridgeC.isChecked());
            }
        });

        moatC = (CheckBox) getActivity().findViewById(R.id.pits_Moat_CheckBox);
        moatC.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                parameters.set(constants.PITS_MOAT_CLAIM_INT, isChecked + ",");
            }
        });
        moatI = (ImageView) getActivity().findViewById(R.id.pits_Moat_Image);
        moatI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                log("Moat clicked");
                moatC.setChecked(!moatC.isChecked());
            }
        });

        rockWallC = (CheckBox) getActivity().findViewById(R.id.pits_Rock_Wall_CheckBox);
        rockWallC.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                parameters.set(constants.PITS_ROCK_WALL_CLAIM_INT, isChecked + ",");
            }
        });
        rockWallI = (ImageView) getActivity().findViewById(R.id.pits_Rock_Wall_Image);
        rockWallI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                log("Rock wall clicked");
                rockWallC.setChecked(!rockWallC.isChecked());
            }
        });

        roughTerrainC = (CheckBox) getActivity().findViewById(R.id.pits_Rough_Terrain_CheckBox);
        roughTerrainC.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                parameters.set(constants.PITS_ROUGH_TERRAIN_CLAIM_INT, isChecked + ",");
            }
        });
        roughTerrainI = (ImageView) getActivity().findViewById(R.id.pits_Rough_Terrain_Image);
        roughTerrainI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                log("Rough terrain clicked");
                roughTerrainC.setChecked(!roughTerrainC.isChecked());
            }
        });

        portcullisC = (CheckBox) getActivity().findViewById(R.id.pits_Portcullis_CheckBox);
        portcullisC.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                parameters.set(constants.PITS_PORTCULLIS_CLAIM_INT, isChecked + ",");
            }
        });
        portcullisI = (ImageView) getActivity().findViewById(R.id.pits_Portcullis_Image);
        portcullisI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                log("Portcullis clicked");
                portcullisC.setChecked(!portcullisC.isChecked());
            }
        });

        rampartsC = (CheckBox) getActivity().findViewById(R.id.pits_Ramparts_CheckBox);
        rampartsC.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                parameters.set(constants.PITS_RAMPARTS_CLAIM_INT, isChecked + ",");
            }
        });
        rampartsI = (ImageView) getActivity().findViewById(R.id.pits_Ramparts_Image);
        rampartsI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                log("ramparts clicked");
                rampartsC.setChecked(!rampartsC.isChecked());
            }
        });

        chevalDeFriseC = (CheckBox) getActivity().findViewById(R.id.pits_Cheval_De_Frise_CheckBox);
        chevalDeFriseC.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                parameters.set(constants.PITS_CHEVAL_DE_FRISE_CLAIM_INT, isChecked + ",");
            }
        });
        chevalDeFriseI = (ImageView) getActivity().findViewById(R.id.pits_Cheval_De_Frise_Image);
        chevalDeFriseI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                log("Cheval de Frise clicked");
                chevalDeFriseC.setChecked(!chevalDeFriseC.isChecked());
            }
        });

        secretPassageC = (CheckBox) getActivity().findViewById(R.id.pits_Hidden_Passage_CheckBox);
        secretPassageC.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                parameters.set(constants.PITS_SECRET_PASSAGE_CLAIM_INT, isChecked + ",");
            }
        });
        secretPassageI = (ImageView) getActivity().findViewById(R.id.pits_Hidden_Passage_Image);
        secretPassageI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                log("Secred passage clicked");
                secretPassageC.setChecked(!secretPassageC.isChecked());
            }
        });

        upperLeftPitsClaimC = (CheckBox) getActivity().findViewById(R.id.pits_Upper_Goal_Left_Check);
        upperLeftPitsClaimC.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                parameters.set(constants.PITS_UPPER_LEFT_CLAIM_INT, isChecked + ",");
            }
        });
        upperLeftPitsClaimI = (ImageView) getActivity().findViewById(R.id.pits_Upper_Goal_Left);
        upperLeftPitsClaimI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                log("Upper Left Goal clicked");
                upperLeftPitsClaimC.setChecked(!upperLeftPitsClaimC.isChecked());
            }
        });

        upperMiddlePitsClaimC = (CheckBox) getActivity().findViewById(R.id.pits_Upper_Goal_Middle_Check);
        upperMiddlePitsClaimC.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                parameters.set(constants.PITS_UPPER_MIDDLE_CLAIM_INT, isChecked + ",");
            }
        });
        upperMiddlePitsClaimI = (ImageView) getActivity().findViewById(R.id.pits_Upper_Goal_Middle);
        upperMiddlePitsClaimI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                log("Upper Middle Goal clicked");
                upperMiddlePitsClaimC.setChecked(!upperMiddlePitsClaimC.isChecked());
            }
        });

        upperRightPitsClaimC = (CheckBox) getActivity().findViewById(R.id.pits_Upper_Goal_Right_Check);
        upperRightPitsClaimC.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                parameters.set(constants.PITS_UPPER_RIGHT_CLAIM_INT, isChecked + ",");
            }
        });
        upperRightPitsClaimI = (ImageView) getActivity().findViewById(R.id.pits_Upper_Goal_Right);
        upperRightPitsClaimI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                log("Upper Right Goal clicked");
                upperRightPitsClaimC.setChecked(!upperRightPitsClaimC.isChecked());
            }
        });

        lowerLeftPitsClaimC = (CheckBox) getActivity().findViewById(R.id.pits_Lower_Goal_Left_Check);
        lowerLeftPitsClaimC.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                parameters.set(constants.PITS_LOWER_LEFT_CLAIM_INT, isChecked + ",");
            }
        });
        lowerLeftPitsClaimI = (ImageView) getActivity().findViewById(R.id.pits_Lower_Left_Goal);
        lowerLeftPitsClaimI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                log("Lower Left Goal clicked");
                lowerLeftPitsClaimC.setChecked(!lowerLeftPitsClaimC.isChecked());
            }
        });

        noGoalPitsClaimC = (CheckBox) getActivity().findViewById(R.id.pits_No_Goal_Check);
        noGoalPitsClaimC.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                parameters.set(constants.PITS_NO_GOAL_CLAIM_INT, isChecked + ",");
            }
        });
        noGoalPitsClaimI = (ImageView) getActivity().findViewById(R.id.pits_No_Goal);
        noGoalPitsClaimI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                log("No Goal clicked");
                noGoalPitsClaimC.setChecked(!noGoalPitsClaimC.isChecked());
            }
        });

        lowerRightPitsClaimC = (CheckBox) getActivity().findViewById(R.id.pits_Lower_Goal_Right_Check);
        lowerRightPitsClaimC.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                parameters.set(constants.PITS_LOWER_RIGHT_CLAIM_INT, isChecked + ",");
            }
        });
        lowerRightPitsClaimI = (ImageView) getActivity().findViewById(R.id.pits_Lower_Right_Goal);
        lowerRightPitsClaimI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                log("Lower Right Goal clicked");
                lowerRightPitsClaimC.setChecked(!lowerRightPitsClaimC.isChecked());
            }
        });

        noAssistedShooter = (RadioButton) getActivity().findViewById(R.id.assisted_Shooter_No_Radio);
        noAssistedShooter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRadioButtonClicked(v);
            }
        });
        noAssistedShooter.setChecked(true);
        yesAssistedShooter = (RadioButton) getActivity().findViewById(R.id.assisted_Shooter_Yes_Radio);
        yesAssistedShooter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRadioButtonClicked(v);
            }
        });

        shooterTypeImage = (ImageView) getActivity().findViewById(R.id.shooter_Type_Image);
        shooterType = (Spinner) getActivity().findViewById(R.id.pits_Shooter_Type_Spinner);
        final ArrayAdapter<CharSequence> shooterTypeAdapter  = ArrayAdapter.createFromResource(getActivity(), R.array.shooter_Type_Choices, android.R.layout.simple_spinner_item);
        shooterTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        shooterType.setAdapter(shooterTypeAdapter);
        shooterType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                parameters.set(constants.PITS_SHOOTER_TYPE_INT, shooterTypeAdapter.getItem(position).toString() + ",");
                log("System type changed: " + shooterTypeAdapter.getItem(position));
                shooterTypeImage.setImageResource(constants.SHOOTER_TYPE_DRAWABLES[position]);
                if (shooterTypeAdapter.getItem(position).equals("Other")) {
                    otherOption(constants.PITS_SHOOTER_TYPE_INT);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        shooterTypeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(shooterTypeAdapter.getCount()>shooterType.getSelectedItemPosition()+1) {
                    shooterType.setSelection(shooterType.getSelectedItemPosition() + 1);
                } else {
                    shooterType.setSelection(0);
                }
                if(shooterTypeAdapter.getItem(shooterType.getSelectedItemPosition()).equals("Other")) {
                    shooterType.setSelection(0);
                }
            }
        });



        driveType = (ImageView) getActivity().findViewById(R.id.drive_Type_Image);
        driveSystemType = (Spinner) getActivity().findViewById(R.id.drive_Type_Spinner);
        final ArrayAdapter<CharSequence> driveTypeAdapter  = ArrayAdapter.createFromResource(getActivity(), R.array.pits_Drive_Type_Options, android.R.layout.simple_spinner_item);
        driveTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        driveSystemType.setAdapter(driveTypeAdapter);
        driveSystemType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                parameters.set(constants.PITS_DRIVE_CONNECTION_INT, driveTypeAdapter.getItem(position).toString() + ",");
                log("System type changed: " + driveTypeAdapter.getItem(position));
                driveType.setImageResource(constants.DRIVE_TYPE_DRAWABLES[position]);
                if (driveTypeAdapter.getItem(position).equals("Other")) {
                    otherOption(constants.PITS_DRIVE_TYPE_INT);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        driveType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(driveTypeAdapter.getCount()>driveSystemType.getSelectedItemPosition()+1) {
                    driveSystemType.setSelection(driveSystemType.getSelectedItemPosition() + 1);
                } else {
                    driveSystemType.setSelection(0);
                }
                if(driveTypeAdapter.getItem(driveSystemType.getSelectedItemPosition()).equals("Other")) {
                    driveSystemType.setSelection(0);
                }
            }
        });

        connectionType = (ImageView) getActivity().findViewById(R.id.drive_Connection_Image);
        driveConnectionType = (Spinner) getActivity().findViewById(R.id.drive_Connection_Type_Spinner);
        final ArrayAdapter<CharSequence> driveConnection = ArrayAdapter.createFromResource(getActivity(),R.array.pits_Connection_Type_Options,android.R.layout.simple_spinner_item);
        driveConnection.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        driveConnectionType.setAdapter(driveConnection);
        driveConnectionType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                parameters.set(constants.PITS_DRIVE_CONNECTION_INT, driveConnection.getItem(position).toString() + ",");
                log("Connection type changed: " + driveConnection.getItem(position).toString());
                connectionType.setImageResource(constants.DRIVE_CONNECTION_TYPE_DRAWABLES[position]);
                if (driveTypeAdapter.getItem(position).equals("Other")) {
                    otherOption(constants.PITS_DRIVE_CONNECTION_INT);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        connectionType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(driveConnection.getCount()>driveConnectionType.getSelectedItemPosition()+1) {
                    driveConnectionType.setSelection(driveConnectionType.getSelectedItemPosition() + 1);
                } else {
                    driveConnectionType.setSelection(0);
                }
                if(driveConnection.getItem(driveConnectionType.getSelectedItemPosition()).equals("Other")) {
                    driveConnectionType.setSelection(0);
                }
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

        wheelTypeImage = (ImageView) getActivity().findViewById(R.id.wheel_Type_Image);
        wheelType = (Spinner) getActivity().findViewById(R.id.wheel_Type_Spinner);
        final ArrayAdapter<CharSequence> wheelTypeAdapter  = ArrayAdapter.createFromResource(getActivity(),R.array.puts_Wheel_Type_Options,android.R.layout.simple_spinner_item);
        wheelTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        wheelType.setAdapter(wheelTypeAdapter);
        wheelType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                parameters.set(constants.PITS_WHEEL_TYPE_INT, wheelTypeAdapter.getItem(position).toString() + ",");
                wheelTypeImage.setImageResource(constants.WHEEL_TYPE_DRAWABLES[position]);
                if (driveTypeAdapter.getItem(position).equals("Other")) {
                    otherOption(constants.PITS_WHEEL_TYPE_INT);
                }
                log("Wheel type changed: " + wheelTypeAdapter.getItem(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        wheelTypeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (wheelTypeAdapter.getCount() > wheelType.getSelectedItemPosition() + 1) {
                    wheelType.setSelection(wheelType.getSelectedItemPosition() + 1);
                } else {
                    wheelType.setSelection(0);
                }
                if (wheelTypeAdapter.getItem(wheelType.getSelectedItemPosition()).equals("Other")) {
                    wheelType.setSelection(0);
                }
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

        noShifters = (RadioButton) getActivity().findViewById(R.id.shifter_No_Radio);
        noShifters.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRadioButtonClicked(v);
            }
        });
        noShifters.setChecked(true);
        yesShifters = (RadioButton) getActivity().findViewById(R.id.shifter_Yes_Radio);
        yesShifters.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRadioButtonClicked(v);
            }
        });

        weight = (EditText) getActivity().findViewById(R.id.robot_Weight_EditText);
        weight.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                parameters.set(constants.PITS_WEIGHT_INT, s.toString() + ",");
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
                parameters.set(constants.PITS_HEIGHT_INT,s.toString()+",");
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
                parameters.set(constants.PITS_WIDTH_INT,s.toString()+",");
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
                parameters.set(constants.PITS_LENGTH_INT, s.toString() + ",");
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
                parameters.set(constants.PITS_QUALITY_INT,qualityAdapter.getItem(position).toString()+",");
                if(qualityAdapter.getItem(position).equals("Other")) {
                    otherOption(constants.PITS_QUALITY_INT);
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
                if (checked) {
                    // Yes
                    shifters = true;
                    log("Shifter use changed: " + shifters);
                }
                break;
            case R.id.shifter_No_Radio:
                if (checked) {
                    // No
                    shifters = false;
                    log("Shifter use changed: " + shifters);
                }
                break;
            case R.id.assisted_Shooter_No_Radio:
                if(checked) {
                    // No
                    assisted = false;
                    log("Assisted Shooter use changed: " + assisted);
                }
            case R.id.assisted_Shooter_Yes_Radio:
                if(checked) {
                    // Yes
                    assisted = true;
                    log("Assisted Shooter use changed: " + assisted);
                }
        }

        parameters.set(constants.PITS_SHIFTER_USE_INT, Boolean.toString(shifters) + ",");
        parameters.set(constants.PITS_ASSISTED_SHOOTER_INT, Boolean.toString(assisted) + ",");
    }

    public void wheelUp(View view) {
        wheels.setText(String.valueOf(Integer.parseInt(wheels.getText().toString()) + 1));
        log("Wheel count changed: " + wheels.getText());
        parameters.set(constants.PITS_WHEEL_COUNT_INT, wheels.getText().toString() + ",");
    }

    public void wheelDown(View view) {
        String temp = String.valueOf(Integer.parseInt(wheels.getText().toString()) - 1);
        if (temp.equals("-1")) {
            temp = "0";
        }
        wheels.setText(String.valueOf(temp));
        log("Wheel count changed: " + wheels.getText());
        parameters.set(constants.PITS_WHEEL_COUNT_INT, wheels.getText().toString() + ",");
    }

    public void motorUp(View view) {
        motors.setText(String.valueOf(Integer.parseInt(motors.getText().toString()) + 1));
        log("Motor count changed: " + motors.getText());
        parameters.set(constants.PITS_MOTOR_COUNT_INT, motors.getText().toString()+",");
    }

    public void motorDown(View view) {
        String temp = String.valueOf(Integer.parseInt(motors.getText().toString()) - 1);
        if (temp.equals("-1")) {
            temp = "0";
        }
        motors.setText(String.valueOf(temp));
        log("Motor count changed: " + motors.getText());
        parameters.set(constants.PITS_MOTOR_COUNT_INT, motors.getText().toString() + ",");
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
                parameters.set(place, value.getText().toString()+",");
                try {
                    InputMethodManager inputMethodManager = (InputMethodManager) getActivity().getSystemService(Activity.INPUT_METHOD_SERVICE);
                    inputMethodManager.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);
                } catch (Exception e) {
                }
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
