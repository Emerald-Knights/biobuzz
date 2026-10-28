package org.firstinspires.ftc.teamcode.EK10582.auton.action;


//import com.qualcomm.robotcore.hardware.DcMotor;
//import com.qualcomm.robotcore.hardware.PIDFCoefficients;
import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.teamcode.EK10582.subsystem.Robot;
//import org.firstinspires.ftc.teamcode.EK10582.subsystem.SubsystemConstants;


public class AutonShoot extends Action {


    private enum State {
        SPIN1,
        WAIT1,
        SPIN2,
        INTAKE,
        WAIT3,
        SPIN3,
        WAIT2,
        SPIN4,
        FINISHED
    }


    private State currentState = State.SPIN1;
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


        setInternalState(State.SPIN1);
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
            case SPIN1:
                //Robot.getInstance().launch.activeLaunch = true;
                Robot.getInstance().launchMotor.setPower(0.8);
                if (stateTimer.milliseconds() >= 250) {
                    shootCounter++;
                    setInternalState(shootCounter >= MAX_SHOOTS ? State.FINISHED : State.WAIT1);
                }
                break;


            case WAIT1:
                //Robot.getInstance().launch.activeLaunch = true;
                Robot.getInstance().launchMotor.setPower(0.0);
                if (stateTimer.milliseconds() >= 1200) {
                    setInternalState(State.SPIN2);
                }
                break;


            case SPIN2:
                //Robot.getInstance().launch.activeLaunch = true;
                Robot.getInstance().launchMotor.setPower(0.8);
                if (stateTimer.milliseconds() >= 250) {
                    shootCounter++;
                    setInternalState(shootCounter >= MAX_SHOOTS ? State.FINISHED : State.INTAKE);
                }
                break;


            case INTAKE:
                //Robot.getInstance().launch.activeLaunch = true;
                Robot.getInstance().launchMotor.setPower(0.0);
                Robot.getInstance().intakeMotor.setPower(0.8);
                if (stateTimer.milliseconds() >= 1000) {
                    setInternalState(State.WAIT2);
                }
                break;

            case WAIT2:
                //Robot.getInstance().launch.activeLaunch = true;
                Robot.getInstance().launchMotor.setPower(0.0);
                Robot.getInstance().intakeMotor.setPower(0.0);
                if (stateTimer.milliseconds() >= 1200) {
                    setInternalState(State.SPIN3);
                }
                break;

            case SPIN3:
                //Robot.getInstance().launch.activeLaunch = true;
                Robot.getInstance().launchMotor.setPower(0.8);
                Robot.getInstance().intakeMotor.setPower(0.0);
                if (stateTimer.milliseconds() >= 250) {
                    shootCounter++;
                    setInternalState(shootCounter >= MAX_SHOOTS ? State.FINISHED : State.WAIT3);
                }
                break;


            case WAIT3:
                // Crucial: Let the flywheel settle for 400ms after the intake "thump"
                // and the previous shot's RPM drop.
                //Robot.getInstance().launch.activeLaunch = false;
                Robot.getInstance().launchMotor.setPower(0.0);
                if (stateTimer.milliseconds() >= 1200) {
                    setInternalState(State.SPIN4);
                }
                break;


            case SPIN4:
                //Robot.getInstance().launch.activeLaunch = true;
                Robot.getInstance().launchMotor.setPower(0.8);
                if (stateTimer.milliseconds() >= 250) {
                    shootCounter++;
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
        //Robot.getInstance().launch.activeLaunch = false;
        Robot.getInstance().launchMotor.setPower(0.0);
        Robot.getInstance().intakeMotor.setPower(0.0);
//        Robot.getInstance().inServo.setPosition(SubsystemConstants.TRANSFER_REST);
    }


    @Override
    public void end() {
        stopAll();
    }
}

