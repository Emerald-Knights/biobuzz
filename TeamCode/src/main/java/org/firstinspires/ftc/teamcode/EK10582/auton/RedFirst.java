package org.firstinspires.ftc.teamcode.EK10582.auton;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.pedropathing.follower.Follower;

import org.firstinspires.ftc.teamcode.EK10582.subsystem.Robot;
import org.firstinspires.ftc.teamcode.pedro.Constants;
import com.pedropathing.api.PoseFactory;
import com.pedropathing.math.Pose;

import static com.pedropathing.api.Paths.*;

import com.pedropathing.paths.Path;

import com.pedropathing.ivy.Scheduler;

import static com.pedropathing.ivy.Scheduler.schedule;
import static com.pedropathing.ivy.pedro.PedroCommands.follow;

import com.pedropathing.ivy.Command;
import static com.pedropathing.ivy.groups.Groups.sequential;
import android.app.Notification;

@Autonomous
public class RedFirst extends OpMode {

    private Follower follower; // Add this
    public Robot robot = Robot.getInstance();
    private final PoseFactory redFirst = PoseFactory.degrees(); // the factory we added before, we are still at the top of the class

    // our poses
    private final Pose startPose = redFirst.of(56, 8, 90);

    private final Pose middle = redFirst.of(56, 18, 90);
    private final Pose park = redFirst.of(11, 99, 90);


    // other poses...
    private final Pose controlPose1 = redFirst.of(22, 27, 0);

    //poses from before

    private Path middle() {
        return line(startPose, middle).linear(startPose, middle);
    }
    private Path park() {
        return curve(middle, controlPose1, park).linear(middle, park);

    }

    @Override
    public void init() {

        Scheduler.reset();
        follower = Constants.create(hardwareMap);
        follower.setPose(startPose);

    }

    @Override
    public void start() {

        schedule(
                sequential(
                        follow(follower, middle()),
                        // AutonShoot command goes here
                        follow(follower, park())
                )
        );

    }

    @Override
    public void loop() {

        follower.update();
        Scheduler.execute();

        telemetry.addData("X", follower.pose().x());
        telemetry.addData("Y", follower.pose().y());
        telemetry.addData("Heading", Math.toDegrees(follower.pose().heading()));
        telemetry.addData("Follower Mode", follower.mode());
        telemetry.update();

    }
}