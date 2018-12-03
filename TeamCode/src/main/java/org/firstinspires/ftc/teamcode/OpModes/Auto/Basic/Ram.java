package org.firstinspires.ftc.teamcode.OpModes.Auto.Basic;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.OpModes.Auto.Utils.AutoMode;

@Autonomous(name = "Ram", group = "test")
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
