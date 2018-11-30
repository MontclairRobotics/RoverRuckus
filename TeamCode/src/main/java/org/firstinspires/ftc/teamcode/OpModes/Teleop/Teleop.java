package org.firstinspires.ftc.teamcode.OpModes.Teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Components.DriveTrain;
import org.firstinspires.ftc.teamcode.Components.Gyro;
import org.firstinspires.ftc.teamcode.Components.Intake;

@TeleOp(name = "Yum", group = "test")
public class Teleop extends OpMode {

    DriveTrain driveTrain;
    Intake intake;
    Gyro gyro;

    @Override
    public void init() {
        driveTrain = new DriveTrain(this);
//        intake = new Intake(this);
//        gyro = new Gyro(this);

        driveTrain.init(true);
//        intake.init(true);
//        gyro.init(true);

        telemetry.addData("INFO", "INIT COMPLETE");

    }

    @Override
    public void loop() {
        driveTrain.run(true);
        driveTrain.getTicksBR();
        driveTrain.getTicksBL();
//        intake.run();
//        gyro.getYaw();

        updateTelemetry(telemetry);
    }

}