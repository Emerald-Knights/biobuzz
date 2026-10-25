package org.firstinspires.ftc.teamcode.EK10582.auton;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;


import com.pedropathing.api.PoseFactory;
import com.pedropathing.math.Pose;


import org.firstinspires.ftc.teamcode.EK10582.auton.action.AutonIntake;
import org.firstinspires.ftc.teamcode.EK10582.auton.action.AutonShoot;


import static com.pedropathing.api.Paths.*;


import com.pedropathing.paths.Path;


import static com.pedropathing.ivy.commands.Commands.waitMs;
import static com.pedropathing.ivy.groups.Groups.parallel;
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
    private final Pose ballBack = redIntake.of(30, 8, 180);
    private final Pose ballposition = redIntake.of(12, 8, 180);
    private final Pose park = redIntake.of(8, 96, 90);




    //poses from before
    private final Pose controlPose1 = redIntake.of(38, 60, 0);


    private Path shoot() {
        return line(startPose, shoot).linear(startPose, shoot);
    }
    private Path intakeReady() {
        return line(shoot, ballBack).linear(shoot, ballBack);
    }
    private Path intake() {
        return line(ballBack, ballposition).linear(ballBack, ballposition);
    }
    private Path intakeBack() {
        return line(ballposition, ballBack).linear(ballposition, ballBack);
    }
    private Path park() {
        return curve(ballposition,controlPose1, park).linear(ballposition, park);
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
                        action(new AutonIntake())
                ),
                waitMs(1000),
                parallel(
                        follow(follower, intakeBack()),
                        action(new AutonIntake())
                ),
                follow(follower, park())


        ));
    }
}
