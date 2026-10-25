package org.firstinspires.ftc.teamcode.EK10582.auton;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import com.pedropathing.api.PoseFactory;
import com.pedropathing.math.Pose;
import org.firstinspires.ftc.teamcode.EK10582.auton.action.AutonShoot;

import static com.pedropathing.api.Paths.*;

import com.pedropathing.paths.Path;

import static com.pedropathing.ivy.pedro.PedroCommands.follow;
import static com.pedropathing.ivy.groups.Groups.sequential;
import static com.pedropathing.ivy.commands.Commands.waitMs;

@Autonomous
public class RedBackShoot extends AutonBase {

    protected PoseFactory poseFactory() {
        return PoseFactory.degrees();
    }

    private final PoseFactory redBackShoot = poseFactory();
    // our poses
    private final Pose startPose = redBackShoot.of(62, 132, 90);

    private final Pose shoot = redBackShoot.of(62, 115, 90);
    private final Pose park = redBackShoot.of(14, 115, 90);


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
                waitMs(15000), // wait 15 seconds before driving
                follow(follower, shoot()),
                action(new AutonShoot()),
                follow(follower, park()),
                waitMs(2000)
        ));
    }
}
