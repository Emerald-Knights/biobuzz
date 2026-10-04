package org.firstinspires.ftc.teamcode.test;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;

@TeleOp(name = "Odo Test")
public class OdoTester extends LinearOpMode {

    private DcMotorEx leftEncoder;
    private DcMotorEx rightEncoder;
    private DcMotorEx frontEncoder;

    @Override
    public void runOpMode() {

        // Odometry encoder ports
        leftEncoder = hardwareMap.get(DcMotorEx.class, "rightFront");
        rightEncoder = hardwareMap.get(DcMotorEx.class, "rightBack");
        frontEncoder = hardwareMap.get(DcMotorEx.class, "leftBack");

        // Encoder directions
        leftEncoder.setDirection(DcMotorEx.Direction.REVERSE);
        rightEncoder.setDirection(DcMotorEx.Direction.REVERSE);
        frontEncoder.setDirection(DcMotorEx.Direction.REVERSE);

        waitForStart();

        int leftStart = leftEncoder.getCurrentPosition();
        int rightStart = rightEncoder.getCurrentPosition();
        int frontStart = frontEncoder.getCurrentPosition();

        while (opModeIsActive()) {

            int leftPosition = leftEncoder.getCurrentPosition() - leftStart;
            int rightPosition = rightEncoder.getCurrentPosition() - rightStart;
            int frontPosition = frontEncoder.getCurrentPosition() - frontStart;

            telemetry.addData("Left Odo", leftPosition);
            telemetry.addData("Right Odo", rightPosition);
            telemetry.addData("Back Odo", frontPosition);

            telemetry.update();
        }
    }
}