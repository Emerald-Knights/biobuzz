package org.firstinspires.ftc.teamcode.test;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.EK10582.auton.AutonBase;
import org.firstinspires.ftc.teamcode.EK10582.auton.action.AutonShoot;

@Autonomous(name = "AutonShoot Test", group = "test")
public class AutonShootTest extends AutonBase {

    @Override
    public void runOpMode() {
        waitForStart();   // AutonBase's version: sets isAuton = true and calls robot.init()

        AutonShoot shoot = new AutonShoot();
        ElapsedTime timer = new ElapsedTime();
        shoot.start();

        while (opModeIsActive() && !shoot.isComplete) {
            shoot.update();
            robot.update();

            telemetry.addData("Time (ms)", timer.milliseconds());
            telemetry.addData("Launch power", robot.launchMotor.getPower());
            telemetry.update();
        }
        shoot.end();

        telemetry.addData("Done in (ms)", timer.milliseconds());
        telemetry.update();
        while (opModeIsActive()) { idle(); }   // keep the result on screen
    }
}
