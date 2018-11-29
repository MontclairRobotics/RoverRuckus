package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

public class Intake {
    Servo147 intakeServo;
    ElapsedTime t;
    private final double SPEED = .5;

    public Intake(Servo147 intakeServo) {
        this.intakeServo = intakeServo;
        t = new ElapsedTime();
        t.reset();
    }

    public void teleop(boolean in, boolean out) {
        if (in) {
            intakeServo.power(SPEED);
        }
        else if (out) {
            intakeServo.power(-SPEED);
        }
        else {
            intakeServo.power(0);
        }

    }
}