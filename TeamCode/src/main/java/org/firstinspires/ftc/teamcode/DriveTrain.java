package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;


public class DriveTrain {

    DcMotor fr, fl, br, bl;
    Gamepad gamepad;
    public DriveTrain(DcMotor r1, DcMotor l1, DcMotor r2, DcMotor l2, Gamepad gamepad) {
        fr = r1;
        fl = l1;
        br = r2;
        bl = l2;
        this.gamepad = gamepad;
    }

    public void teleOp() {
        double leftPower;
        double rightPower;

        leftPower = gamepad.left_stick_x + gamepad.left_stick_y;
        rightPower = gamepad.left_stick_y - gamepad.left_stick_x;

        fr.setPower(rightPower);
        fl.setPower(leftPower);
        br.setPower(rightPower);
        bl.setPower(leftPower);

    }

}