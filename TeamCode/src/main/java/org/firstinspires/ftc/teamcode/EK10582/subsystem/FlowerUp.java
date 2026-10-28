package org.firstinspires.ftc.teamcode.EK10582.subsystem;

import static org.firstinspires.ftc.teamcode.EK10582.subsystem.SubsystemConstants.FLOWER_DOWN;
import static org.firstinspires.ftc.teamcode.EK10582.subsystem.SubsystemConstants.FLOWER_MEDIUM;
import static org.firstinspires.ftc.teamcode.EK10582.subsystem.SubsystemConstants.FLOWER_UP;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class FlowerUp extends Subsystem{
    public boolean activeFlowerUp;

    public void init(boolean isAuton){
        activeFlowerUp=false;

    }
    public void update(boolean isAuton) {
        if (activeFlowerUp) {
            Robot.getInstance().flowerServo.setPosition(FLOWER_UP);
        } else {
            Robot.getInstance().flowerServo.setPosition(FLOWER_MEDIUM);
        }
    }



    public void stop(){

    }
    public void printToTelemetry(Telemetry telemetry){
        telemetry.addData("Flower Servo State: ", activeFlowerUp);

    }
}
