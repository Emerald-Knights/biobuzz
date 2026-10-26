package org.firstinspires.ftc.teamcode.EK10582.auton;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import com.pedropathing.api.PoseFactory;
import com.pedropathing.math.Pose;

import static com.pedropathing.api.Paths.*;

import com.pedropathing.paths.Path;

import static com.pedropathing.ivy.commands.Commands.waitMs;
import static com.pedropathing.ivy.pedro.PedroCommands.follow;
import static com.pedropathing.ivy.groups.Groups.sequential;

@Autonomous
public class FrontPark extends AutonBase {

    protected PoseFactory poseFactory() {
        return PoseFactory.degrees();
    }

    private final PoseFactory frontPark = poseFactory();
    // our poses
    private final Pose startPose = frontPark.of(56, 8, 270);

    private final Pose middle = frontPark.of(56, 18, 270);
    private final Pose park = frontPark.of(8, 96, 270);

    //control points
    private final Pose controlPose1 = frontPark.of(22, 27, 0);

    //poses from before

    private Path middle() {
        return line(startPose, middle).linear(startPose, middle);
    }
    private Path park() {
        return curve(middle, controlPose1, park).linear(middle, park);

    }

    @Override
    protected Pose startPose() {
        return startPose;
    }

    @Override
    protected void autonTelemetry() {
        allTelemetry.addData("X", follower.pose().x());
        allTelemetry.addData("Y", follower.pose().y());
        allTelemetry.addData("Heading", Math.toDegrees(follower.pose().heading()));
        allTelemetry.addData("Follower Mode", follower.mode());
    }

    @Override
    public void runOpMode() {
        waitForStart(); // creates the follower at startPose()
        run(sequential(
                follow(follower, middle()),
                follow(follower, park()),
                waitMs(2000)
        ));
    }
}
