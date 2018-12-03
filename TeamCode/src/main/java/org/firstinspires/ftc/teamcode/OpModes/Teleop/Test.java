package org.firstinspires.ftc.teamcode.OpModes.Teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "Test", group = "Test")
public class Test extends OpMode {

    Servo servo1;
    Servo servo2;
    Servo servo3;

    @Override
    public void init() {
        servo1 = hardwareMap.servo.get("servo1");
        servo2 = hardwareMap.servo.get("servo2");
        servo3 = hardwareMap.servo.get("servo3");

    }

    @Override
    public void loop() {
        servo1.setPosition(gamepad1.right_stick_y);
        servo2.setPosition(gamepad1.left_stick_y);
        servo3.setPosition(gamepad2.left_stick_y);

        telemetry.addData("servo1",servo1.getPosition());
        telemetry.addData("servo2",servo2.getPosition());
        telemetry.addData("servo3",servo3.getPosition());
        updateTelemetry(telemetry);
    }
}
