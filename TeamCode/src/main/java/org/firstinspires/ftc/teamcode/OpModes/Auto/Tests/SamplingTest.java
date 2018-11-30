package org.firstinspires.ftc.teamcode.OpModes.Auto.Tests;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;

import java.util.List;

@Autonomous(name = "Sampling Test", group = "Test")
public class SamplingTest extends OpMode {

    private static final String TFOD_MODEL_ASSET = "RoverRuckus.tflite";
    private static final String LABEL_GOLD_MINERAL = "Gold Mineral";
    private static final String LABEL_SILVER_MINERAL = "Silver Mineral";

    private static final String VUFORIA_KEY = "AVhGMov/////AAAAGUk16JthIkWst4BeQ3creo+NTUF+BxVD6iSoptSHES0tn3qxxl8EoEMBtZfR9lS5zeb8wa5m+susmQEk+ELlMZvkhfCo5hwgtQVQo95VhTaduQjLatwooAcigCDfAK19KDQPw7O4/Q0p0G79ni5UlnYrw/lF1ZC2iv+41EGTjOTT8yC6wWMzzi2ugWGtIYs9Qy62b9S+Jr2/JjoqtzoaeUX7cmshji5IRmPojALj71tKJb1Gay4XcCb7fMMkO10SDaY84E66Vt0aEhgyA4VY/ASABIEEBlpDoq7N/tTSMxDfahX0xP76BXUSNEug7Y378HPg9siRGv5AQns3Y44RfPqBu6kQN1yDXb+43Zl3ZkzF";
    private VuforiaLocalizer vuforia;
    private TFObjectDetector tfod;

    @Override
    public void init() {
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters();
        parameters.vuforiaLicenseKey = VUFORIA_KEY;
        parameters.cameraDirection = VuforiaLocalizer.CameraDirection.FRONT;
        vuforia = ClassFactory.getInstance().createVuforia(parameters);
        if (ClassFactory.getInstance().canCreateTFObjectDetector()) {
            int tfodMonitorViewId = hardwareMap.appContext.getResources().getIdentifier(
                    "tfodMonitorViewId", "id", hardwareMap.appContext.getPackageName());
            TFObjectDetector.Parameters tfodParameters = new TFObjectDetector.Parameters(tfodMonitorViewId);
            tfod = ClassFactory.getInstance().createTFObjectDetector(tfodParameters, vuforia);
            tfod.loadModelFromAsset(TFOD_MODEL_ASSET, LABEL_GOLD_MINERAL, LABEL_SILVER_MINERAL);
        } else {
            telemetry.addData("Sorry!", "This device is not compatible with TFOD");
        }
    }

    int x =0;
    @Override
    public void loop() {
        switch(x){
            case 0:
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
                                    x=4;
                                } else if (goldMineralX > silverMineral1X && goldMineralX > silverMineral2X) {
                                    telemetry.addData("Gold Mineral Position", "Left");
                                    x=2;
                                } else {
                                    telemetry.addData("Gold Mineral Position", "Center");
                                    x=3;
                                }
                            }
                        }
                    }
                }
                break;

            case 2:
                telemetry.addData("Status", "Left");
                x= 3;
                break;

            case 3:
                telemetry.addData("Status","Drive Forward");
                break;

            case 4:
                telemetry.addData("Status", "Right");
                x=3;
                break;





        }

    }
}
