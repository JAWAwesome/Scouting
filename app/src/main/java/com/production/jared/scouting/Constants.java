package com.production.jared.scouting;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

/**
 * Created by Jared on 9/20/2015.
 */
public class Constants {
    // Default action values
    // Setup
    public final String DEFAULT_PERSON_NAME = "Jared Wagner";
    public final String DEFAULT_TEAM_NAME = "Absolute Zero Electricity";
    public final String DEFAULT_TEAM_NUMBER = "3941";

    // Pits
    public final String DEFAULT_PITS_SALLY_PORT = "false";
    public final String DEFAULT_PITS_DRAWBRIDGE = "false";
    public final String DEFAULT_PITS_MOAT = "false";
    public final String DEFAULT_PITS_ROCK_WALL = "false";
    public final String DEFAULT_PITS_ROUGH_TERRAIN = "false";
    public final String DEFAULT_PITS_PORTCULLIS = "false";
    public final String DEFAULT_PITS_RAMPARTS = "false";
    public final String DEFAULT_PITS_CHEVAL_DE_FRISE = "false";
    public final String DEFAULT_PITS_HIDDEN_PASSAGE = "false";
    public final String DEFAULT_PITS_UPPER_LEFT_GOAL = "false";
    public final String DEFAULT_PITS_UPPER_MIDDLE_GOAL = "false";
    public final String DEFAULT_PITS_UPPER_RIGHT_GOAL = "false";
    public final String DEFAULT_PITS_BOTTOM_LEFT_GOAL = "false";
    public final String DEFAULT_PITS_NO_GOAL = "false";
    public final String DEFAULT_PITS_BOTTOM_RIGHT_GOAL = "false";
    public final String DEFAULT_PITS_ASSISTED_SHOOTER = "false";
    public final String DEFAULT_PITS_SHOOTER_TYPE = "kicker";

    public final String DEFAULT_ROBOT_DRIVE_SYSTEM_TYPE = "Tank";
    public final String DEFAULT_ROBOT_DRIVE_SYSTEM_CONNECTION = "Direct";
    public final String DEFAULT_ROBOT_DRIVE_SYSTEM_WHEEL_COUNT = "4";
    public final String DEFAULT_ROBOT_DRIVE_SYSTEM_WHEEL_TYPE = "Large Friction";
    public final String DEFAULT_ROBOT_DRIVE_SYSTEM_MOTOR_COUNT = "4";
    public final String DEFAULT_ROBOT_DRIVE_SYSTEM_SHIFTER_USE = "false";
    public final String DEFAULT_ROBOT_WEIGHT = "120";
    public final String DEFAULT_ROBOT_HEIGHT = "12";
    public final String DEFAULT_ROBOT_WIDTH = "28";
    public final String DEFAULT_ROBOT_LENGTH = "32";
    public final String DEFAULT_ROBOT_QUALITY = "High";

    // Auto
    public final String DEFAULT_AUTO_DEFENSE_REACHED = "None";
    public final String DEFAULT_AUTO_DEFENSE_REACHED_POSITION = "NONE";
    public final String DEFAULT_AUTO_DEFENSE_CROSSED = "None";
    public final String DEFAULT_AUTO_DEFENSE_CROSSED_POSITION = "None";
    public final String DEFAULT_AUTO_SHOOT_POSITION = "None";
    public final String DEFAULT_AUTO_OTHER_INFORMATION = "None";

    // Teleop
    public final String DEFAULT_TELEOP_DEFENSE_LIST = "None";
    public final String DEFAULT_TELEOP_DEFENSE_POSITION_LIST = "None";
    public final String DEFAULT_TELEOP_DEFENSE_COUNT = "None";
    public final String DEFAULT_TELEOP_SHOOT_POSITION_LIST = "None";
    public final String DEFAULT_TELEOP_SHOOT_COUNT = "None";
    public final String DEFAULT_TELEOP_CHALLENGE_POSITION = "None";
    public final String DEFAULT_TELEOP_SCALED_POSITION = "None";
    public final String DEFAULT_TELEOP_BREECHED = "None";
    public final String DEFAULT_TELEOP_CAPTURED = "None";
    public final String DEFAULT_TELEOP_OTHER_INFORMATION = "None";





    // Position lables
    // Setup position labels
    public final int SETUP_PERSON_NAME_INT = 0;
    public final int SETUP_TEAM_NAME_INT = 1;
    public final int SETUP_TEAM_NUMBER_INT = 2;

