package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.robot.Robot;
import java.util.Set;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name="Robot Drive", group="Iterative Opmode")
public class RobotDriveModularized extends OpMode
{
    private ElapsedTime runtime = new ElapsedTime();
    public KaizenRobot robot = null;
    
    boolean isHeld;
    /*
     * Code to run ONCE when the driver hits INIT
     */
    @Override
    public void init() {
        robot = new KaizenRobot(hardwareMap);
        isHeld = false;
        
        telemetry.addData("Status", "Initialized");
    }

    /*
     * Code to run REPEATEDLY after the driver hits INIT, but before they hit PLAY
     */
    @Override
    public void init_loop() {
    }

    /*
     * Code to run ONCE when the driver hits PLAY
     */
    @Override
    public void start() {
        runtime.reset();
    }

    /*
     * Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
     */
    @Override
    public void loop() {
        
        //Get joystick input and control wheels with it
        double leftPower = -gamepad1.left_stick_y;
        double rightPower = -gamepad1.right_stick_y ;

        //Set power of Motors
        robot.setRightVelocity(rightPower);
        robot.setLeftVelocity(leftPower);
        
        //Set Mount Velocity based on dpad
        //Move Left if Left, Right if Right
        if(gamepad1.dpad_right)
            robot.setMountVelocity(-500);
        else if(gamepad1.dpad_left)
            robot.setMountVelocity(500);
        else
            robot.setMountVelocity(0);
        
        //Do not move claw unless it is opening
        if(isHeld)
        {
            //if left trigger close
            if(gamepad1.left_trigger)
            {
                robot.setClawPosition(1);
                isHeld = false;
            }
        }

        //Move Claw if it is not Closed
        if(!isHeld)
        {
            //if right trigger close, nothing stay
            if(gamepad1.right_trigger > 0)
            {
                robot.setClawPosition(0);
                isHeld = true;
            }
            else
                robot.setClawPosition(.5);
        }
        
        //Show time and wheel power.
        telemetry.addData("Status", "Run Time: " + runtime.toString());
        telemetry.addData("Motors", "left (%.2f), right (%.2f)", leftPower, rightPower);
    }

        
    @Override
    public void stop() {
    }

}
