package org.firstinspires.ftc.teamcode.EK10582.auton.action;

import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.teamcode.EK10582.subsystem.Robot;
import org.firstinspires.ftc.teamcode.EK10582.subsystem.SubsystemConstants;

public class AutonFlowerDown extends Action {

    private enum State {
        DOWN,      // Flower down
        FINISHED
    }

    private State currentState = State.DOWN;
    private final ElapsedTime stateTimer = new ElapsedTime();

    // the intake cycle runs inside this action, so it is NOT added with Robot.addAction()

    @Override
    public void start() {
        stateTimer.reset();
        isComplete = false;
        currentState = State.DOWN;
    }

    @Override
    public void update() {
        switch (currentState) {
            case DOWN:
                Robot.getInstance().flowerServo.setPosition(SubsystemConstants.FLOWER_POSITION);
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
