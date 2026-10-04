package org.firstinspires.ftc.teamcode.pedro;

import com.pedropathing.follower.Follower;
import com.pedropathing.revhub.drivetrains.Mecanum;
import com.pedropathing.revhub.drivetrains.MecanumConfig;
import com.pedropathing.revhub.localizers.Encoder;
import com.pedropathing.revhub.localizers.ThreeWheelConfig;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Constants {

    // Copied from test branch (Pedro 2.x MecanumConstants)
    public static MecanumConfig drivetrainConfig = new MecanumConfig(c -> {
        c.frontLeftName.set("leftFront");
        c.frontRightName.set("rightFront");
        c.backLeftName.set("leftBack");
        c.backRightName.set("rightBack");
        c.frontLeftDirection.set(DcMotorSimple.Direction.REVERSE);
        c.frontRightDirection.set(DcMotorSimple.Direction.FORWARD);
        c.backLeftDirection.set(DcMotorSimple.Direction.REVERSE);
        c.backRightDirection.set(DcMotorSimple.Direction.FORWARD);
    });

    // Copied from test branch (Pedro 2.x ThreeWheelConstants)
    public static ThreeWheelConfig localizerConfig = new ThreeWheelConfig(c -> {
        c.leftEncoderName.set("rightFront");
        c.rightEncoderName.set("rightBack");
        c.strafeEncoderName.set("leftBack");
        c.leftPodY.set(6.13);
        c.rightPodY.set(-6.13);
        c.strafePodX.set(-4.86);
        c.forwardTicksToInches.set(0.001964593333);
        c.strafeTicksToInches.set(-0.001973913907);
        // 2.x called this turnTicksToInches. Re-run Three Wheel Tuner to confirm.
        c.turnTicksToRadians.set(0.001912528435);
        c.leftEncoderDirection.set(Encoder.FORWARD);
        c.rightEncoderDirection.set(Encoder.REVERSE);
        c.strafeEncoderDirection.set(Encoder.FORWARD);
    });

    // TODO: run Foresight Tuner, paste its foresightConfig here, then:
    // return new Follower(new ThreeWheelLocalizer(h, localizerConfig), new Mecanum(h, drivetrainConfig), <Foresight algorithm>);
    public static Follower create(HardwareMap h) {
        return null;
    }
}