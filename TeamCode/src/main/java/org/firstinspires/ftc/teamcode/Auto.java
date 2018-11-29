package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

public abstract class Auto extends OpMode {

    DcMotor[] leftMotors = new DcMotor[2];
    DcMotor[] rightMotors = new DcMotor[2];
    DriveTrain driveTrain;
    Intake Intake;

    int state;

    double x = 0.7;
    public void setup(){
        leftMotors[0] = hardwareMap.dcMotor.get("FrontLeft");
        leftMotors[1] = hardwareMap.dcMotor.get("BackLeft");
        rightMotors[0] = hardwareMap.dcMotor.get("FrontRight");
        rightMotors[1] = hardwareMap.dcMotor.get("BackRight");

        state = 0;

        driveTrain = new DriveTrain (rightMotors[0], leftMotors[0], rightMotors[1], leftMotors[1]);
    }

    public void turnLeft(){
        driveTrain.setRightPower(0.5);
        driveTrain.setLeftPower(-0.5);
    }
    public void turnRight(){
        driveTrain.setRightPower(-0.5);
        driveTrain.setLeftPower(0.5);

    }

    public boolean setDrivePower(double rightPower, double leftPower){
        driveTrain.setRightPower(rightPower);
        driveTrain.setLeftPower(leftPower);
        return true;
    }

    public boolean startDrive(){
        driveTrain.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        driveTrain.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        return true;
    }

    public boolean drive(int ticks){
        driveTrain.setPosition(ticks, ticks);
        return driveTrain.getTicks() > ticks;
    }

    public boolean stopDrive(){
        return setDrivePower(0, 0);
    }

    public boolean turn(int ticks){
        driveTrain.setPosition(ticks, -ticks);
        return (driveTrain.getTicks() > ticks);
    }

    @Override
    public void init() {
        setup();
        userInit();
    }

    @Override
    public void loop() {
        userLoop();
    }
    public abstract void userInit();
    public abstract void userLoop();

    public void calcNextState(boolean condition){
        if(condition) state++;
    }
}