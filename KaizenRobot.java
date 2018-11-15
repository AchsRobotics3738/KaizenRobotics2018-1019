package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class KaizenRobot
{
  private DcMotor FR;
  private DcMotor FL;
  private DcMotor BR;
  private DcMotor BL;
  
  private Servo Claw;
  private Servo Arm;
  private Servo Base;
  
  public KaizenRobot(HardwareMap hardwareMap)
  {
     // Initialize the DCMotors
    FR  = hardwareMap.get(DcMotor.class, "Front Right");
    BR  = hardwareMap.get(DcMotor.class, "Back Right");
    FL  = hardwareMap.get(DcMotor.class, "Front Left");
    BL  = hardwareMap.get(DcMotor.class, "Back Left");

    //Initalize the Servos
    Claw = hardwareMap.get(Servo.class, "Claw");
    Arm = hardwareMap.get(Servo.class, "Arm");
    Base = hardwareMap.get(Servo.class, "Base");
      
    // Set Default Positions of motors
    FR.setDirection(DcMotor.Direction.FORWARD);
    FL.setDirection(DcMotor.Direction.REVERSE);
    BR.setDirection(DcMotor.Direction.FORWARD);
    BL.setDirection(DcMotor.Direction.REVERSE);
    
    // Set Default Positions of servos
    Claw.setPosition(.5);
    Arm.setPosition(.5);
    Base.setPosition(.5);
  }
  
  //Methods that Return their respective servo motors from the robot
  public Servo getClaw() { return Claw; }
  
  public Servo getArm() { return Arm; }
  
  public Servo getBase() { return Base; }
  
  /*Set the right wheels to an input velocity from a joystick
  * Forward if positive
  * Reverse if negative
  */
  public void setRightVelocity(double velocity)
  {
    FR.setPower(velocity);
    BR.setPower(velocity);
  }
  
  //Set the left wheels to an input velocity from a joystick
  public void setLeftVelocity(double velocity)
  {
    FL.setPower(velocity);
    BL.setPower(velocity);
  }
  
  /*Move a selected servo based on a passed position as a float
  * Close if position > 0
  * Open if position < 0
  * Keep if position = 0
  */
  public void moveServo(Servo servo, double position)
  {
    if(position > 0)
            servo.setPosition(1);
        else if(position < 0)
            servo.setPosition(0);
        else
            servo.setPosition(.5);
  }
  
  /* Set the servo position based on a double position
  *  1 is 180 degrees
  *  0 is 0 degrees
  *  .5 is 90 degrees
  */
  public void setServoPosition(Servo servo, double position)
  {
    servo.setPosition(position);
  }
}
