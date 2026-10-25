package org.firstinspires.ftc.teamcode.EK10582.subsystem;

import static org.firstinspires.ftc.teamcode.EK10582.subsystem.SubsystemConstants.FLOWER_DOWN;
import static org.firstinspires.ftc.teamcode.EK10582.subsystem.SubsystemConstants.FLOWER_UP;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class FlowerIntake extends Subsystem{
    public boolean activeFlower;

    public void init(boolean isAuton){
        activeFlower=false;

    }
    public void update(boolean isAuton) {
        if (activeFlower) {
            Robot.getInstance().flowerServo.setPosition(FLOWER_DOWN);
        } else {
            Robot.getInstance().flowerServo.setPosition(FLOWER_UP);
        }
    }



    public void stop(){

    }
    public void printToTelemetry(Telemetry telemetry){
        telemetry.addData("Flower Servo State: ", activeFlower);

    }
}
