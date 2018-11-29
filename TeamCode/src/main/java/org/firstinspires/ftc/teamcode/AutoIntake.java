package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

public class AutoIntake extends Auto {

    AutoMode AuM;

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
                    if(AuM == AutoMode.LEFT){
                        turnLeft();
                        setDrivePower(0.5, 0.5);
                        startDrive();


                    }

        }
    }
}
