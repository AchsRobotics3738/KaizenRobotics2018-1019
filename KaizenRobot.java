package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class KaizenRobot
{
  static final double COUNTS_PER_MOTOR_REV = 1440; 
  static final double DRIVE_GEAR_REDUCTION = 2.0;
  static final double WHEEL_DIAMETER_INCHES = 3.875;
  static final double COUNTS_PER_INCH = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) / (WHEEL_DIAMETER_INCHES * 3.1415);

  private DcMotor fr;
  private DcMotor fl;
  private DcMotor br;
  private DcMotor bl;
  private DcMotor base;
  private DcMotor joint;
  
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
    fr.setDirection(DcMotor.Direction.FORWARD);
    fl.setDirection(DcMotor.Direction.REVERSE);
    br.setDirection(DcMotor.Direction.FORWARD);
    bl.setDirection(DcMotor.Direction.REVERSE);
    base.setDirection(DcMotor.Direction.FORWARD);

    // Set Default Positions of the servo
    claw.setPosition(.5);
  }
  
  //Start Encoders
  public void startEncoders()
  {
    fr.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    fl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
  }
  
  //Set the motors to run using encoders
  public void setRunUsingEncoder()
  {
    fr.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    fl.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
  }
  
  //Set the Encoders to run to position
  public void setRunToPosition()
  {
    fr.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    fl.setMode(DcMotor.RunMode.RUN_TO_POSITION);
  }
  
  //Set the target distance for the left motor via the encoder
  public void setLeftTargetDistance(double inches)
  {
    int target = fl.getCurrentPosition() + (int)(inches * COUNTS_PER_INCH);
    fl.setTargetPosition(target);
  }
  
  //Set the target distance for the right motor via the encoder
  public void setRightTargetDistance(double inches)
  {
    int target = fr.getCurrentPosition() + (int)(inches * COUNTS_PER_INCH);
    fr.setTargetPosition(target);
  }
  
  //Return weather the motors are running to a destination
  public boolean getIsBusy()
  {
    return fl.isBusy() && fr.isBusy();
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
