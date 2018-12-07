package org.firstinspires.ftc.teamcode.OpModes.Auto.Tests;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.OpModes.Auto.Utils.AutoMode;

@Autonomous(name = "Square", group = "Test")
public class SquareTest extends AutoMode {

    @Override
    public void init() {
        super.init();
    }

    @Override
    public void loop() {
        switch (states){
            case START:
                telemetry.addData("State", "Start");
                nextState(true, States.DRIVE, 24);
                break;

            case DRIVE:
                telemetry.addData("State", "Drive");
                nextState(driveTrain.autoDrive((int) input), States.TURN, 90);
                break;

            case TURN:
                telemetry.addData("State", "Turn");
                nextState(driveTrain.autoTurn((int) input), States.TURN, 24);
                break;
        }
    }


}
