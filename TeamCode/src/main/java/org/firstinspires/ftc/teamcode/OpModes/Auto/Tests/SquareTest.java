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
                states = States.DRIVE;
                input = 24;
                telemetry.addData("State", "Start");
                break;

            case DRIVE:
                states = States.TURN;
                input = 90;
                telemetry.addData("State", "Drive");
                break;

            case TURN:
                states = States.DRIVE;
                input = 24;
                telemetry.addData("State", "Turn");
                break;
        }
    }


}
