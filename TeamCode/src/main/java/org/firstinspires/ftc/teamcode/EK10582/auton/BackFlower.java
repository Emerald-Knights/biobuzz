package org.firstinspires.ftc.teamcode.EK10582.auton;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import com.pedropathing.api.PoseFactory;
import com.pedropathing.math.Pose;

import org.firstinspires.ftc.teamcode.EK10582.auton.action.AutonFlowerUp;
import org.firstinspires.ftc.teamcode.EK10582.auton.action.AutonShoot;

import static com.pedropathing.api.Paths.*;

import com.pedropathing.paths.Path;

import static com.pedropathing.ivy.pedro.PedroCommands.follow;
import static com.pedropathing.ivy.groups.Groups.sequential;

@Autonomous
public class BackFlower extends AutonBase {

    protected PoseFactory poseFactory() {
        return PoseFactory.degrees();
    }

    private final PoseFactory backFlower = poseFactory();
    // our poses
    private final Pose startPose = backFlower.of(62, 132, 270);
    private final Pose shoot = backFlower.of(62, 115, 270);
    private final Pose flowerBack = backFlower.of(47, 115, 90);
    private final Pose flowerForward = backFlower.of(47, 128, 90);
    private final Pose park = backFlower.of(12, 115, 180);


    //poses from before
    private final Pose controlPose1 = backFlower.of(52, 90, 0);

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
                action(new AutonFlowerUp()),
                follow(follower, shoot()),
                action(new AutonShoot()),
                follow(follower, park())
                //remember to add a intake action
        ));
    }
}

