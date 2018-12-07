package org.firstinspires.ftc.teamcode.OpModes.Auto.Basic;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.teamcode.Components.IntakeLift;
import org.firstinspires.ftc.teamcode.OpModes.Auto.Utils.AutoMode;

import java.util.List;

/**
 * AUTO MODE: Sampling
 *
 * MINIMUM POINT WORTH: 15 pts
 *
 * SETUP: Front facing the sample
 *
 * SUMMARY: The auto uses TF to detect from the sample,
 *  the position of the gold mineral and turns towards the gold minerals position.
 *  The robot will then drive forward.
 */

@Autonomous(name = "Sampling", group = "Basic")
public class Sampling extends AutoMode {

    //Auto specific
    private boolean sampleComplete = false;
    private enum Turn{
        LEFT(-90), RIGHT(90);

        private final int degrees;
        Turn(int degrees) {
            this.degrees = degrees;
        }
        public int getDegrees() {
            return degrees;
        }
    }
    Turn turn;

    @Override
    public void init() {
        super.init();
    }

    @Override
    public void loop() {
        switch(states){
            case START:
                telemetry.addData("Current State","Start");
                nextState(intakeLift.setAutoPos(IntakeLift.AutoPos.UP), States.VISION);
                break;

            case VISION:
                telemetry.addData("Current State", "Vision");
                if (tfod != null) {
                    tfod.activate();
                    List<Recognition> updatedRecognitions = tfod.getUpdatedRecognitions();
                    if (updatedRecognitions != null) {
                        telemetry.addData("# Object Detected", updatedRecognitions.size());
                        if (updatedRecognitions.size() == 3) {
                            int goldMineralX = -1;
                            int silverMineral1X = -1;
                            int silverMineral2X = -1;
                            for (Recognition recognition : updatedRecognitions) {
                                if (recognition.getLabel().equals(LABEL_GOLD_MINERAL)) {
                                    goldMineralX = (int) recognition.getLeft();
                                } else if (silverMineral1X == -1) {
                                    silverMineral1X = (int) recognition.getLeft();
                                } else {
                                    silverMineral2X = (int) recognition.getLeft();
                                }
                            }
                            if (goldMineralX != -1 && silverMineral1X != -1 && silverMineral2X != -1) {
                                if (goldMineralX < silverMineral1X && goldMineralX < silverMineral2X) {
                                    telemetry.addData("Gold Mineral Position", "Right");
                                    turn = Turn.RIGHT;
                                    nextState(true, States.TURN);
                                } else if (goldMineralX > silverMineral1X && goldMineralX > silverMineral2X) {
                                    telemetry.addData("Gold Mineral Position", "Left");
                                    turn = Turn.LEFT;
                                    nextState(true, States.TURN);
                                } else {
                                    telemetry.addData("Gold Mineral Position", "Center");
                                    input = 24;
                                    nextState(true, States.DRIVE);
                                }
                            }
                        }
                    }
                }
                break;

            case TURN:
                telemetry.addData("Current State", "Turn");
                if(sampleComplete){
                    nextState(driveTrain.autoTurn((int) input), States.DRIVE, 24);
                }else{
                    nextState(driveTrain.autoTurn((int) input), States.DRIVE, 24);
                }
                break;

            case DRIVE:
                telemetry.addData("Current State","Drive");
                if(sampleComplete){
                    nextState(driveTrain.autoDrive((int) input), States.ACCEPT);
                }else{
                    nextState(driveTrain.autoDrive((int) input), States.DRIVE, -turn.getDegrees());
                }
                break;

            case ACCEPT:
                telemetry.addData("STATUS", "AUTO COMPLETE");
                break;
        }

    }
}
