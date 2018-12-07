package org.firstinspires.ftc.teamcode.OpModes.Auto.Tests;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.OpModes.Auto.Utils.AutoMode;

@Autonomous(name = "Auto Drive Test", group = "Test")
public class AutoDriveTest extends AutoMode {

    @Override
    public void init() {
        super.init();
    }

    @Override
    public void loop() {
        super.driveTrain.autoDrive(24);
    }
}
