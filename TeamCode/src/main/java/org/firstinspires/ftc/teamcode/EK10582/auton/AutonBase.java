package org.firstinspires.ftc.teamcode.EK10582.auton;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.pedropathing.follower.Follower;
import com.pedropathing.ivy.Command;
import com.pedropathing.ivy.CommandBuilder;
import com.pedropathing.ivy.Scheduler;
import com.pedropathing.math.Pose;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.EK10582.EKLinear;
import org.firstinspires.ftc.teamcode.EK10582.subsystem.Robot;
import org.firstinspires.ftc.teamcode.EK10582.auton.action.Action;
import org.firstinspires.ftc.teamcode.EK10582.teleop.DriverStation;
import org.firstinspires.ftc.teamcode.pedro.Constants;


public abstract class AutonBase extends EKLinear {

    protected Follower follower;

    //override this in autons that drive with pedro. null means no follower gets created
    protected Pose startPose() {
        return null;
    }

    public void runAction(Action action) { //only used in synchronous auton b/c while loop is blocking
        action.start();
        while(!action.isComplete) {
            action.update();
            Robot.getInstance().update();
        }
        action.end();
    }

    //wraps one of our Actions as an Ivy Command so it can go inside sequential(), parallel(), etc.
    //the Command drives the Action itself, so do NOT also add it with Robot.addAction()
    public CommandBuilder action(Action action) {
        return Command.build()
                .setStart(() -> {
                    action.isComplete = false; //lets the same Action object be reused
                    action.start();
                })
                .setExecute(action::update)
                .setDone(() -> action.isComplete)
                .setEnd(endCondition -> action.end()); //runs on finish or interrupt
    }

    //schedules an Ivy command and blocks until it finishes, updating everything each loop
    public void run(Command command) {
        Scheduler.schedule(command);
        while(opModeIsActive() && Scheduler.isScheduled(command)) {
            if(follower != null) follower.update();
            Scheduler.execute();
            autonTelemetry();
            robot.update(); //also sends telemetry, so autonTelemetry() has to run before this
        }
    }

    //override this to add auton-specific telemetry every loop. use allTelemetry so it shows on dashboard too
    protected void autonTelemetry() {
    }

    @Override
    public void waitForStart() {
        //initing things
        isAuton = true;
        allTelemetry = new MultipleTelemetry(FtcDashboard.getInstance().getTelemetry(), this.telemetry);
        matchTimer = new ElapsedTime();
        driverStation = new DriverStation(gamepad1, gamepad2);
        robot.init(hardwareMap, this);

        Scheduler.reset();
        if(startPose() != null) {
            follower = Constants.create(hardwareMap);
            follower.setPose(startPose());
        }

        while(!isStarted() && !isStopRequested()){
            telemetry.addData("Waiting for Start","");
            telemetry.update();
        }

        //after start
        matchTimer.reset();
    }

}
