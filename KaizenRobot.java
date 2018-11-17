package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class KaizenRobot
{
  private DcMotor right;
  private DcMotor left;
  private DcMotor mount;
  private DcMotor joint;
  
  private Servo claw;
  
  public KaizenRobot(HardwareMap hardwareMap)
  {
     // Initialize the DCMotors
    right  = hardwareMap.get(DcMotor.class, "Right");
    left  = hardwareMap.get(DcMotor.class, "Left");
    mount = hardwareMap.get(DcMotor.class, "Mount");
    joint = hardwareMap.get(DcMotor.class, "Joint");

    //Initalize the Servo
    claw = hardwareMap.get(Servo.class, "Claw");
      
    // Set Default Positions of motors
    right.setDirection(DcMotor.Direction.FORWARD);
    left.setDirection(DcMotor.Direction.REVERSE);
    mount.setDirection(DcMotor.Direction.FORWARD);
    join.setDirection(DcMotor.Direction.FORWARD);

    // Set Default Positions of the servo
    claw.setPosition(.5);
  }

  /*Set the right wheels to an input velocity from a joystick
  * Forward if positive
  * Reverse if negative
  */
  public void setRightVelocity(double velocity)
  {
    right.setPower(velocity);
  }
  
  //Set the left wheels to an input velocity from a joystick
  public void setLeftVelocity(double velocity)
  {
    left.setPower(velocity);
  }
  
  //Turn the robot in place by having one motor move forward and the other back
  public void turn(double velocity, boolean isRight)
  {
    if(ifRight)
    {
      setRightVelocity(-velocity);
      setLeftVelocity(velocity);
    }
    else
    {
      setRightVelocity(velocity);
      setLeftVelocity(-velocity);
    }
  }

  //Set the joint velocity to an input velocity
  public void setJointVelocity(double velocity)
  {
    joint.setPower(velocity);
  }

  //Set the mount velocity to an input velocity
  public void setMountVelocity(double velocity)
  {
    mount.setPower(velocity);
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

  //Move the bot foreward a sent in amount of rotations
  public void moveForeward(int rotations)
  {

  }

  //Turn the robot a sent in angle
  public void turn(int angle)
  {

  }
}
