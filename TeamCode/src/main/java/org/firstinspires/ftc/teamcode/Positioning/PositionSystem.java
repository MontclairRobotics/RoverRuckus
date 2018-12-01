package org.firstinspires.ftc.teamcode.Positioning;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.Components.DriveTrain;
import org.firstinspires.ftc.teamcode.Components.Gyro;
import org.firstinspires.ftc.teamcode.Positioning.Utils.Vector2d;

public class PositionSystem {

    public enum TgtPositions{
        DEPOT(new Vector2d(0,0)), CRATER(new Vector2d(0,0));

        private final Vector2d tgtPosition;
        TgtPositions(Vector2d id) { this.tgtPosition = id; }
        public Vector2d getVector2d() { return tgtPosition; }
    }

    OpMode opMode;
    DriveTrain driveTrain;
    Gyro gyro;

    private final double inchPerTick = 0.0D; //TODO: GET RATIO
    private double oldSec;
    private double oldDistance;
    private double oldHeading;
    private Vector2d position;

    /**
     *
     * @param opMode OpMode
     * @param gyro Gyroscope
     * @param driveTrain Drive Train
     * @param startingPosition Starting Position
     */
    public PositionSystem(OpMode opMode, Gyro gyro, DriveTrain driveTrain, Vector2d startingPosition){
        this.opMode = opMode;
        this.gyro = gyro;
        this.driveTrain = driveTrain;
        this.position = startingPosition;
    }

    /**
     * Updates current position using encoder and gyro every 0.25 seconds
     */
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
     * Calculates distance from current location to target position
     * @param tgtPos Vector2d representation of tgt location
     * @return Distance in inches
     */
    public double calcDist(Vector2d tgtPos){
        return Math.sqrt(Math.pow(position.getX()-tgtPos.getX(),2)+Math.pow(position.getY()-tgtPos.getY(),2));
    }


    /**
     * Calculates the angle to turn for driving to target position
     * @param tgtPos Vector2d representation of tgt location
     * @return Angle in degrees.
     */
    public double calcAngle(Vector2d tgtPos){
        return gyro.getYaw()-Math.abs(Math.atan((tgtPos.getY()-position.getY())/(tgtPos.getX()-position.getX())));
    }

}
