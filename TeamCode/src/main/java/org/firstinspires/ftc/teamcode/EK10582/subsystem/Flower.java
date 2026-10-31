package org.firstinspires.ftc.teamcode.EK10582.subsystem;

import static org.firstinspires.ftc.teamcode.EK10582.subsystem.SubsystemConstants.FLOWER_DOWN;
import static org.firstinspires.ftc.teamcode.EK10582.subsystem.SubsystemConstants.FLOWER_MEDIUM;
import static org.firstinspires.ftc.teamcode.EK10582.subsystem.SubsystemConstants.FLOWER_UP;

import org.firstinspires.ftc.robotcore.external.Telemetry;

/**
 * Single owner of the flower servo. Defaults to FLOWER_MEDIUM; "down" (intake) takes
 * priority over "up" if both are requested at once.
 */
public class Flower extends Subsystem{
    public boolean down;
    public boolean up;

    private double targetPosition = Double.NaN;

    public void init(boolean isAuton){
        down = false;
        up = false;
        targetPosition = Double.NaN;
    }
    public void update(boolean isAuton) {
        double position;
        if (down) {
            position = FLOWER_DOWN;
        } else if (up) {
            position = FLOWER_UP;
        } else {
            position = FLOWER_MEDIUM;
        }

        // Only send a new command when the target actually changes
        if (position != targetPosition) {
            targetPosition = position;
            Robot.getInstance().flowerServo.setPosition(position);
        }
    }

    public void stop(){

    }
    public void printToTelemetry(Telemetry telemetry){
        telemetry.addData("Flower Down: ", down);
        telemetry.addData("Flower Up: ", up);
        telemetry.addData("Flower Target: ", targetPosition);
    }
}
