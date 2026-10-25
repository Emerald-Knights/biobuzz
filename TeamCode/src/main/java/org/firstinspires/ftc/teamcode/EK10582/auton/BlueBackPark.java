package org.firstinspires.ftc.teamcode.EK10582.auton;

import com.pedropathing.api.PoseFactory;

public class BlueBackPark extends RedBackPark{
    @Override

    protected PoseFactory poseFactory() {
        return PoseFactory.degrees().mirrorX(70.75).mirrorY(70.75);
    }
}