    // Pits position labels
    public final int PITS_SALLY_PORT_CLAIM_INT = 0;
    public final int PITS_DRAWBRIDGE_CLAIM_INT = 1;
    public final int PITS_MOAT_CLAIM_INT = 2;
    public final int PITS_ROCK_WALL_CLAIM_INT = 3;
    public final int PITS_ROUGH_TERRAIN_CLAIM_INT = 4;
    public final int PITS_PORTCULLIS_CLAIM_INT = 5;
    public final int PITS_RAMPARTS_CLAIM_INT = 6;
    public final int PITS_CHEVAL_DE_FRISE_CLAIM_INT = 7;
    public final int PITS_SECRET_PASSAGE_CLAIM_INT = 8;
    public final int PITS_UPPER_LEFT_CLAIM_INT = 9;
    public final int PITS_UPPER_MIDDLE_CLAIM_INT = 10;
    public final int PITS_UPPER_RIGHT_CLAIM_INT = 11;
    public final int PITS_LOWER_LEFT_CLAIM_INT = 12;
    public final int PITS_NO_GOAL_CLAIM_INT = 13;
    public final int PITS_LOWER_RIGHT_CLAIM_INT = 14;
    public final int PITS_ASSISTED_SHOOTER_INT = 15;
    public final int PITS_SHOOTER_TYPE_INT = 16;

    public final int PITS_DRIVE_TYPE_INT = 17;
    public final int PITS_DRIVE_CONNECTION_INT = 18;
    public final int PITS_WHEEL_COUNT_INT = 19;
    public final int PITS_WHEEL_TYPE_INT = 20;
    public final int PITS_MOTOR_COUNT_INT = 21;
    public final int PITS_SHIFTER_USE_INT = 22;
    public final int PITS_WEIGHT_INT = 23;
    public final int PITS_HEIGHT_INT = 24;
    public final int PITS_WIDTH_INT = 25;
    public final int PITS_LENGTH_INT = 26;
    public final int PITS_QUALITY_INT = 27;

    // Auto position labels
    public final int AUTO_DEFENSE_REACHED_INT = 0;
    public final int AUTO_DEFENSE_REACHED_POSITION_INT = 1;
    public final int AUTO_DEFENSE_CROSSED_INT = 2;
    public final int AUTO_DEFENSE_CROSSED_POSITION_INT = 3;
    public final int AUTO_SHOOT_POSITION_INT = 4;
    public final int AUTO_OTHER_INFORMATION_INT = 5;


    // Teleop position labels
    public final int TELEOP_DEFENSE_LIST_INT = 0;
    public final int TELEOP_DEFENSE_POSITION_LIST_INT = 1;
    public final int TELEOP_DEFENSE_COUNT_INT = 2;
    public final int TELEOP_SHOOT_POSITION_LIST_INT = 3;
    public final int TELEOP_SHOOT_COUNT_INT = 4;
    public final int TELEOP_CHALLENGE_POSITION_INT = 5;
    public final int TELEOP_SCALED_POSITION_INT = 6;
    public final int TELEOP_BREECHED_INT = 7;
    public final int TELEOP_CAPTURED_INT = 8;
    public final int TELEOP_OTHER_INFORMATION_INT = 9;





    // Value Lables
    // Setup
    public final String TIMESTAMP = "Time Stamp";
    public final String PERSON_NAME = "Person Name";
    public final String TEAM_NAME = "Team Name";
    public final String TEAM_NUMBER = "Team Number";

    // Pits
    public final String PITS_SALLY_PORT = "Pits Claim Sally Port";
    public final String PITS_DRAWBRIDGE = "Pits Claim Drawbridge";
    public final String PITS_MOAT = "Pits Claim Moat";
    public final String PITS_ROCK_WALL = "Pits Claim Rock Wall";
    public final String PITS_ROUGH_TERRAIN = "Pits Claim Rough Terrain";
    public final String PITS_PORTCULLIS = "Pits Claim Portcullis";
    public final String PITS_RAMPARTS = "Pits Claim Ramparts";
    public final String PITS_CHEVAL_DE_FRISE = "Pits Claim Cheval de Frise";
    public final String PITS_HIDDEN_PASSAGE = "Pits Claim Hidden Passage";
    public final String PITS_UPPER_LEFT_GOAL = "Pits Claim Upper Left Goal";
    public final String PITS_UPPER_MIDDLE_GOAL = "Pits Claim Upper Middle Goal";
    public final String PITS_UPPER_RIGHT_GOAL = "Pits Claim Upper Right Goal";
    public final String PITS_BOTTOM_LEFT_GOAL = "Pits Claim Lower Left Goal";
    public final String PITS_NO_GOAL = "Pits Claim No Goal";
    public final String PITS_BOTTOM_RIGHT_GOAL = "Pits Claim Lower Right Goal";
    public final String PITS_ASSISTED_SHOOTER = "Assisted Shooter";
    public final String PITS_SHOOTER_TYPE = "Shooter Type";

