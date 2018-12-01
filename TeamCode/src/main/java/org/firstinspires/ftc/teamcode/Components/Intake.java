package org.firstinspires.ftc.teamcode.Components;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Intake Controls:
 * Gamepad: 2
 * Intake Button: A
 * Outtake Button: B
 */
public class Intake {

    OpMode opMode;
    Servo motor;

    private final double speed = 1;

    public Intake(OpMode opMode){
        this.opMode = opMode;

        motor = opMode.hardwareMap.servo.get("Intake");
    }

    public void run(){
        if(opMode.gamepad1.a){
            setSpeed(motor, speed);
        }else if(opMode.gamepad1.b){
            setSpeed(motor, -speed);
        }else{
            setSpeed(motor, 0);
        }
    }

    private void setSpeed(Servo servo, double speed){
        servo.setPosition(speed/2+0.5);
    }
}
