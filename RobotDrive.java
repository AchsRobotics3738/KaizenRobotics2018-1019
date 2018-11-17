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
        double rightPower = -gamepad1.right_stick_y;

        //Set power of Motors
        robot.setRightVelocity(rightPower);
        robot.setLeftVelocity(leftPower);
        
        //Turn the Robot to the side if dpad is pressed
        //Turn Left if Left, Right if Right
        if(gamepad1.dpad_right)
            robot.turn(500, true);
        else(gamepad1.dpad_left)
            robot.turn(500, false);
        
        //Hold Claw until it is oppened with the right trigger
        //Move Claw if it is not Closed
        if(isHeld)
        {
            //if right trigger, open
            if(gamepad1.right_trigger)
            {
                robot.setClawPosition(1);
                isHeld = false;
            }
        }
        else
        {
            //if left trigger close, nothing stay
            if(gamepad1.left_trigger)
            {
                robot.setClawPosition(0);
                isHeld = true;
            }
            else
                robot.setClawPosition(.5);
        }
        
        //Move base of claw on player input
        //Down with X, up with Y
        if(gamepad1.y)
            robot.moveBase(500);
        else if(gamepad1.x)
            robot.moveBase(-500);
        
        //Move joint of claw on player dPad input
        //Down with down Up with up
        if(gamepad1.dpad_up)
            robot.moveJoint(500);
        else if(gamepad1.dpad_down)
            robot.moveJoint(-500);
            
        //Show time elapsed and wheel power.
        telemetry.addData("Status", "Run Time: " + runtime.toString());
    }

        
    @Override
    public void stop() {
    }

}
