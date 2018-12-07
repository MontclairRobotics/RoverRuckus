package org.firstinspires.ftc.teamcode.OpModes.Teleop.Tests;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Servo;

public class ServoTester extends OpMode {

    Servo servo;

    @Override
    public void init() {
        servo = hardwareMap.servo.get("servo");
    }

    @Override
    public void loop() {
        servo.setPosition(gamepad1.left_stick_y/2+0.5);
        telemetry.addData("Pos", servo.getPosition());
    }
}
