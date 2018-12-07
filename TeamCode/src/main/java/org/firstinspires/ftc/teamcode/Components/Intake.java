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

    public enum AutoStates{
        INTAKE(1), OUTTAKE(-1), IDLE(0);

        private final int speed;

        AutoStates(int speed) {
            this.speed = speed;
        }

        public int getSpeed() {
            return speed;
        }

    }

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

    public void setAutoRotation(AutoStates autoStates){
        switch (autoStates){
            case INTAKE:
                setSpeed(motor, AutoStates.INTAKE.getSpeed());
                break;

            case OUTTAKE:
                setSpeed(motor, AutoStates.OUTTAKE.getSpeed());
                break;

            case IDLE:
                setSpeed(motor, AutoStates.IDLE.getSpeed());
                break;
        }

    }

    private void setSpeed(Servo servo, double speed){
        servo.setPosition(speed/2+0.5);
    }
}
