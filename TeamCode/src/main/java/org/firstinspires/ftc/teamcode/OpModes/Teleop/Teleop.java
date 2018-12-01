package org.firstinspires.ftc.teamcode.OpModes.Teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Components.DriveTrain;
import org.firstinspires.ftc.teamcode.Components.Gyro;
import org.firstinspires.ftc.teamcode.Components.Intake;
import org.firstinspires.ftc.teamcode.Components.IntakeLift;

@TeleOp(name = "Yum", group = "test")
public class Teleop extends OpMode {

    DriveTrain driveTrain;
    Intake intake;
    IntakeLift intakeLift;

    @Override
    public void init() {
        driveTrain = new DriveTrain(this);
        intake = new Intake(this);
        intakeLift = new IntakeLift(this);

        telemetry.addData("INFO", "INIT COMPLETE");

    }

    @Override
    public void loop() {
        driveTrain.run(true);

        intake.run();
        intakeLift.run();

        // Debug for ticks per inch
        telemetry.addData("Left Ticks", driveTrain.getTicksBL());
        telemetry.addData("Right Ticks", driveTrain.getTicksBR());

        updateTelemetry(telemetry);
    }

}