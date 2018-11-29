package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;


public class DriveTrain {

    DcMotor fr, fl, br, bl;
    Gamepad gamepad;
    public DriveTrain(DcMotor r1, DcMotor l1, DcMotor r2, DcMotor l2) {
        fr = r1;
        fl = l1;
        br = r2;
        bl = l2;
    }

    public void setRightPower(double power){
        fr.setPower(power);
        br.setPower(power);
    }

    public void setLeftPower(double power){
        fl.setPower(power);
        bl.setPower(power);
    }

    public void teleOp(double x, double y) {

        double leftPower = x + y;
        double rightPower = y - x;

        setRightPower(rightPower);
        setLeftPower(leftPower);

    }

    public void setMode(DcMotor.RunMode mode){
        fr.setMode(mode);
        fl.setMode(mode);
        br.setMode(mode);
        bl.setMode(mode);
    }

    public void setPosition(int r, int l){
        fr.setTargetPosition(r);
        br.setTargetPosition(r);
        fl.setTargetPosition(l);
        bl.setTargetPosition(l);
    }

    public int getTicks(){
        return fr.getCurrentPosition();
    }
}