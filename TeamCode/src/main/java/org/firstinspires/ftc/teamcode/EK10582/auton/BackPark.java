package org.firstinspires.ftc.teamcode.EK10582.auton;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import com.pedropathing.api.PoseFactory;
import com.pedropathing.math.Pose;

import static com.pedropathing.api.Paths.*;

import com.pedropathing.paths.Path;

import static com.pedropathing.ivy.pedro.PedroCommands.follow;
import static com.pedropathing.ivy.groups.Groups.sequential;
import static com.pedropathing.ivy.commands.Commands.waitMs;

import org.firstinspires.ftc.teamcode.EK10582.auton.action.AutonFlowerUp;

@Autonomous
public class BackPark extends AutonBase {

    protected PoseFactory poseFactory() {
        return PoseFactory.degrees();
    }

    private final PoseFactory backPark = poseFactory();
    // our poses
    private final Pose startPose = backPark.of(62, 132, 90);

    private final Pose shoot = backPark.of(62, 115, 90);
    private final Pose park = backPark.of(12, 115, 90);


    //poses from before

    private Path shoot() {
        return line(startPose, shoot).linear(startPose, shoot);
    }
    private Path park() {
        return line(shoot, park).linear(shoot, park);

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
                waitMs(15000), // wait 15 seconds before driving
                follow(follower, shoot()),
                follow(follower, park()),
                waitMs(2000)
        ));
    }
}
