package org.firstinspires.ftc.teamcode.EK10582.auton;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import com.pedropathing.api.PoseFactory;
import com.pedropathing.math.Pose;

import org.firstinspires.ftc.teamcode.EK10582.auton.action.AutonIntake;
import org.firstinspires.ftc.teamcode.EK10582.auton.action.AutonShoot;

import static com.pedropathing.api.Paths.*;

import com.pedropathing.paths.Path;

import org.firstinspires.ftc.teamcode.pedro.Constants;

import static com.pedropathing.ivy.commands.Commands.waitMs;
import static com.pedropathing.ivy.groups.Groups.parallel;
import static com.pedropathing.ivy.pedro.PedroCommands.follow;
import static com.pedropathing.ivy.groups.Groups.sequential;

@Autonomous
public class FrontIntake extends AutonBase {

    protected PoseFactory poseFactory() {
        return PoseFactory.degrees();
    }

    private final PoseFactory frontIntake = poseFactory();
    // our poses
    private final Pose startPose = frontIntake.of(56, 8, 270);
    private final Pose shoot = frontIntake.of(56, 18, 270);
    private final Pose ballBack = frontIntake.of(30, 10, 180);
    private final Pose ballPosition = frontIntake.of(5, 10, 180);
    private final Pose park = frontIntake.of(8, 96, 270);


    //poses from before
    private final Pose controlPose1 = frontIntake.of(38, 60, 0);

    private Path shoot() {
        return line(startPose, shoot).linear(startPose, shoot);
    }
    private Path intakeReady() {
        return line(shoot, ballBack).linear(shoot, ballBack);
    }
    private Path intake() {
        //caps speed at 20 in/s for just this path so the intake has time to grab the balls
        return line(ballBack, ballPosition).linear(ballBack, ballPosition)
                .with(Constants.foresightConfig.maxVelocityConstraint.at(23.0));
    }
    private Path intakeBack() {
        return line(ballPosition, ballBack).linear(ballPosition, ballBack);
    }
    private Path park() {
        return curve(ballBack,controlPose1, park).linear(ballBack, park);
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
                follow(follower, intakeReady()),
                parallel(
                        follow(follower, intake()),
                        action(new AutonIntake(3000))
                ),
                waitMs(500),
                parallel(
                        follow(follower, intakeBack()),
                        action(new AutonIntake(3000))
                ),
                follow(follower, park()),
                waitMs(2000)

        ));
    }
}
