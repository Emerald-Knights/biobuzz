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
public class RedTwoIntake extends AutonBase {

    protected PoseFactory poseFactory() {
        return PoseFactory.degrees();
    }

    private final PoseFactory redTwoIntake = poseFactory();
    // our poses
    private final Pose startPose = redTwoIntake.of(62, 132, 270);
    private final Pose shoot = redTwoIntake.of(62, 115, 270);
    private final Pose flowerBack = redTwoIntake.of(47, 115, 90);
    private final Pose flowerForward = redTwoIntake.of(47, 128, 90);
    private final Pose park = redTwoIntake.of(10, 115, 180);


    //poses from before
    private final Pose controlPose1 = redTwoIntake.of(52, 90, 0);

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

