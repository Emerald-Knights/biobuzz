package org.firstinspires.ftc.teamcode.EK10582.auton;

import com.pedropathing.api.PoseFactory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous
public class BlueBackShoot extends RedBackShoot {
    @Override

    protected PoseFactory poseFactory() {
        return PoseFactory.degrees().mirrorX(70.75).mirrorY(70.75);
    }
}