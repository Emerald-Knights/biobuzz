package org.firstinspires.ftc.teamcode.EK10582.auton;

import com.pedropathing.api.PoseFactory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous
public class BlueFirst extends RedFirst {
    @Override
    protected PoseFactory poseFactory() {
        return PoseFactory.degrees().mirrorX(70.75);
    }
}