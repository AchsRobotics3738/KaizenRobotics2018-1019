package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.robot.Robot;
import java.util.Set;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name="Autonomous Drive", group="Kaizen Robot")
public class AutonDrive extends LinearOpMode
{
    KaizenRobot robot = null;
    private ElapsedTime runtime = new ElapsedTime();

    @Override
    public void runOpMode()
    {
        //Create Robot
        robot = new KaizenRobot(hardwareMap);
        robot.startEncoders();
        robot.setRunUsingEncoder();

    //Wait for driver to hit play
        waitForStart();
    
    //Move Claw Forward
    runtime.reset();
    while(runtime.seconds() < 1.75){robot.setBaseVelocity(20);}
    robot.setBaseVelocity(0);
    
    telemetry.addData("Status", "Running");
    
    //Move 12 inches forward over 5 seconds
        moveByValue(.6, 35, 35, 5.0);
    
    //Move Claw Backward    
    runtime.reset();
    while(runtime.seconds() < 1.67){robot.setBaseVelocity(-20);}
    robot.setBaseVelocity(0);
    }
    
    //Method to make the robot move by a designated number of inches.
    public void moveByValue(double speed, double lInch, double rInch, double duration)
    {
        // Ensure that the opmode is still active
        if (opModeIsActive()) {
            robot.setLeftTargetDistance(lInch);
            robot.setRightTargetDistance(rInch);
            robot.setRunToPosition();

            // reset the timeout time and start motion.
            runtime.reset();
            
            //multiply left by .5 to account for uneven weight
            robot.setLeftVelocity(speed);
            robot.setRightVelocity(speed);
        
        /*Ensure three things to run the code
            1.) the opmode is running
        2.) the time is under the duration
        3.) the robot is still running the opmode
        */
            while (opModeIsActive() && (runtime.seconds() < duration) && robot.getIsBusy()) {

                telemetry.addData("Inch Amount", "Left: %d, Right: %d");
                telemetry.update();
            }

            // Stop all motion;
            robot.setLeftVelocity(0);
            robot.setRightVelocity(0);
        
         //Reset Encoders for next step
            robot.setRunUsingEncoder();
        }
    }
}
