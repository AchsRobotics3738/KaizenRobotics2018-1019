package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class KaizenRobot
{
  private DcMotor fr;
  private DcMotor fl;
  private DcMotor br;
  private DcMotor bl;
  private DcMotor base;
  
  private Servo claw;
  
  public KaizenRobot(HardwareMap hardwareMap)
  {
     // Initialize the DCMotors
    fr  = hardwareMap.get(DcMotor.class, "Front Right");
    fl  = hardwareMap.get(DcMotor.class, "Front Left");
    br  = hardwareMap.get(DcMotor.class, "Back Right");
    bl  = hardwareMap.get(DcMotor.class, "Back Left");
    base = hardwareMap.get(DcMotor.class, "Base");

    //Initalize the Servo
    claw = hardwareMap.get(Servo.class, "Claw");
      
    // Set Default Positions of motors
    fr.setDirection(DcMotor.Direction.REVERSE);
    fl.setDirection(DcMotor.Direction.FORWARD);
    br.setDirection(DcMotor.Direction.REVERSE);
    bl.setDirection(DcMotor.Direction.FORWARD);
    base.setDirection(DcMotor.Direction.FORWARD);

    // Set Default Positions of the servo
    claw.setPosition(.5);
  }

  /*Set the right wheels to an input velocity from a joystick
  * Forward if positive
  * Reverse if negative
  */
  public void setRightVelocity(double velocity)
  {
    fr.setPower(velocity);
    br.setPower(velocity);
  }
  
  //Set the left wheels to an input velocity from a joystick
  public void setLeftVelocity(double velocity)
  {
    fl.setPower(velocity);
    bl.setPower(velocity);
  }
  
  //Turn the robot in place by having one motor move forward and the other back
  public void turn(double velocity)
  {
      setRightVelocity(-velocity);
      setLeftVelocity(velocity);
  }
  
  //Set the base velocity to an input velocity
  public void setBaseVelocity(double velocity)
  {
    base.setPower(velocity);
  }
  
  /* Set the claw position based on a double position
  *  1 is 180 degrees
  *  0 is 0 degrees
  *  .5 is 90 degrees
  */
  public void setClawPosition(double position)
  {
    claw.setPosition(position);
  }
}
