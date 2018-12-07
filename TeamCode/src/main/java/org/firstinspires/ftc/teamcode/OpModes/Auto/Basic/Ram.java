package org.firstinspires.ftc.teamcode.OpModes.Auto.Basic;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.OpModes.Auto.Utils.AutoMode;

/**
 * AUTO MODE: Ram
 *
 * MINIMUM POINT WORTH: 5 pts
 *
 * SETUP: Front facing the sample
 *
 * SUMMARY: The auto jerks in the backwards direction, then rams full speed ahead.
 * The robot has the ability to drop the marker and push the center mineral from the center,
 * if facing towards the depot. Otherwise if pointed towards the crater, the robot can go over the barrier,
 * however may get stuck on the minerals and become immobilized.
 */
@Autonomous(name = "Ram", group = "Basic")
public class Ram extends AutoMode {

    double startTime;
    int i = 1;

    @Override
    public void init() {
        super.init();

    }

    @Override
    public void loop() {
        switch(i){
            case 1:
                startTime = System.currentTimeMillis();
                i++;

            case 2:
                if (System.currentTimeMillis() > startTime+250){
                    driveTrain.ram();
                }else{
                    driveTrain.ram2();
                }

        }
    }
}
