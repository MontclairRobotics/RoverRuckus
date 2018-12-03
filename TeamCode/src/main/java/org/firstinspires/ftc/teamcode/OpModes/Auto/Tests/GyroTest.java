package org.firstinspires.ftc.teamcode.OpModes.Auto.Tests;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.Components.Gyro;
import org.firstinspires.ftc.teamcode.OpModes.Auto.Utils.AutoMode;

//@Autonomous(name = "Gyro Test", group = "Test")
public class GyroTest extends AutoMode {

    Gyro gyro;

    @Override
    public void init() {
        super.init();
        gyro = new Gyro(this);
    }

    @Override
    public void loop() {
        gyro.getYaw();
    }
}
