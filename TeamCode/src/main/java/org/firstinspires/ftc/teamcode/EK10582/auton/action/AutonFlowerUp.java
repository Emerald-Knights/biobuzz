package org.firstinspires.ftc.teamcode.EK10582.auton.action;

import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.teamcode.EK10582.subsystem.Robot;
import org.firstinspires.ftc.teamcode.EK10582.subsystem.SubsystemConstants;

public class AutonFlowerUp extends Action {

    private enum State {
   // Run the AutonIntake cycle
        UP,        // Flower back up
        FINISHED
    }

    private State currentState = State.UP;
    private final ElapsedTime stateTimer = new ElapsedTime();

    @Override
    public void start() {
        stateTimer.reset();
        isComplete = false;
        currentState = State.UP;
    }

    @Override
    public void update() {
        switch (currentState) {

            case UP:
                Robot.getInstance().flowerServo.setPosition(SubsystemConstants.FLOWER_MEDIUM);
                if (stateTimer.milliseconds() >= 400) {
                    setInternalState(State.FINISHED);
                }
                break;

            case FINISHED:
                isComplete = true;
                break;
        }
    }

    private void setInternalState(State newState) {
        if (currentState != newState) {
            currentState = newState;
            stateTimer.reset();
        }
    }

    @Override
    public void end() {
    }
}
