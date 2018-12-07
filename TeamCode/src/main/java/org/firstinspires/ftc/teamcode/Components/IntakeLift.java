package org.firstinspires.ftc.teamcode.Components;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.configuration.typecontainers.MotorConfigurationType;

/**
 * Intake Lift Controls:
 * Gamepad: 2
 * Joystick: Both
 */
public class IntakeLift {

    OpMode opMode;

    DcMotor liftLeft;
    DcMotor liftRight;

//    Servo secondaryMotor;

    public enum AutoPos{
        UP(0,0,0), DOWN(0,0,0), BACK(0,0,0), OUTTAKE(0,0,0); //TODO: TEST FOR VALUES

        private final int liftLeftMotorPos;
        private final int liftRightMotorPos;
        private final int secondaryMotorPos;

        AutoPos(int liftLeftMotorPos, int liftRightMotorPos, int secondaryMotorPos) {
            this.liftLeftMotorPos = liftLeftMotorPos;
            this.liftRightMotorPos = liftRightMotorPos;
            this.secondaryMotorPos = secondaryMotorPos;
        }

        public int getLiftLeftMotorPos() {
            return liftLeftMotorPos;
        }

        public int getLiftRightMotorPos(){
            return liftRightMotorPos;
        }

        public int getSecondaryMotorPos() {
            return secondaryMotorPos;
        }
    }

    public IntakeLift(OpMode opMode){
        this.opMode = opMode;

        liftLeft = opMode.hardwareMap.dcMotor.get("IntakeLeft");
        liftLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        liftLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        liftLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        liftRight = opMode.hardwareMap.dcMotor.get("IntakeRight");
        liftRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        liftRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

//        secondaryMotor = opMode.hardwareMap.servo.get("IntakeLift");
    }

    public void run(){
        if(Math.abs(opMode.gamepad2.left_stick_y) < 0.05 &&
                Math.abs(opMode.gamepad2.left_stick_y) >= 0){
            liftLeft.setPower(0);
            liftRight.setPower(0);
        }else{
            liftLeft.setPower(opMode.gamepad2.left_stick_y);
            liftRight.setPower(opMode.gamepad2.left_stick_y);
        }

        getPos();
    }

    public boolean setAutoPos(AutoPos autoPos){
        liftLeft.setTargetPosition(autoPos.getLiftLeftMotorPos());
        liftRight.setTargetPosition(autoPos.getLiftRightMotorPos());
//        secondaryMotor.setPosition(autoPos.getSecondaryMotorPos());
        if (liftLeft.getCurrentPosition() > autoPos.getLiftLeftMotorPos()-5 &&
                liftLeft.getCurrentPosition() < autoPos.getLiftLeftMotorPos() + 5 &&
                liftRight.getCurrentPosition() > autoPos.getLiftRightMotorPos()-5 &&
                liftRight.getCurrentPosition() < autoPos.getLiftRightMotorPos() + 5 /*&&
                secondaryMotor.getPosition() > autoPos.getSecondaryMotorPos() - 5 &&
                secondaryMotor.getPosition() < autoPos.getSecondaryMotorPos() + 5*/){
            return true;
        }else {
            return false;
        }
    }

    public void getPos(){
        opMode.telemetry.addData("Left Motor Pos", liftLeft.getCurrentPosition());
        opMode.telemetry.addData("Right Motor Pos", liftRight.getCurrentPosition());
//        opMode.telemetry.addData("Secondary Motor Pos", secondaryMotor.getPosition());
    }

    private void setSpeed(Servo servo, double speed){
        servo.setPosition(speed/2+0.5);
    }


}
