package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
/*

 */
@TeleOp(name = "Test Robot", group = "Test")
public class Robot extends OpMode {

    DcMotor[] leftMotors = new DcMotor[2];
    DcMotor[] rightMotors = new DcMotor[2];
    DcMotor buttonMotorA;
    DcMotor buttonMotorB;
    DcMotor liftMotor;
    DcMotor intakeMotor1;
    DcMotor intakeMotor2;


    double leftPower;
    double rightPower;

    double x = 0.7;

    @Override
    public void init() {
        leftMotors[0] = hardwareMap.dcMotor.get("FrontLeft");
        leftMotors[1] = hardwareMap.dcMotor.get("BackLeft");
        rightMotors[0] = hardwareMap.dcMotor.get("FrontRight");
        rightMotors[1] = hardwareMap.dcMotor.get("BackRight");
        buttonMotorA = hardwareMap.dcMotor.get("liftUp");
        buttonMotorB = hardwareMap.dcMotor.get("dropDown");
        liftMotor = hardwareMap.dcMotor.get("LiftDrop");
        intakeMotor1 = hardwareMap.dcMotor.get("frontIntake");
        intakeMotor2 = hardwareMap.dcMotor.get("backIntake");
        intakeMotor1 = hardwareMap.dcMotor.get("frontOuttake");
        intakeMotor2 = hardwareMap.dcMotor.get("backOuttake");
    }

    @Override
    public void loop() {
        leftPower = gamepad1.left_stick_x + gamepad1.left_stick_y;
        rightPower = gamepad1.left_stick_y - gamepad1.left_stick_x;
        rightMotors[0].setPower(rightPower);
        rightMotors[1].setPower(rightPower);
        leftMotors[0].setPower(leftPower);
        leftMotors[1].setPower(leftPower);
        liftMotor.setPower(-gamepad1.right_stick_y);

        if (gamepad1.a) {
            intakeMotor1.setPower(x);
        }
        else {
            intakeMotor1.setPower(0);
        }
        if (gamepad1.a) {
            intakeMotor2.setPower(-x);
        }
        else {
            intakeMotor2.setPower(0);
        }
        if (gamepad1.b) {
            intakeMotor1.setPower(-x);
        }
        else {
            intakeMotor1.setPower(0);
        }
        if (gamepad1.b) {
            intakeMotor2.setPower(x);
        }
        else {
            intakeMotor2.setPower(0);
        }
    }
}
