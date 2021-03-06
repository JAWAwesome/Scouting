package com.production.jared.scouting;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;

import java.util.ArrayList;

/**
 * Created by Jared on 9/18/2015.
 */
public class TeleopMain extends Fragment implements ChangeText{
    static Handler sender;
    Constants constants = new Constants();
    final String TAG = this.getClass().toString();
    public View teleop;
    ArrayList<String> parameters = new ArrayList<>();
    ImageView crossDefenseI;
    Button crossDefenseB;
    ImageView shotGoalI;
    Button shotGoalB;
    ImageView challengeTowerI;
    Button challengeTowerB;
    ImageView scaledTowerI;
    Button scaledTowerB;
    ImageView breechedTowerI;
    Button breechedTowerB;
    ImageView capturedTowerI;
    Button capturedTowerB;
    EditText details;
    boolean breached = false;
    boolean capture = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        log("View being created");
        // Create the Fragment
        View v = inflater.inflate(R.layout.teleop_main, container, false);
        teleop = v;
        parameters.add(constants.DEFAULT_TELEOP_DEFENSE_LIST+",");
        parameters.add(constants.DEFAULT_TELEOP_DEFENSE_POSITION_LIST+",");
        parameters.add(constants.DEFAULT_TELEOP_DEFENSE_COUNT+",");
        parameters.add(constants.DEFAULT_TELEOP_SHOOT_POSITION_LIST+",");
        parameters.add(constants.DEFAULT_TELEOP_SHOOT_COUNT+",");
        parameters.add(constants.DEFAULT_TELEOP_CHALLENGE_POSITION+",");
        parameters.add(constants.DEFAULT_TELEOP_SCALED_POSITION+",");
        parameters.add(constants.DEFAULT_TELEOP_BREECHED+",");
        parameters.add(constants.DEFAULT_TELEOP_CAPTURED+",");
        parameters.add(constants.DEFAULT_TELEOP_OTHER_INFORMATION+",");
        return v;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        log("View created");
        crossDefenseI = (ImageView) getActivity().findViewById(R.id.cross_Defense_Teleop_Image);
        crossDefenseI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                crossedDefense(v);
            }
        });
        crossDefenseB = (Button) getActivity().findViewById(R.id.cross_Defense_Teleop_Button);
        crossDefenseB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                crossedDefense(v);
            }
        });

        shotGoalI = (ImageView) getActivity().findViewById(R.id.shoot_Goal_Teleop_Image);
        shotGoalI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shotGoal(v);
            }
        });
        shotGoalB = (Button) getActivity().findViewById(R.id.shoot_Goal_Teleop_Button);
        shotGoalB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shotGoal(v);
            }
        });

        challengeTowerI = (ImageView) getActivity().findViewById(R.id.challenge_Tower_Teleop_Image);
        challengeTowerI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                challengedTower(v);
            }
        });
        challengeTowerB = (Button) getActivity().findViewById(R.id.challenge_Tower_Teleop_Button);
        challengeTowerB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                challengedTower(v);
            }
        });

        scaledTowerI = (ImageView) getActivity().findViewById(R.id.scale_Tower_Teleop_Image);
        scaledTowerI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scaledTower(v);
            }
        });
        scaledTowerB = (Button) getActivity().findViewById(R.id.scale_Tower_Teleop_Buttom);
        scaledTowerB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scaledTower(v);
            }
        });

        breechedTowerI = (ImageView) getActivity().findViewById(R.id.breech_Teleop_Image);
        breechedTowerI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                breechedDefenses(v);
            }
        });
        breechedTowerB = (Button) getActivity().findViewById(R.id.breech_Teleop_Button);
        breechedTowerB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                breechedDefenses(v);
            }
        });

        capturedTowerI = (ImageView) getActivity().findViewById(R.id.capture_Teleop_Image);
        capturedTowerI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                capturedTower(v);
            }
        });
        capturedTowerB = (Button) getActivity().findViewById(R.id.capture_Teleop_Button);
        capturedTowerB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                capturedTower(v);
            }
        });

        details = (EditText) getActivity().findViewById(R.id.teleop_Detailed_Text);
        details.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                parameters.set(constants.TELEOP_OTHER_INFORMATION_INT,s.toString());
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

    public void crossedDefense(View v) {
        // Ask what to do
        final Dialog dialog = new Dialog(getActivity());
        // use the layout file created
        dialog.setContentView(R.layout.defense_chooser);
        dialog.setTitle("Defense Crossed");
        dialog.show();
        final RadioButton sallyPortR;
        final ImageView sallyPortI;
        final RadioButton drawbridgeR;
        final ImageView drawbridgeI;
        final RadioButton moatR;
        final ImageView moatI;
        final RadioButton rockWallR;
        final ImageView rockWallI;
        final RadioButton roughTerrainR;
        final ImageView roughTerrainI;
        final RadioButton portcullisR;
        final ImageView portcullisI;
        final RadioButton rampartsR;
        final ImageView rampartsI;
        final RadioButton chevalDeFriseR;
        final ImageView chevalDeFriseI;
        final RadioButton secretPassageR;
        final ImageView secretPassageI;

        sallyPortR = (RadioButton) dialog.findViewById(R.id.sally_Port_RadioButton);
        sallyPortR.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    parameters.set(constants.TELEOP_DEFENSE_LIST_INT, "Sally Port,");
                }
            }
        });
        sallyPortI = (ImageView) dialog.findViewById(R.id.sally_Port_Image);
        sallyPortI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                log("Sally port clicked");
                sallyPortR.setChecked(!sallyPortR.isChecked());
            }
        });

        drawbridgeR = (RadioButton) dialog.findViewById(R.id.drawbridge_RadioButton);
        drawbridgeR.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    parameters.set(constants.TELEOP_DEFENSE_LIST_INT, "Drawbridge,");
                }
            }
        });
        drawbridgeI = (ImageView) dialog.findViewById(R.id.drawbridge_Image);
        drawbridgeI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                log("Drawbridge clicked");
                drawbridgeR.setChecked(!drawbridgeR.isChecked());
            }
        });

        moatR = (RadioButton) dialog.findViewById(R.id.moat_RadioButton);
        moatR.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    parameters.set(constants.TELEOP_DEFENSE_LIST_INT, "Moat,");
                }
            }
        });
        moatI = (ImageView) dialog.findViewById(R.id.moat_Image);
        moatI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                log("Moat clicked");
                moatR.setChecked(!moatR.isChecked());
            }
        });

        rockWallR = (RadioButton) dialog.findViewById(R.id.rock_Wall_RadioButton);
        rockWallR.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    parameters.set(constants.TELEOP_DEFENSE_LIST_INT, "Rock Wall,");
                }
            }
        });
        rockWallI = (ImageView) dialog.findViewById(R.id.rock_Wall_Image);
        rockWallI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                log("Rock wall clicked");
                rockWallR.setChecked(!rockWallR.isChecked());
            }
        });

        roughTerrainR = (RadioButton) dialog.findViewById(R.id.rough_Terrain_RadioButton);
        roughTerrainR.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    parameters.set(constants.TELEOP_DEFENSE_LIST_INT, "Rough Terrain,");
                }
            }
        });
        roughTerrainI = (ImageView) dialog.findViewById(R.id.rough_Terrain_Image);
        roughTerrainI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                log("Rough terrain clicked");
                roughTerrainR.setChecked(!roughTerrainR.isChecked());
            }
        });

        portcullisR = (RadioButton) dialog.findViewById(R.id.portcullis_RadioButton);
        portcullisR.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    parameters.set(constants.TELEOP_DEFENSE_LIST_INT, "Portcullis,");
                }
            }
        });
        portcullisI = (ImageView) dialog.findViewById(R.id.portcullis_Image);
        portcullisI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                log("Portcullis clicked");
                portcullisR.setChecked(!portcullisR.isChecked());
            }
        });

        rampartsR = (RadioButton) dialog.findViewById(R.id.ramparts_RadioButton);
        rampartsR.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    parameters.set(constants.TELEOP_DEFENSE_LIST_INT, "Ramparts,");
                }
            }
        });
        rampartsI = (ImageView) dialog.findViewById(R.id.ramparts_Image);
        rampartsI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                log("ramparts clicked");
                rampartsR.setChecked(!rampartsR.isChecked());
            }
        });

        chevalDeFriseR = (RadioButton) dialog.findViewById(R.id.cheval_De_Frise_RadioButton);
        chevalDeFriseR.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    parameters.set(constants.TELEOP_DEFENSE_LIST_INT, "Cheval de Frise,");
                }
            }
        });
        chevalDeFriseI = (ImageView) dialog.findViewById(R.id.cheval_De_Frise_Image);
        chevalDeFriseI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                log("Cheval de Frise clicked");
                chevalDeFriseR.setChecked(!chevalDeFriseR.isChecked());
            }
        });

        secretPassageR = (RadioButton) dialog.findViewById(R.id.hidden_Passage_RadioButton);
        secretPassageR.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    parameters.set(constants.TELEOP_DEFENSE_LIST_INT, "Hidden Passage,");
                }
            }
        });
        secretPassageI = (ImageView) dialog.findViewById(R.id.hidden_Passage_Image);
        secretPassageI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                log("Secred passage clicked");
                secretPassageR.setChecked(!secretPassageR.isChecked());
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
                final RadioButton one = (RadioButton) dialog.findViewById(R.id.defense_Position_1);
                final RadioButton two = (RadioButton) dialog.findViewById(R.id.defense_Position_2);
                final RadioButton three = (RadioButton) dialog.findViewById(R.id.defense_Position_3);
                final RadioButton four = (RadioButton) dialog.findViewById(R.id.defense_Position_4);
                final RadioButton five = (RadioButton) dialog.findViewById(R.id.defense_Position_5);
                one.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (isChecked) {
                            parameters.set(constants.TELEOP_DEFENSE_POSITION_LIST_INT, "One,");
                        }
                    }
                });
                two.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (isChecked) {
                            parameters.set(constants.TELEOP_DEFENSE_POSITION_LIST_INT, "Two,");
                        }
                    }
                });
                three.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (isChecked) {
                            parameters.set(constants.TELEOP_DEFENSE_POSITION_LIST_INT, "Three,");
                        }
                    }
                });
                four.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (isChecked) {
                            parameters.set(constants.TELEOP_DEFENSE_POSITION_LIST_INT, "Four,");
                        }
                    }
                });
                five.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (isChecked) {
                            parameters.set(constants.TELEOP_DEFENSE_POSITION_LIST_INT, "Five,");
                        }
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
        final RadioButton upperLeftPitsClaimC;
        final ImageView upperLeftPitsClaimI;
        final RadioButton upperMiddlePitsClaimC;
        final ImageView upperMiddlePitsClaimI;
        final RadioButton upperRightPitsClaimC;
        final ImageView upperRightPitsClaimI;
        final RadioButton lowerLeftPitsClaimC;
        final ImageView lowerLeftPitsClaimI;
        final RadioButton noGoalPitsClaimC;
        final ImageView noGoalPitsClaimI;
        final RadioButton lowerRightPitsClaimC;
        final ImageView lowerRightPitsClaimI;
        upperLeftPitsClaimC = (RadioButton) dialog.findViewById(R.id.upper_Goal_Left_RadioButton_General);
        upperLeftPitsClaimC.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                parameters.set(constants.TELEOP_SHOOT_POSITION_LIST_INT, "Upper Left,");
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

        upperMiddlePitsClaimC = (RadioButton) dialog.findViewById(R.id.upper_Goal_Middle_RadioButton_General);
        upperMiddlePitsClaimC.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                parameters.set(constants.TELEOP_SHOOT_POSITION_LIST_INT, "Upper Middle,");
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

        upperRightPitsClaimC = (RadioButton) dialog.findViewById(R.id.upper_Goal_Right_RadioButton_General);
        upperRightPitsClaimC.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                parameters.set(constants.TELEOP_SHOOT_POSITION_LIST_INT, "Upper Right,");
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

        lowerLeftPitsClaimC = (RadioButton) dialog.findViewById(R.id.lower_Goal_Left_RadioButton_General);
        lowerLeftPitsClaimC.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                parameters.set(constants.TELEOP_SHOOT_POSITION_LIST_INT, "Lower Left,");
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

        noGoalPitsClaimC = (RadioButton) dialog.findViewById(R.id.no_Goal_General);
        noGoalPitsClaimC.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                parameters.set(constants.TELEOP_SHOOT_POSITION_LIST_INT, "No Goal,");
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

        lowerRightPitsClaimC = (RadioButton) dialog.findViewById(R.id.lower_Goal_Right_RadioButton_General);
        lowerRightPitsClaimC.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                parameters.set(constants.TELEOP_SHOOT_POSITION_LIST_INT, "Lower Right,");
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

    public void challengedTower(View v) {
        // Ask what to do
        final Dialog dialog = new Dialog(getActivity());
        // use the layout file created
        dialog.setContentView(R.layout.tower_chooser);
        dialog.setTitle("Challenged Tower");
        dialog.show();
        final RadioButton position_A = (RadioButton) dialog.findViewById(R.id.tower_Position_A);
        final RadioButton position_B = (RadioButton) dialog.findViewById(R.id.tower_Position_B);
        final RadioButton position_C = (RadioButton) dialog.findViewById(R.id.tower_Position_C);
        position_A.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                parameters.set(constants.TELEOP_CHALLENGE_POSITION_INT,"A,");
            }
        });
        position_B.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                parameters.set(constants.TELEOP_CHALLENGE_POSITION_INT,"B,");
            }
        });
        position_C.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                parameters.set(constants.TELEOP_CHALLENGE_POSITION_INT,"C,");
            }
        });
        Button end = (Button) dialog.findViewById(R.id.tower_Button);
        end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

    public void scaledTower(View v) {
        // Ask what to do
        final Dialog dialog = new Dialog(getActivity());
        // use the layout file created
        dialog.setContentView(R.layout.tower_chooser);
        dialog.setTitle("Scaled Tower");
        dialog.show();
        final RadioButton position_A = (RadioButton) dialog.findViewById(R.id.tower_Position_A);
        final RadioButton position_B = (RadioButton) dialog.findViewById(R.id.tower_Position_B);
        final RadioButton position_C = (RadioButton) dialog.findViewById(R.id.tower_Position_C);
        position_A.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    parameters.set(constants.TELEOP_SCALED_POSITION_INT,"A,");
                }
            }
        });
        position_B.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                parameters.set(constants.TELEOP_SCALED_POSITION_INT,"B,");
            }
        });
        position_C.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                parameters.set(constants.TELEOP_SCALED_POSITION_INT,"C,");
            }
        });
        Button end = (Button) dialog.findViewById(R.id.tower_Button);
        end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

    public void breechedDefenses(View v) {
        breached = !breached;
        parameters.set(constants.TELEOP_BREECHED_INT,Boolean.toString(breached)+",");
        if(breached) {
            breechedTowerB.setBackgroundColor(Color.parseColor("#99ff00"));
        } else {
            breechedTowerB.setBackgroundColor(Color.parseColor("lightgrey"));
        }
    }

    public void capturedTower(View v) {
        capture = !capture;
        parameters.set(constants.TELEOP_CAPTURED_INT,Boolean.toString(capture)+",");
        if(capture) {
            capturedTowerB.setBackgroundColor(Color.parseColor("#99ff00"));
        } else {
            capturedTowerB.setBackgroundColor(Color.parseColor("lightgrey"));
        }
    }

    public static Fragment newInstance(Handler handler) {
        // Used for the fragment
        sender = handler;
        TeleopMain f = new TeleopMain();
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
        Log.i(TAG,message);
    }

    @Override
    public ArrayList<String> get() {
        return parameters;
    }
}
