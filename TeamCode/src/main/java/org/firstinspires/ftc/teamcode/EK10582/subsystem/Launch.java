package org.firstinspires.ftc.teamcode.EK10582.subsystem;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Launch extends Subsystem{

    public boolean activeLaunch;

    public void init(boolean isAuton){
        activeLaunch=false;

    }
    public void update(boolean isAuton) {
        if (isAuton) return;

        if (activeLaunch) {
            Robot.getInstance().launchMotor.setPower(0.8);
        } else {
            Robot.getInstance().launchMotor.setPower(0);
        }
    }



    public void stop(){

    }
    public void printToTelemetry(Telemetry telemetry){
        telemetry.addData("Launch State", activeLaunch);

    }


}
