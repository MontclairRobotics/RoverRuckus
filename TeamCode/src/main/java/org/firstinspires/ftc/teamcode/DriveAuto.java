package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name = "Test Drive")
public class DriveAuto extends Auto {
    public final int DRIVE_DIST_1 = 30;
    public final int DRIVE_DIST_2 = 30;
    public final int DISTANCE_PER_TICK = 1;

    @Override
    public void userInit() {

    }

    @Override
    public void userLoop() {
        switch (state){
            case 0:
                calcNextState(startDrive());
                break;
            case 1:
                setDrivePower(.5, .5);
                calcNextState(drive(DRIVE_DIST_1));
                break;
            case 2:
                calcNextState(stopDrive());
                break;
            case 3:
                calcNextState(startDrive());
                break;
            case 4:
                calcNextState(turn(45));
                break;
            case 5:
                calcNextState(stopDrive());
                break;
            case 6:
                calcNextState(startDrive());
                break;
            case 7:
                calcNextState(drive(-DRIVE_DIST_2));
                break;
            case 8:
                calcNextState(stopDrive());
                break;
        }
    }
}





