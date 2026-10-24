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
public class RedIntake extends AutonBase {

    protected PoseFactory poseFactory() {
        return PoseFactory.degrees();
    }

    private final PoseFactory redIntake = poseFactory();
    // our poses
    private final Pose startPose = redIntake.of(56, 8, 90);
    private final Pose shoot = redIntake.of(56, 18, 90);
    private final Pose flowerBack = redIntake.of(26, 47, 180);
    private final Pose flowerForward = redIntake.of(13, 47, 180);
    private final Pose park = redIntake.of(12, 102, 90);


    //poses from before
    private final Pose controlPose1 = redIntake.of(41, 37, 0);

    private Path shoot() {
        return line(startPose, shoot).linear(startPose, shoot);
    }
    private Path intakeReady() {
        return curve(shoot, controlPose1, flowerBack).linear(shoot, flowerBack);
    }
    private Path intake() {
        return line(flowerBack, flowerForward).linear(flowerBack, flowerForward);
    }
    private Path intakeBack() {
        return line(flowerForward, flowerBack).linear(flowerForward, flowerBack);
    }
    private Path park() {
        return line(flowerBack, park).linear(flowerBack, park);
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
                follow(follower, shoot()),
                action(new AutonShoot()),
                follow(follower, park())
                //remember to add a intake action
        ));
    }
}
