package org.firstinspires.ftc.teamcode.pedro;

import com.pedropathing.algorithm.Foresight;
import com.pedropathing.algorithm.ForesightConfig;
import com.pedropathing.follower.Follower;
import com.pedropathing.revhub.drivetrains.Mecanum;
import com.pedropathing.revhub.drivetrains.MecanumConfig;
import com.pedropathing.revhub.localizers.Encoder;
import com.pedropathing.revhub.localizers.ThreeWheelConfig;
import com.pedropathing.revhub.localizers.ThreeWheelLocalizer;
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
//    public static ThreeWheelConfig localizerConfig = new ThreeWheelConfig(c -> {
//        c.leftEncoderName.set("rightFront");
//        c.rightEncoderName.set("rightBack");
//        c.strafeEncoderName.set("leftBack");
//        c.leftPodY.set(6.13);
//        c.rightPodY.set(-6.13);
//        c.strafePodX.set(-4.86);
//        c.forwardTicksToInches.set(0.001964593333);
//        c.strafeTicksToInches.set(-0.001973913907);
//        // 2.x called this turnTicksToInches. Re-run Three Wheel Tuner to confirm.
//        c.turnTicksToRadians.set(0.001912528435);
//        c.leftEncoderDirection.set(Encoder.REVERSE);
//        c.rightEncoderDirection.set(Encoder.FORWARD);
//        c.strafeEncoderDirection.set(Encoder.FORWARD);
//    });

    public static ThreeWheelConfig localizerConfig = new ThreeWheelConfig(c -> {
        c.leftEncoderName.set("rightFront");
        c.rightEncoderName.set("rightBack");
        c.strafeEncoderName.set("leftBack");
        c.leftPodY.set(6.279894018154288);
        c.rightPodY.set(-6.583147204752397);
        c.strafePodX.set(-4.732637236617522);
        c.forwardTicksToInches.set(0.0019562586923902465);
        c.strafeTicksToInches.set(0.001981178801386825);
        c.turnTicksToRadians.set(0.001967449831237681);
        c.leftEncoderDirection.set(Encoder.FORWARD);
        c.rightEncoderDirection.set(Encoder.REVERSE);
        c.strafeEncoderDirection.set(Encoder.REVERSE);
    });

    public static ForesightConfig foresightConfig = new ForesightConfig(
            c -> {
//                Controller primaryTranslationalForward = Controller.proportional(0.3);
//                Controller secondaryTranslationalForward = Controller.proportional(0.1);
//                Controller primaryTranslationalLateral = Controller.proportional(0.3);
//                Controller secondaryTranslationalLateral = Controller.proportional(0.1);
//                c.forwardTranslational.set(Controller.piecewise(secondaryTranslationalForward).put(2.5, primaryTranslationalForward));
//                c.strafeTranslational.set(Controller.piecewise(secondaryTranslationalLateral).put(2.5, primaryTranslationalLateral));
//                c.coast.set(Controller.proportionalFeedforward(0.010978350889324107));
//                c.brake.set(Controller.proportionalFeedforward(0.008731598255925491));
//                c.headingFeedback.set(Controller.proportional(5.258721785960744));
//                c.headingBrakeCoefficients.set(Vector2D.cartesian(0.05642143125655298, 0.0063829525363003695));
//                c.linearBrakeCoefficients.set(Matrix.diag(0.10605894992901523, 0.08719146175596092));
//                c.quadraticBrakeCoefficients.set(Matrix.diag(0.0014663966976606565, 0.0013837064502458813));
//                c.maxAchievableForwardVelocity.set(72.72923108818539);
//                c.maxAchievableStrafeVelocity.set(52.34323936525474);
//                c.naturalForwardDeceleration.set(85.01144677379789);
//                c.naturalStrafeDeceleration.set(104.49787535782846);
            }
    );

    // TODO: run Foresight Tuner, paste its foresightConfig here, then:
    // return new Follower(new ThreeWheelLocalizer(h, localizerConfig), new Mecanum(h, drivetrainConfig), <Foresight algorithm>);
    public static Follower create(HardwareMap h) {
        return new Follower(
                new ThreeWheelLocalizer(h, localizerConfig),
                new Mecanum(h, drivetrainConfig),
                new Foresight(foresightConfig));
    }
}