package org.firstinspires.ftc.teamcode.Components;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Intake Lift Controls:
 * Gamepad: 2
 * Joystick: Both
 */
public class IntakeLift {

    OpMode opMode;

    DcMotor primaryMotor;
    Servo secondaryMotor;

    public enum AutoPos{
        UP(0,0), DOWN(0,0); //TODO: TEST FOR VALUES

        private final int primaryMotorPos;
        private final int secondaryMotorPos;

        AutoPos(int primaryMotorPos, int secondaryMotorPos) {
            this.primaryMotorPos = primaryMotorPos;
            this.secondaryMotorPos = secondaryMotorPos;
        }

        public int getPrimaryMotorPos() {
            return primaryMotorPos;
        }

        public int getSecondaryMotorPos() {
            return secondaryMotorPos;
        }
    }

    public IntakeLift(OpMode opMode){
        this.opMode = opMode;

        primaryMotor = opMode.hardwareMap.dcMotor.get("PrimaryMotor");
        secondaryMotor = opMode.hardwareMap.servo.get("SecondaryMotor");
    }

    public void run(){
        primaryMotor.setPower(opMode.gamepad2.left_stick_y);
        setSpeed(secondaryMotor, opMode.gamepad2.right_stick_y);
    }

    public void setAutoPos(AutoPos autoPos){
        primaryMotor.setTargetPosition(autoPos.getPrimaryMotorPos());
        secondaryMotor.setPosition(autoPos.getSecondaryMotorPos());
    }

    private void setSpeed(Servo servo, double speed){
        servo.setPosition(speed/2+0.5);
    }


}