    public final String ROBOT_DRIVE_SYSTEM_TYPE = "Drive Type";
    public final String ROBOT_DRIVE_SYSTEM_CONNECTION = "Drive Connection Type";
    public final String ROBOT_DRIVE_SYSTEM_WHEEL_COUNT = "Drive Wheel Count";
    public final String ROBOT_DRIVE_SYSTEM_WHEEL_TYPE = "Drive Wheel Type";
    public final String ROBOT_DRIVE_SYSTEM_MOTOR_COUNT = "Drive Motor Count";
    public final String ROBOT_DRIVE_SYSTEM_SHIFTER_USE = "Uses Drive Shifters";
    public final String ROBOT_WEIGHT = "Weight";
    public final String ROBOT_HEIGTH = "Height";
    public final String ROBOT_WIDTH = "Width";
    public final String ROBOT_LENGTH = "Length";
    public final String ROBOT_QUALITY = "Build Quality";

    // Auto
    public final String AUTO_DEFENSE_REACHED = "Auto Defense Reached";
    public final String AUTO_DEFENSE_REACHED_POSITION = "Auto Defense Reached Position";
    public final String AUTO_DEFENSE_CROSSED = "Auto Defense Crossed";
    public final String AUTO_DEFENSE_CROSSED_POSITION = "Auto Defense Crossed Position";
    public final String AUTO_SHOOT_POSITION = "Auto Goal Shot Position";
    public final String AUTO_OTHER_INFORMATION = "Optional Text Description";

    // Teleop
    public final String TELEOP_DEFENSE_LIST = "Teleop Defense Crossed";
    public final String TELEOP_DEFENSE_POSITION_LIST = "Teleop Defense Crossed Position";
    public final String TELEOP_DEFENSE_COUNT = "Teleop Defense Crossed Count";
    public final String TELEOP_SHOOT_POSITION_LIST = "Teleop Goal Shot Position";
    public final String TELEOP_SHOOT_COUNT = "Teleop Goal Shot Count";
    public final String TELEOP_CHALLENGE_POSITION = "Teleop Challenge Position";
    public final String TELEOP_SCALED_POSITION = "Teleop Scaled Position";
    public final String TELEOP_BREECHED = "Teleop Breeched Status";
    public final String TELEOP_CAPTURED = "Teleop Captured Status";
    public final String TELEOP_OTHER_INFORMATION = "Optional Text Description";





    // Drawlables
    public final int[] DRIVE_TYPE_DRAWABLES = {R.drawable.tankdrive,R.drawable.omnidrive,R.drawable.mecanumdrive,R.drawable.swervedrive,R.drawable.holonomicdrive,R.drawable.butterflydrive,R.drawable.other};
    public final int[] WHEEL_TYPE_DRAWABLES = {R.drawable.mecanumwheel,R.drawable.omniwheel,R.drawable.tanktread,R.drawable.frictionwheel,R.drawable.frictionwheel,R.drawable.pneumaticwheel,R.drawable.other};
    public final int[] DRIVE_CONNECTION_TYPE_DRAWABLES = {R.drawable.beltconnection,R.drawable.chainconnection,R.drawable.shaftconnection,R.drawable.gearconnection,R.drawable.directconnection,R.drawable.other};
    public final int[] SHOOTER_TYPE_DRAWABLES = {R.drawable.pitchershooter,R.drawable.pneumaticshooter,R.drawable.surgicaltubingshooter,R.drawable.springshooter,R.drawable.kickershooter,R.drawable.miss,R.drawable.other};
    public final int[] PICKUP_TYPE_DRAWABLES = new int[4];





    // Picture codes
    public final int REQUEST_IMAGE_CAPTURE = 0;
}
