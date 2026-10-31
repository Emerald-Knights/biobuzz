package org.firstinspires.ftc.teamcode.EK10582.teleop;

import com.qualcomm.robotcore.hardware.Gamepad;


class JoystickConstants {
    public static double DEADZONE = 0.01;
    public static double minJoystick = 0.1;
    public static double maxJoystick = 1;
}

public class DriverStation {
    Gamepad gamepad1, gamepad2;

    public DriverStation(Gamepad gamepad1, Gamepad gamepad2) {
        this.gamepad1 = gamepad1;
        this.gamepad2 = gamepad2;
    }

    //-----------------------first controller------------------------------

    public double getLeftStickY() {
        return -filterJoystick(gamepad1.left_stick_y);
    }

    public double getLeftStickX() {
        return filterJoystick(gamepad1.left_stick_x);
    }

    public double getRightStickX() {
        return filterJoystick(gamepad1.right_stick_x);
    }


    public double getRightTrigger1(){
        return filterJoystick(gamepad1.right_trigger);
    }
    public double getLeftTrigger1(){
        return filterJoystick(gamepad1.left_trigger);
    }

    boolean lateA1 = false;
    public boolean getA1(){
        boolean out;
        out = gamepad1.a && !lateA1;
        lateA1 = gamepad1.a;
        return out;
    }

    boolean lateB1 = false;
    public boolean getB1(){
        boolean out;
        out = gamepad1.b && !lateB1;
        lateB1 = gamepad1.b;
        return out;
    }



    boolean lateLB1 = false;
    public boolean getLB1(){
        boolean out;
        out = gamepad1.left_bumper && !lateLB1;
        lateLB1 = gamepad1.left_bumper;
        return out;
    }

    boolean lateDPADUP1 = false;
    public boolean getDPADUP1(){
        boolean out;
        out = gamepad1.dpad_up && !lateDPADUP1;
        lateDPADUP1 = gamepad1.dpad_up;
        return out;
    }
    boolean lateDPADDOWN1 = false;
    public boolean getDPADDOWN1() {
        boolean out;
        out = gamepad1.dpad_down && !lateDPADDOWN1;
        lateDPADDOWN1 = gamepad1.dpad_down;
        return out;
    }

    boolean lateDPADLEFT1 = false;
    public boolean getDPADLEFT1(){
        boolean out;
        out = gamepad1.dpad_left && !lateDPADLEFT1;
        lateDPADLEFT1 = gamepad1.dpad_left;
        return out;
    }

    boolean lateDPADRIGHT1 = false;
    public boolean getDPADRIGHT1(){
        boolean out;
        out = gamepad1.dpad_right && !lateDPADRIGHT1;
        lateDPADRIGHT1 = gamepad1.dpad_right;
        return out;
    }

    boolean latereset1 = false;
    public boolean getReset() {
        boolean out;
        out = !gamepad1.dpad_down && latereset1;
        latereset1 = gamepad1.dpad_down;
        return out;
    }



    public double filterJoystick(double input) {
        //implements both deadzone and scaled drive
        if(Math.abs(input) < JoystickConstants.DEADZONE) return 0;
        if(input > 0) {
            return JoystickConstants.minJoystick * Math.pow((JoystickConstants.maxJoystick / JoystickConstants.minJoystick), input);
        } else {
            input *= -1;
            return -JoystickConstants.minJoystick * Math.pow((JoystickConstants.maxJoystick / JoystickConstants.minJoystick), input);
        }
    }



    //----------------------second controller---------------------------------
    public double getLY2(){return filterJoystick(gamepad2.left_stick_y);} // motor

    public double getRY2() { return filterJoystick(gamepad2.right_stick_y); } // motor, limit max speed

    public double getRT2() { return filterJoystick(gamepad2.right_trigger); } // motor, limit max speed

    public double getLT2() { return filterJoystick(gamepad2.left_trigger); } // motor, limit max speed

    public double getWristPosition() { return filterJoystick(gamepad2.right_trigger - gamepad2.left_trigger); } // servo

    boolean lateA2 = false;
    public boolean getA2() {
        boolean out;
        out = gamepad2.a && !lateA2;
        lateA2 = gamepad2.a;
        return out;
    }

    boolean lateB2 = false;
    public boolean getB2() {
        boolean out;
        out = gamepad2.b && !lateB2;
        lateB2 = gamepad2.b;
        return out;
    }
    boolean lateX2 = false;
    public boolean getX2() {
        boolean out;
        out = gamepad2.x && !lateX2;
        lateX2 = gamepad2.x;
        return out;
    }
    boolean lateY2 = false;
    public boolean getY2() {
        boolean out;
        out = gamepad2.y && !lateY2;
        lateY2 = gamepad2.y;
        return out;
    }


    boolean lateDPADUP2 = false;
    public boolean getDPADUP2(){
        boolean out;
        out = gamepad2.dpad_up && !lateDPADUP2;
        lateDPADUP2 = gamepad2.dpad_up;
        return out;
    }
    boolean lateDPADDOWN2 = false;
    public boolean getDPADDOWN2() {
        boolean out;
        out = gamepad2.dpad_down && !lateDPADDOWN2;
        lateDPADDOWN2 = gamepad2.dpad_down;
        return out;
    }
    boolean lateDPADLEFT2 = false;
    public boolean getDPADLEFT2(){
        boolean out;
        out = gamepad2.dpad_left && !lateDPADLEFT2;
        lateDPADLEFT2 = gamepad2.dpad_left;
        return out;
    }
    boolean lateDPADRIGHT2 = false;
    public boolean getDPADRIGHT2() {
        boolean out;
        out = gamepad2.dpad_right && !lateDPADRIGHT2;
        lateDPADRIGHT2 = gamepad2.dpad_right;
        return out;
    }

    boolean lateRB2 = false;
    public boolean getRB2() {
        boolean out;
        out = gamepad2.right_bumper && !lateRB2;
        lateRB2 = gamepad2.right_bumper;
        return out;
    }
    boolean lateLB2 = false;
    public boolean getLB2() {
        boolean out;
        out = gamepad2.left_bumper && !lateLB2;
        lateLB2 = gamepad2.left_bumper;
        return out;
    }

    boolean lateLSB2 = false;
    public boolean getLSB2() {
        boolean out;
        out = gamepad2.left_stick_button && !lateLSB2;
        lateLSB2 = gamepad2.left_stick_button;
        return out;
    }

    boolean lateRSB2 = false;
    public boolean getRSB2() {
        boolean out;
        out = gamepad2.right_stick_button && !lateRSB2;
        lateRSB2 = gamepad2.right_stick_button;
        return out;
    }



//    public double toggleHang(){
//        return filterJoystick(gamepad2.right_trigger);
//    }

}
