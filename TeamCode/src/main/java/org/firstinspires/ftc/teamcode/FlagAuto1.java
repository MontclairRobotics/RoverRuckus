package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;


@Autonomous(name = "Flag Auto", group = "Competition")
public class FlagAuto1 extends OpMode {

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

        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters(cameraMonitorViewId);
        parameters.vuforiaLicenseKey = "AdPKmsn/////AAABmWkB0NUOk0euovrci+aCHok2XxRc0O7fOBANd9f852xhWBK6V2cuDKmBGsfJiQka+41+zgcWhTfRx349FTDXvQUuwwIwl2cdwNa4/cNhi4IwrD3Xnh97riiKPFduEx7t9yRydZ+Gat9WsNMtVM8wpenNd6sgfhJi15C2cdPHpu6gh7RLVhu60NIyD3nNUQZWom5MgI59SP0q4YNn/J/hv/G8OVomJgw+PD6oArXGpXN6Ik7DRXwXTCOPhLshM+t43zBd+UC1IxXfpi5md7BMz4njk4fk2Pjw/er39lzb7ajXnmtQhLIS5dEHt/6wLlDb90fRLnADD+glISflL/jbivC4vjHn5Brm++Jr2CfARup0";
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
                break;
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
