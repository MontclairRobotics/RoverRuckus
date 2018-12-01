package org.firstinspires.ftc.teamcode.Components;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Drivetrain Controls:
 * Gamepad: 1
 * Joystick: Left
 */
public class DriveTrain{

    OpMode opMode;

    private boolean driving = false;
    private boolean turning = false;
    private final double TICKS_PER_INCH = 1000/14;
    private  final double circumference = 14 * Math.sqrt(2) * Math.PI;
    private  final double degree = circumference / 360;

    private final DcMotor[][] motors = new DcMotor[2][2];

    public DriveTrain(OpMode opMode) {
        this.opMode = opMode;

        motors[0][0] = opMode.hardwareMap.dcMotor.get("FrontLeft");
        motors[0][1] = opMode.hardwareMap.dcMotor.get("BackLeft");
        motors[1][0] = opMode.hardwareMap.dcMotor.get("FrontRight");
        motors[1][1] = opMode.hardwareMap.dcMotor.get("BackRight");
    }

    public void run(boolean debug) {
        double leftPower;
        double rightPower;

        leftPower = opMode.gamepad1.left_stick_y - opMode.gamepad1.left_stick_x;
        rightPower = opMode.gamepad1.left_stick_y + opMode.gamepad1.left_stick_x;

        motors[0][0].setPower(leftPower);
        motors[0][1].setPower(leftPower);
        motors[1][0].setPower(rightPower);
        motors[1][1].setPower(rightPower);

        if(debug){
            opMode.telemetry.addData("Left Power", leftPower);
            opMode.telemetry.addData("Right Power", rightPower);
        }

    }

    // AUTO DRIVING
    private int distanceTraveled = 0;
    private int driveStartingPos = 0;
    public boolean autoDrive(int distance){
        distance = (int) (distance * TICKS_PER_INCH);
        if (!(driving)) {
            for (int i = 0; i < motors[0].length; i++) {
                driveStartingPos = motors[0][i].getCurrentPosition();
                motors[0][i].setTargetPosition(driveStartingPos + distance);
            }
            for (int i = 0; i < motors[1].length; i++) {
                driveStartingPos = motors[1][i].getCurrentPosition();
                motors[1][i].setTargetPosition((driveStartingPos - distance));
            }
            driving = true;
        }

        distanceTraveled = motors[0][0].getCurrentPosition() + driveStartingPos;

        if (Math.abs(distanceTraveled - distance) < 20) {
            driving = false;
            distanceTraveled = 0;
            driveStartingPos = 0;
        }
        return !(driving);
    }

    // AUTO TURNING
    private int motor1end = 0;
    public boolean autoTurn(int degrees) {
        if(!turning) {
            int distance = (int)(degrees * degree * TICKS_PER_INCH);
            motor1end = motors[0][0].getCurrentPosition() + distance;
            for(DcMotor[] row : motors) {
                for(DcMotor m : row) {
                    m.setTargetPosition(m.getCurrentPosition() + distance);
                }
            }
            turning = true;
        }
        if(Math.abs(motors[0][0].getCurrentPosition() - motor1end) < 20) {
            turning = false;
            return true;
        }
        return false;
    }

    //GET ENCODER TICKS
    public double getTicksFL(){
        return motors[0][0].getCurrentPosition();
    }

    public double getTicksBL(){
        return motors[0][1].getCurrentPosition();
    }

    public double getTicksFR(){
        return motors[1][0].getCurrentPosition();
    }

    public double getTicksBR(){
        return motors[1][1].getCurrentPosition();
    }
}