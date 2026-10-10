package org.firstinspires.ftc.teamcode.EK10582.subsystem;

import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.EK10582.EKLinear;
import org.firstinspires.ftc.teamcode.EK10582.auton.action.Action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Robot {

    static Robot robot = null;

    HardwareMap hardwareMap;
    EKLinear linearOpMode;

    public DcMotorEx leftFront, leftBack, rightFront, rightBack, intakeMotor, launchMotor;

    public Servo flowerServo;
//
//    public CRServo;

//    public Limelight3A limelight3A;

    public IMU imu;



    //Declare subsystems here: Ex. mecanumDrive, collection, slides, sorting, etc.
//    public AprilTags aprilTags = new AprilTags();
    public MecanumDrive mecanumDrive = new MecanumDrive();
    public Intake intake = new Intake();

    public FlowerIntake flowerIntake = new FlowerIntake();

    public Launch launch = new Launch();

    //Lists of active subsystems and telemetry
    public List<Subsystem> subsystems = Arrays.asList(mecanumDrive, intake, launch, flowerIntake);
    public List<Subsystem> telemetrySubsystems = Arrays.asList(mecanumDrive, intake, launch, flowerIntake);


    //Creates an arraylist called actions that stores all the actions that are currently being done
    private ArrayList<Action> actions = new ArrayList<Action>();

    //Resets Cycle timer whenever the cycle finishes
    public ElapsedTime cycleTimer = new ElapsedTime();

    public void init(HardwareMap hardwareMap, LinearOpMode linearOpMode) {

        //Sets the instance variable to the parameter
        //The parameters come from EKLinear
        this.hardwareMap = hardwareMap;
        this.linearOpMode = (EKLinear)linearOpMode;

        //motor hardwareMapping

        leftFront = hardwareMap.get(DcMotorEx.class, "leftFront");
        leftBack = hardwareMap.get(DcMotorEx.class, "leftBack");
        rightFront = hardwareMap.get(DcMotorEx.class, "rightFront");
        rightBack = hardwareMap.get(DcMotorEx.class, "rightBack");


        intakeMotor = hardwareMap.get(DcMotorEx.class, "intakeMotor");
        launchMotor = hardwareMap.get(DcMotorEx.class, "launchMotor");



        //servo hardwareMapping
        flowerServo = hardwareMap.get(Servo.class, "intakeMotor");

//



        //camera hardwareMapping
//        limelight3A = hardwareMap.get(Limelight3A.class, "limelight");

        //imu hardwareMapping
        imu = hardwareMap.get(IMU.class,"imu");



//////        When the motor power is set to zero it brakes instead of coasting
        leftFront.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        leftBack.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        rightFront.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        rightBack.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);


        //launchMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        leftFront.setDirection(DcMotorSimple.Direction.REVERSE);
        leftBack.setDirection(DcMotorSimple.Direction.REVERSE);


        for(Subsystem subsystem : subsystems) {
            //initialize all the subsystems in our list of active subsystems
            subsystem.init(false);
        }

        cycleTimer.reset();
    }

    //checks if a robot object is created
    //it is unnecessary and can get complicated if there multiple robot objects
    public static Robot getInstance() {
        if(robot == null) robot = new Robot();
        return robot;
    }
    //Adds actions to the list of active actions so they can be updated
    public void addAction(Action action) {
        action.start();
        actions.add(action);
    }
    //Returns the amount of running actions
    public int getActionLength(){
        return actions.size();
    }

    public void clearActions() {
        while (!actions.isEmpty()) {
            actions.remove(0);
        }
    }
    public void update() {
        //Update every single subsystem in the subsystems array initialized earlier
        for(Subsystem subsystem : subsystems) {
            subsystem.update(linearOpMode.isAuton);
            if(linearOpMode.isStopRequested()){
                return;
            }
        }
        //Cycles through all actions and updates them

        for(int i = 0; i < actions.size(); i++) {
            actions.get(i).update();

            //if an action is finished, end said action and remove it from the list of things to do
            if(actions.get(i).isComplete) {
                actions.get(i).end();
                actions.remove(i);
                i--;
            }
        }


        //telemetry
        linearOpMode.allTelemetry.addData("Match Time", linearOpMode.matchTimer.milliseconds());
        linearOpMode.allTelemetry.addData("Cycle Time", cycleTimer.milliseconds());
//        linearOpMode.allTelemetry.addData("Active Actions", getActionLength());
//        linearOpMode.allTelemetry.addData("Distance Sensor", distance.getDistance(DistanceUnit.MM));
//        linearOpMode.allTelemetry.addData("actions: ", actions);
        cycleTimer.reset();
        for(Subsystem subsystem : telemetrySubsystems) {
            linearOpMode.allTelemetry.addData("  --  ", (subsystem.getClass().getSimpleName() + "  --  "));
            subsystem.printToTelemetry(linearOpMode.allTelemetry);
        }
        linearOpMode.allTelemetry.update();
    }
}