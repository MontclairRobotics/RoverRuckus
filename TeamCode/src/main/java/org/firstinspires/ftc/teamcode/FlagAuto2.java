package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous(name = "Flag Auto", group = "Competition")
public class FlagAuto2 extends OpMode {

    DcMotor r1, r2, l1, l2;
    int state;
    final int TICKS_PER_INCH = 100;

    @Override
    public void init() {
        state = 1;
        r1 = hardwareMap.dcMotor.get("FrontRight");
        r2 = hardwareMap.dcMotor.get("BackRight");
        l1 = hardwareMap.dcMotor.get("FrontLeft");
        l2 = hardwareMap.dcMotor.get("BackLeft");
    }

    @Override
    public void loop() {
        switch (state) {
            case 1:
                startDrive(0.5);
                state++;
                break;
            case 2:
                goToPosition(10 * TICKS_PER_INCH);
                if (isAtPosition(10 * TICKS_PER_INCH)) {
                    state++;
                }

                break;
            case 3:
                startDrive(0.5);
                state++;
                break;
            case 4:
                r1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                r1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                r1.setPower(0.5);
                r2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                r2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                r2.setPower(0.5);
                l1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                l1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                l1.setPower(-0.5);
                l2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                l2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                l2.setPower(-0.5);
                goToPosition(20 * TICKS_PER_INCH);
                //fix these numbers after testing
                state++;
                break;
            case 5:
                startDrive(0);
                state++;
            case 6:
                goToPosition(10 * TICKS_PER_INCH);
                if (isAtPosition(10 * TICKS_PER_INCH)) {
                    state++;
                }
                break;
            case 7:
                goToPosition(-30 * TICKS_PER_INCH);
                if (isAtPosition(-30 * TICKS_PER_INCH)) {
                    state++;
                }
            default:
                telemetry.addData("STATUS", "AUTO MODE FINISHED");
                break;
        }
    }

    public void startDrive ( double power){
        r1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        r1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        r1.setPower(power);
        r2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        r2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        r2.setPower(power);
        l1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        l1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        l1.setPower(power);
        l2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        l2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        l2.setPower(power);
    }

    public void goToPosition ( int position){
        r1.setTargetPosition(position);
        r2.setTargetPosition(position);
        l1.setTargetPosition(position);
        l2.setTargetPosition(position);
    }


    public boolean isAtPosition ( int position){
        return (Math.abs(r1.getTargetPosition() - position) < 20);
    }
}
