package org.firstinspires.ftc.teamcode.EK10582.auton;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import com.pedropathing.api.PoseFactory;
import com.pedropathing.math.Pose;
import org.firstinspires.ftc.teamcode.EK10582.auton.action.AutonShoot;

import static com.pedropathing.api.Paths.*;

import com.pedropathing.paths.Path;

import static com.pedropathing.ivy.pedro.PedroCommands.follow;
import static com.pedropathing.ivy.groups.Groups.sequential;

@Autonomous
public class RedFirst extends AutonBase {

    protected PoseFactory poseFactory() {
        return PoseFactory.degrees();
    }

    private final PoseFactory redFirst = poseFactory();
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
                action(new AutonShoot()),
                follow(follower, park())
        ));
    }
}
