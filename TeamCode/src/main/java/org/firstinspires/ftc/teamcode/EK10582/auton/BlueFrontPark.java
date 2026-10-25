package org.firstinspires.ftc.teamcode.EK10582.auton;

import com.pedropathing.api.PoseFactory;

public class BlueFrontPark extends RedFrontPark{
    @Override

    protected PoseFactory poseFactory() {
        return PoseFactory.degrees().mirrorX(70.75).mirrorY(70.75);
    }
}
