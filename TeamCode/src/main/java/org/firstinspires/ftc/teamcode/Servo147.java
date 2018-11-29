package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

public class Servo147 {

    public static ElapsedTime t;

    public static void init(){
        t = new ElapsedTime();
        t.reset();
    }

    public static  void loop(){
        t.reset();
    }

    public static double getTimeInLoop(){
        return t.seconds();
    }

    public Servo servo;

    public Servo147(Servo s){
        servo = s;
    }

    public void increment(int amount){
        servo.setPosition(servo.getPosition() + amount);
    }

    public void power(double distancePerSecond){
        increment((int)(distancePerSecond * getTimeInLoop()));
    }
}
