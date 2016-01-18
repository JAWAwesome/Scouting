package com.production.jared.scouting;

import android.app.Activity;
import android.app.Dialog;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Jared on 9/18/2015.
 */
public class AutoMain extends Fragment implements ChangeText{
    static Handler sender;
    Constants constants = new Constants();
    final String TAG = this.getClass().toString();
    public View auto;
    ArrayList<String> parameters = new ArrayList<>();
    ImageView reachedDefenseI;
    Button reachedDefenseB;
    ImageView crossedDefenseI;
    Button crossedDefenseB;
    ImageView shotGoalI;
    Button shotGoalB;
    EditText details;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        log("View being created");
        // Create the fragment
        View v = inflater.inflate(R.layout.auto_main, container, false);
        auto = v;
        parameters.add(constants.DEFAULT_AUTO_ACTION_1 + ",");
        parameters.add(constants.DEFAULT_AUTO_ACTION_2+",");
        parameters.add(constants.DEFAULT_AUTO_ACTION_3 + ",");
        parameters.add(constants.DEFAULT_AUTO_ACTION_4+",");
        parameters.add(constants.DEFAULT_AUTO_ACTION_5 + ",");
        return v;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        log("View created");
        reachedDefenseI = (ImageView) getActivity().findViewById(R.id.reach_Defense_Auto_Image);
        reachedDefenseI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reachedDefense(v);
            }
        });
        reachedDefenseB = (Button) getActivity().findViewById(R.id.reach_Defense_Auto_Button);
        reachedDefenseB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reachedDefense(v);
            }
        });

        crossedDefenseI = (ImageView) getActivity().findViewById(R.id.cross_Defense_Auto_Image);
        crossedDefenseI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                crossedDefense(v);
            }
        });
        crossedDefenseB = (Button) getActivity().findViewById(R.id.cross_Defense_Auto_Button);
        crossedDefenseB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                crossedDefense(v);
            }
        });

        shotGoalI = (ImageView) getActivity().findViewById(R.id.shoot_Goal_Auto_Image);
        shotGoalI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shotGoal(v);
            }
        });
        shotGoalB = (Button) getActivity().findViewById(R.id.shoot_Goal_Auto_Button);
        shotGoalB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shotGoal(v);
            }
        });

        details = (EditText) getActivity().findViewById(R.id.auto_Detailed_Text);
        details.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        try {
            InputMethodManager inputMethodManager = (InputMethodManager) getActivity().getSystemService(Activity.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);
        } catch (Exception e) {
        }
    }

    public void reachedDefense(View v) {
        // Ask what to do
        final Dialog dialog = new Dialog(getActivity());
        // use the layout file created
        dialog.setContentView(R.layout.defense_chooser);
        dialog.setTitle("Defense Reached");
        dialog.show();
        final CheckBox sallyPortC;
        final ImageView sallyPortI;
        final CheckBox drawbridgeC;
        final ImageView drawbridgeI;
        final CheckBox moatC;
        final ImageView moatI;
        final CheckBox rockWallC;
        final ImageView rockWallI;
        final CheckBox roughTerrainC;
        final ImageView roughTerrainI;
        final CheckBox portcullisC;
        final ImageView portcullisI;
        final CheckBox rampartsC;
        final ImageView rampartsI;
        final CheckBox chevalDeFriseC;
        final ImageView chevalDeFriseI;
        final CheckBox secretPassageC;
        final ImageView secretPassageI;

        sallyPortC = (CheckBox) dialog.findViewById(R.id.sally_Port_CheckBox);
        sallyPortC.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                parameters.set(0, isChecked + ",");
            }
        });
        sallyPortI = (ImageView) dialog.findViewById(R.id.sally_Port_Image);
        sallyPortI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                log("Sally port clicked");
                sallyPortC.setChecked(!sallyPortC.isChecked());
            }
        });

        drawbridgeC = (CheckBox) dialog.findViewById(R.id.drawbridge_CheckBox);
        drawbridgeC.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                parameters.set(0, isChecked + ",");
            }
        });
        drawbridgeI = (ImageView) dialog.findViewById(R.id.drawbridge_Image);
        drawbridgeI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                log("Drawbridge clicked");
                drawbridgeC.setChecked(!drawbridgeC.isChecked());
            }
        });

        moatC = (CheckBox) dialog.findViewById(R.id.moat_CheckBox);
        moatC.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                parameters.set(0, isChecked + ",");
            }
        });
        moatI = (ImageView) dialog.findViewById(R.id.moat_Image);
        moatI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                log("Moat clicked");
                moatC.setChecked(!moatC.isChecked());
            }
        });

        rockWallC = (CheckBox) dialog.findViewById(R.id.rock_Wall_CheckBox);
        rockWallC.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                parameters.set(0, isChecked + ",");
            }
        });
        rockWallI = (ImageView) dialog.findViewById(R.id.rock_Wall_Image);
        rockWallI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                log("Rock wall clicked");
                rockWallC.setChecked(!rockWallC.isChecked());
            }
        });

        roughTerrainC = (CheckBox) dialog.findViewById(R.id.rough_Terrain_CheckBox);
        roughTerrainC.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                parameters.set(0, isChecked + ",");
            }
        });
        roughTerrainI = (ImageView) dialog.findViewById(R.id.rough_Terrain_Image);
        roughTerrainI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                log("Rough terrain clicked");
                roughTerrainC.setChecked(!roughTerrainC.isChecked());
            }
        });

        portcullisC = (CheckBox) dialog.findViewById(R.id.portcullis_CheckBox);
        portcullisC.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                parameters.set(0, isChecked + ",");
            }
        });
        portcullisI = (ImageView) dialog.findViewById(R.id.portcullis_Image);
        portcullisI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                log("Portcullis clicked");
                portcullisC.setChecked(!portcullisC.isChecked());
            }
        });

        rampartsC = (CheckBox) dialog.findViewById(R.id.ramparts_CheckBox);
        rampartsC.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                parameters.set(0, isChecked + ",");
            }
        });
        rampartsI = (ImageView) dialog.findViewById(R.id.ramparts_Image);
        rampartsI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                log("ramparts clicked");
                rampartsC.setChecked(!rampartsC.isChecked());
            }
        });

        chevalDeFriseC = (CheckBox) dialog.findViewById(R.id.cheval_De_Frise_CheckBox);
        chevalDeFriseC.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                parameters.set(0, isChecked + ",");
            }
        });
        chevalDeFriseI = (ImageView) dialog.findViewById(R.id.cheval_De_Frise_Image);
        chevalDeFriseI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                log("Cheval de Frise clicked");
                chevalDeFriseC.setChecked(!chevalDeFriseC.isChecked());
            }
        });

        secretPassageC = (CheckBox) dialog.findViewById(R.id.hidden_Passage_CheckBox);
        secretPassageC.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                parameters.set(0, isChecked + ",");
            }
        });
        secretPassageI = (ImageView) dialog.findViewById(R.id.hidden_Passage_Image);
        secretPassageI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                log("Secred passage clicked");
                secretPassageC.setChecked(!secretPassageC.isChecked());
            }
        });

        Button end = (Button) dialog.findViewById(R.id.defense_Button);
        end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                // Ask what to do
                final Dialog dialog = new Dialog(getActivity());
                // use the layout file created
                dialog.setContentView(R.layout.position_chooser);
                dialog.setTitle("Defense Position");
                dialog.show();
                final Button one = (Button) dialog.findViewById(R.id.defense_Position_1);
                final Button two = (Button) dialog.findViewById(R.id.defense_Position_2);
                final Button three = (Button) dialog.findViewById(R.id.defense_Position_3);
                final Button four = (Button) dialog.findViewById(R.id.defense_Position_4);
                final Button five = (Button) dialog.findViewById(R.id.defense_Position_5);
                one.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
                two.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
                three.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
                four.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
                five.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
                Button end = (Button) dialog.findViewById(R.id.defense_Placement_Button);
                end.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
            }
        });
    }

    public void crossedDefense(View v) {
        // Ask what to do
        final Dialog dialog = new Dialog(getActivity());
        // use the layout file created
        dialog.setContentView(R.layout.defense_chooser);
        dialog.setTitle("Crossed Defense");
        dialog.show();
        final CheckBox sallyPortC;
        final ImageView sallyPortI;
        final CheckBox drawbridgeC;
        final ImageView drawbridgeI;
        final CheckBox moatC;
        final ImageView moatI;
        final CheckBox rockWallC;
        final ImageView rockWallI;
        final CheckBox roughTerrainC;
        final ImageView roughTerrainI;
        final CheckBox portcullisC;
        final ImageView portcullisI;
        final CheckBox rampartsC;
        final ImageView rampartsI;
        final CheckBox chevalDeFriseC;
        final ImageView chevalDeFriseI;
        final CheckBox secretPassageC;
        final ImageView secretPassageI;

        sallyPortC = (CheckBox) dialog.findViewById(R.id.sally_Port_CheckBox);
        sallyPortC.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                parameters.set(0, isChecked + ",");
            }
        });
        sallyPortI = (ImageView) dialog.findViewById(R.id.sally_Port_Image);
        sallyPortI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                log("Sally port clicked");
                sallyPortC.setChecked(!sallyPortC.isChecked());
            }
        });

        drawbridgeC = (CheckBox) dialog.findViewById(R.id.drawbridge_CheckBox);
        drawbridgeC.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                parameters.set(0, isChecked + ",");
            }
        });
        drawbridgeI = (ImageView) dialog.findViewById(R.id.drawbridge_Image);
        drawbridgeI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                log("Drawbridge clicked");
                drawbridgeC.setChecked(!drawbridgeC.isChecked());
            }
        });

        moatC = (CheckBox) dialog.findViewById(R.id.moat_CheckBox);
        moatC.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                parameters.set(0, isChecked + ",");
            }
        });
        moatI = (ImageView) dialog.findViewById(R.id.moat_Image);
        moatI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                log("Moat clicked");
                moatC.setChecked(!moatC.isChecked());
            }
        });

        rockWallC = (CheckBox) dialog.findViewById(R.id.rock_Wall_CheckBox);
        rockWallC.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                parameters.set(0, isChecked + ",");
            }
        });
        rockWallI = (ImageView) dialog.findViewById(R.id.rock_Wall_Image);
        rockWallI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                log("Rock wall clicked");
                rockWallC.setChecked(!rockWallC.isChecked());
            }
        });

        roughTerrainC = (CheckBox) dialog.findViewById(R.id.rough_Terrain_CheckBox);
        roughTerrainC.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                parameters.set(0, isChecked + ",");
            }
        });
        roughTerrainI = (ImageView) dialog.findViewById(R.id.rough_Terrain_Image);
        roughTerrainI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                log("Rough terrain clicked");
                roughTerrainC.setChecked(!roughTerrainC.isChecked());
            }
        });

        portcullisC = (CheckBox) dialog.findViewById(R.id.portcullis_CheckBox);
        portcullisC.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                parameters.set(0, isChecked + ",");
            }
        });
        portcullisI = (ImageView) dialog.findViewById(R.id.portcullis_Image);
        portcullisI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                log("Portcullis clicked");
                portcullisC.setChecked(!portcullisC.isChecked());
            }
        });

        rampartsC = (CheckBox) dialog.findViewById(R.id.ramparts_CheckBox);
        rampartsC.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                parameters.set(0, isChecked + ",");
            }
        });
        rampartsI = (ImageView) dialog.findViewById(R.id.ramparts_Image);
        rampartsI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                log("ramparts clicked");
                rampartsC.setChecked(!rampartsC.isChecked());
            }
        });

        chevalDeFriseC = (CheckBox) dialog.findViewById(R.id.cheval_De_Frise_CheckBox);
        chevalDeFriseC.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                parameters.set(0, isChecked + ",");
            }
        });
        chevalDeFriseI = (ImageView) dialog.findViewById(R.id.cheval_De_Frise_Image);
        chevalDeFriseI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                log("Cheval de Frise clicked");
                chevalDeFriseC.setChecked(!chevalDeFriseC.isChecked());
            }
        });

        secretPassageC = (CheckBox) dialog.findViewById(R.id.hidden_Passage_CheckBox);
        secretPassageC.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                parameters.set(0, isChecked + ",");
            }
        });
        secretPassageI = (ImageView) dialog.findViewById(R.id.hidden_Passage_Image);
        secretPassageI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                log("Secred passage clicked");
                secretPassageC.setChecked(!secretPassageC.isChecked());
            }
        });

        Button end = (Button) dialog.findViewById(R.id.defense_Button);
        end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                // Ask what to do
                final Dialog dialog = new Dialog(getActivity());
                // use the layout file created
                dialog.setContentView(R.layout.position_chooser);
                dialog.setTitle("Defense Position");
                dialog.show();
                final Button one = (Button) dialog.findViewById(R.id.defense_Position_1);
                final Button two = (Button) dialog.findViewById(R.id.defense_Position_2);
                final Button three = (Button) dialog.findViewById(R.id.defense_Position_3);
                final Button four = (Button) dialog.findViewById(R.id.defense_Position_4);
                final Button five = (Button) dialog.findViewById(R.id.defense_Position_5);
                one.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
                two.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
                three.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
                four.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
                five.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });

                Button end = (Button) dialog.findViewById(R.id.defense_Placement_Button);
                end.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
            }
        });
    }

    public void shotGoal(View v) {
        // Ask what to do
        final Dialog dialog = new Dialog(getActivity());
        // use the layout file created
        dialog.setContentView(R.layout.goal_chooser);
        dialog.setTitle("Shoot Goal");
        dialog.show();
        final CheckBox upperLeftPitsClaimC;
        final ImageView upperLeftPitsClaimI;
        final CheckBox upperMiddlePitsClaimC;
        final ImageView upperMiddlePitsClaimI;
        final CheckBox upperRightPitsClaimC;
        final ImageView upperRightPitsClaimI;
        final CheckBox lowerLeftPitsClaimC;
        final ImageView lowerLeftPitsClaimI;
        final CheckBox noGoalPitsClaimC;
        final ImageView noGoalPitsClaimI;
        final CheckBox lowerRightPitsClaimC;
        final ImageView lowerRightPitsClaimI;
        upperLeftPitsClaimC = (CheckBox) dialog.findViewById(R.id.upper_Goal_Left_Check_General);
        upperLeftPitsClaimC.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                parameters.set(0, isChecked + ",");
            }
        });
        upperLeftPitsClaimI = (ImageView) dialog.findViewById(R.id.upper_Goal_Left_Image_General);
        upperLeftPitsClaimI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                log("Upper Left Goal clicked");
                upperLeftPitsClaimC.setChecked(!upperLeftPitsClaimC.isChecked());
            }
        });

        upperMiddlePitsClaimC = (CheckBox) dialog.findViewById(R.id.upper_Goal_Middle_Check_General);
        upperMiddlePitsClaimC.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                parameters.set(0, isChecked + ",");
            }
        });
        upperMiddlePitsClaimI = (ImageView) dialog.findViewById(R.id.upper_Goal_Middle_Image_General);
        upperMiddlePitsClaimI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                log("Upper Middle Goal clicked");
                upperMiddlePitsClaimC.setChecked(!upperMiddlePitsClaimC.isChecked());
            }
        });

        upperRightPitsClaimC = (CheckBox) dialog.findViewById(R.id.upper_Goal_Right_Check_General);
        upperRightPitsClaimC.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                parameters.set(0, isChecked + ",");
            }
        });
        upperRightPitsClaimI = (ImageView) dialog.findViewById(R.id.upper_Goal_Right_Image_General);
        upperRightPitsClaimI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                log("Upper Right Goal clicked");
                upperRightPitsClaimC.setChecked(!upperRightPitsClaimC.isChecked());
            }
        });

        lowerLeftPitsClaimC = (CheckBox) dialog.findViewById(R.id.lower_Goal_Left_Check_General);
        lowerLeftPitsClaimC.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                parameters.set(0, isChecked + ",");
            }
        });
        lowerLeftPitsClaimI = (ImageView) dialog.findViewById(R.id.lower_Left_Goal_Image_General);
        lowerLeftPitsClaimI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                log("Lower Left Goal clicked");
                lowerLeftPitsClaimC.setChecked(!lowerLeftPitsClaimC.isChecked());
            }
        });

        noGoalPitsClaimC = (CheckBox) dialog.findViewById(R.id.no_Goal_General);
        noGoalPitsClaimC.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                parameters.set(0, isChecked + ",");
            }
        });
        noGoalPitsClaimI = (ImageView) dialog.findViewById(R.id.no_Goal_Image_General);
        noGoalPitsClaimI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                log("No Goal clicked");
                noGoalPitsClaimC.setChecked(!noGoalPitsClaimC.isChecked());
            }
        });

        lowerRightPitsClaimC = (CheckBox) dialog.findViewById(R.id.lower_Goal_Right_Check_General);
        lowerRightPitsClaimC.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                parameters.set(0, isChecked + ",");
            }
        });
        lowerRightPitsClaimI = (ImageView) dialog.findViewById(R.id.lower_Right_Goal_Image_General);
        lowerRightPitsClaimI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                log("Lower Right Goal clicked");
                lowerRightPitsClaimC.setChecked(!lowerRightPitsClaimC.isChecked());
            }
        });

        Button end = (Button) dialog.findViewById(R.id.goal_Button);
        end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

    public static Fragment newInstance(Handler handler) {
        // Used for the fragment
        sender = handler;
        AutoMain f = new AutoMain();
        Bundle b = new Bundle();
        f.setArguments(b);
        return f;
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
                parameters.set(place, value.getText().toString() + ",");
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
