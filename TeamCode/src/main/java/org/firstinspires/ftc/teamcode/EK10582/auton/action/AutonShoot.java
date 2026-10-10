package org.firstinspires.ftc.teamcode.EK10582.auton.action;

//import com.qualcomm.robotcore.hardware.DcMotor;
//import com.qualcomm.robotcore.hardware.PIDFCoefficients;
import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.teamcode.EK10582.subsystem.Robot;
//import org.firstinspires.ftc.teamcode.EK10582.subsystem.SubsystemConstants;

public class AutonShoot extends Action {

    private enum State {
        SPIN,        // Initial spin up
        //FIRE,           // Servo UP
        //RESET_SERVO,    // Servo DOWN
        //LOAD_NEXT,      // Intake ON (Bumping next ball)
        WAIT,        // WAIT for flywheel to recover speed after shot/load
        FINISHED
    }

    private State currentState = State.SPIN;
    private final ElapsedTime stateTimer = new ElapsedTime();
    private final ElapsedTime totalTimer = new ElapsedTime();

    private int shootCounter = 0;
    private final int MAX_SHOOTS = 4;

    @Override
    public void start() {
        totalTimer.reset();
        shootCounter = 0;
        stateTimer.reset();

//        PIDFCoefficients pidf = new PIDFCoefficients(0.23, 0, 0, 12.85);
//        Robot.getInstance().launchMotor.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER, pidf);

//        Robot.getInstance().inServo.setPosition(SubsystemConstants.TRANSFER_REST);
//        Robot.getInstance().intakeMotor.setPower(0);

        setInternalState(State.SPIN);
    }

    @Override
    public void update() {
        // Increased safety timeout to 5s to account for re-warming
//        if (totalTimer.milliseconds() >= 4000 && shootCounter >= MAX_SHOOTS) {
//            setInternalState(State.FINISHED);
//        }

        // Always keep flywheel spinning at target during the action
//        if (currentState != State.FINISHED) {
//            Robot.getInstance().launchMotor.setVelocity(1600);
//        }

        switch (currentState) {
            case SPIN:
                //Robot.getInstance().launch.activeLaunch = true;
                Robot.getInstance().launchMotor.setPower(0.8);
                if (stateTimer.milliseconds() >= 500) {
                    shootCounter++;
                    setInternalState(shootCounter >= MAX_SHOOTS ? State.FINISHED : State.WAIT);
                }
                break;

//            case FIRE:
//                Robot.getInstance().inServo.setPosition(SubsystemConstants.TRANSFER_POSITION);
//                Robot.getInstance().intakeMotor.setPower(0);
//                if (stateTimer.milliseconds() >= 600) {
//                    setInternalState(State.RESET_SERVO);
//                }
//                break;

//            case RESET_SERVO:
//                Robot.getInstance().inServo.setPosition(SubsystemConstants.TRANSFER_REST);
//                if (stateTimer.milliseconds() >= 500) {
//                    shootCounter++;
//                    if (shootCounter < MAX_SHOOTS) {
//                        setInternalState(State.LOAD_NEXT);
//                    } else {
//                        setInternalState(State.FINISHED);
//                    }
//                }
//                break;

//            case LOAD_NEXT:
//                // Run intake slightly longer (700ms) to ensure Ball 3 makes it
//                Robot.getInstance().intakeMotor.setPower(0.9);
//                if (stateTimer.milliseconds() >= 700) {
//                    Robot.getInstance().intakeMotor.setPower(0);
//                    setInternalState(State.RE_WARM);
//                }
//                break;

            case WAIT:
                // Crucial: Let the flywheel settle for 400ms after the intake "thump"
                // and the previous shot's RPM drop.
                //Robot.getInstance().launch.activeLaunch = false;
                Robot.getInstance().launchMotor.setPower(0.0);
                if (stateTimer.milliseconds() >= 400) {
                    setInternalState(State.SPIN);
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
        //Robot.getInstance().launch.activeLaunch = false;
        Robot.getInstance().launchMotor.setPower(0);
//        Robot.getInstance().inServo.setPosition(SubsystemConstants.TRANSFER_REST);
    }

    @Override
    public void end() {
        stopAll();
    }
}