package org.firstinspires.ftc.teamcode.EK10582.auton.action;

//import com.qualcomm.robotcore.hardware.DcMotor;
//import com.qualcomm.robotcore.hardware.PIDFCoefficients;
import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.teamcode.EK10582.subsystem.Robot;
//import org.firstinspires.ftc.teamcode.EK10582.subsystem.SubsystemConstants;

public class AutonIntake extends Action {

    private enum State {
        SPIN,
        FINISHED
    }

    private State currentState = State.SPIN;
    private final ElapsedTime stateTimer = new ElapsedTime();
    private final ElapsedTime totalTimer = new ElapsedTime();
    private final double durationMs;

    public AutonIntake() {
        this(1200);
    }

    public AutonIntake(double durationMs) {
        this.durationMs = durationMs;
    }


    @Override
    public void start() {
        totalTimer.reset();
        stateTimer.reset();
        isComplete = false;

//        PIDFCoefficients pidf = new PIDFCoefficients(0.23, 0, 0, 12.85);
//        Robot.getInstance().launchMotor.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER, pidf);

//        Robot.getInstance().inServo.setPosition(SubsystemConstants.TRANSFER_REST);
//        Robot.getInstance().intakeMotor.setPower(0);

        setInternalState(State.SPIN);
    }

    @Override
    public void update() {

        switch (currentState) {
            case SPIN:
                Robot.getInstance().intakeMotor.setPower(0.8);
                if (stateTimer.milliseconds() >= durationMs) {
                    setInternalState(State.FINISHED);
                }
                break;

            case FINISHED:
                stopAll();
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

    private void stopAll() {
        Robot.getInstance().intakeMotor.setPower(0);
//        Robot.getInstance().inServo.setPosition(SubsystemConstants.TRANSFER_REST);
    }

    @Override
    public void end() {
        stopAll();
    }
}