package org.firstinspires.ftc.teamcode.OpModes.Auto.Tests;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous(name = "Ticks Test", group = "Test")
public class TicksTest extends OpMode {

    private final DcMotor[][] motors = new DcMotor[2][2];

    @Override
    public void init() {

        motors[0][0] = hardwareMap.dcMotor.get("FrontLeft");
        motors[0][1] = hardwareMap.dcMotor.get("BackLeft");
        motors[1][0] = hardwareMap.dcMotor.get("FrontRight");
        motors[1][1] = hardwareMap.dcMotor.get("BackRight");

        motors[0][0].setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motors[0][1].setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motors[1][0].setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motors[1][1].setMode(DcMotor.RunMode.RUN_TO_POSITION);

    }

    @Override
    public void loop() {
        motors[0][0].setTargetPosition(1000);
        motors[0][1].setTargetPosition(1000);
        motors[1][0].setTargetPosition(1000);
        motors[1][1].setTargetPosition(1000);
    }
}
