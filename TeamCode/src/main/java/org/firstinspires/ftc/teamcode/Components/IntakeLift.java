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

    public IntakeLift(OpMode opMode){
        this.opMode = opMode;
    }

    public void init(boolean debug){
        primaryMotor = opMode.hardwareMap.dcMotor.get("PrimaryMotor");
        secondaryMotor = opMode.hardwareMap.servo.get("SecondaryMotor");

        if(debug){
            opMode.telemetry.addData("INFO","Intake Lift INIT");
        }
    }

    public void teleop(){
        primaryMotor.setPower(opMode.gamepad2.left_stick_y);
        setSpeed(secondaryMotor, opMode.gamepad2.right_stick_y);
    }

    private void setSpeed(Servo servo, double speed){
        servo.setPosition(speed/2+0.5);
    }

}
