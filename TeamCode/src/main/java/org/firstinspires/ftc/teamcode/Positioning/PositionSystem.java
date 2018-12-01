package org.firstinspires.ftc.teamcode.Positioning;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.Components.DriveTrain;
import org.firstinspires.ftc.teamcode.Components.Gyro;
import org.firstinspires.ftc.teamcode.Positioning.Utils.Vector2d;

@Deprecated //INCOMPLETE AND UNSAFE!!!
public class PositionSystem {

    public enum TgtPositions{
        DEPOT(new Vector2d(0,0)), CRATER(new Vector2d(0,0));

        private final Vector2d tgtPosition;
        TgtPositions(Vector2d id) { this.tgtPosition = id; }
        public Vector2d getValue() { return tgtPosition; }
    }

    OpMode opMode;
    DriveTrain driveTrain;
    Gyro gyro;

    private final double inchPerTick = 0.0D; //TODO: GET RATIO
    private double oldSec;
    private double oldDistance;
    private double oldHeading;
    private Vector2d position;

    public PositionSystem(OpMode opMode, Gyro gyro, DriveTrain driveTrain){
        this.opMode = opMode;
        this.gyro = gyro;
        this.driveTrain = driveTrain;
    }

    public void init(boolean debug){
        if(debug){
            opMode.telemetry.addData("INFO", "Pos Sys INIT");
        }
    }

    public void updatePos(){
        double pastSec = System.currentTimeMillis()/1000.0-oldSec;
        if(pastSec > 0.25) {
            double distance = ((driveTrain.getTicksBL() * inchPerTick +
                    driveTrain.getTicksBR() * inchPerTick) / 2);

            double angle = gyro.getYaw() - oldHeading;
            double diffDistance = distance - oldDistance;
            Vector2d change = new Vector2d(diffDistance * Math.sin(angle * Math.PI / 180),
                    diffDistance * Math.cos(angle * Math.PI / 180));
            position = position.add(change);

            opMode.telemetry.addData("Current Pos X", position.getX());
            opMode.telemetry.addData("Current Pos Y", position.getY());

            oldSec = System.currentTimeMillis() / 1000.0;
            oldDistance = distance;
            oldHeading = angle;
        }
    }

    /**
     * Calculates distance from current location to tgt
     * @param tgtPos Vector2d representation of tgt location
     * @return distance in inches
     */
    public double calcDist(Vector2d tgtPos){
        return Math.sqrt(Math.pow(position.getX()-tgtPos.getX(),2)+Math.pow(position.getY()-tgtPos.getY(),2));
    }


    //TODO: FINISH
    public double calcAngle(Vector2d tgtPos){
        
        return 0;
    }

}
