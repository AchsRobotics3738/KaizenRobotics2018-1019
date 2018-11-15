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
  private DcMotor Mount;
  
  private Servo Claw;
  
  public KaizenRobot(HardwareMap hardwareMap)
  {
     // Initialize the DCMotors
    FR  = hardwareMap.get(DcMotor.class, "Front Right");
    BR  = hardwareMap.get(DcMotor.class, "Back Right");
    FL  = hardwareMap.get(DcMotor.class, "Front Left");
    BL  = hardwareMap.get(DcMotor.class, "Back Left");
    Mount = hardwareMap.get(DcMotor.class, "Mount");
    
    //Initalize the Servo
    Claw = hardwareMap.get(Servo.class, "Claw");
      
    // Set Default Positions of motors
    FR.setDirection(DcMotor.Direction.FORWARD);
    FL.setDirection(DcMotor.Direction.REVERSE);
    BR.setDirection(DcMotor.Direction.FORWARD);
    BL.setDirection(DcMotor.Direction.REVERSE);
    Mount.setDirection(DcMotor.Direction.FORWARD);
    
    // Set Default Positions of the servo
    Claw.setPosition(.5);
  }
  
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
  
  //Set the mount velocity to an input velocity
  public void setMountVelocity(double velocity)
  {
    Mount.setPower(velocity);
  }
  
  /* Set the claw position based on a double position
  *  1 is 180 degrees
  *  0 is 0 degrees
  *  .5 is 90 degrees
  */
  public void setClawPosition(double position)
  {
    Claw.setPosition(position);
  }
}
