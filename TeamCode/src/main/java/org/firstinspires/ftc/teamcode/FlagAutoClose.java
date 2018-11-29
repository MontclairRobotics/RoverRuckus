package org.firstinspires.ftc.teamcode;

public class FlagAutoClose extends Auto{
    public final int DRIVE_DIST_1 = 1000;

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

        }
    }
}
