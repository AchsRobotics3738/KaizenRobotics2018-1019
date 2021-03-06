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

/*
 * Robot drive class, this is the class that actually operates the teleOP portion of the code.
 */
@TeleOp(name="Robot Drive", group="Iterative Opmode")
public class RobotDrive extends OpMode
{
    private ElapsedTime runtime = new ElapsedTime();
    public KaizenRobot robot = null;
    
    //handles claw logic
    boolean isHeld;
    
    //handles toggle of wheel speed
    boolean isFast = true;
    double wheelSpeed = 0.6;
    static double fastSpeed = 0.6;
    static double slowSpeed = 0.15;
    
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
    public void loop() 
    {
        //Set power of Motors with joysticks
        if(gamepad1.right_stick_y > 0.1 || gamepad1.right_stick_y < -0.1)
            robot.setRightVelocity(-gamepad1.right_stick_y * wheelSpeed);
        else
            robot.setRightVelocity(0);
        if(gamepad1.left_stick_y > 0.1 ||gamepad1.left_stick_y < -0.1)
            robot.setLeftVelocity(-gamepad1.left_stick_y * wheelSpeed); 
        else
            robot.setLeftVelocity(0);
        //Turn the Robot to the side if dpad is pressed
        //Turn Left if Left, Right if Right
        /*
        if(gamepad1.dpad_right)
            robot.turn(50.0);
        else if(gamepad1.dpad_left)
            robot.turn(-50.0);
        else
            robot.turn(0.0);
        */
        //Show time elapsed
        telemetry.addData("Status", "Run Time: " + runtime.toString());
        
        //if left trigger open
        if(gamepad1.left_trigger > 0)
        {
            isHeld = false;
            robot.setClawPosition(0);
        }
        //if right trigger close, nothing stay
        else if(gamepad1.right_trigger > 0)
        {
            isHeld = true;
            robot.setClawPosition(1);
        }
        //if claw is held and no input, close
        else if(isHeld)
            robot.setClawPosition(1);
        //if claw is not held and no input, keep
        else
            robot.setClawPosition(.5);
        
        //Move base of claw on player input
        //Down with X, up with Y
        if(gamepad1.y)
            robot.setBaseVelocity(.2); //Change this to be more or less powerful based on need
        else if(gamepad1.x)
            robot.setBaseVelocity(-.2); //Change this to be more or less powerful based on need
        else
            robot.setBaseVelocity(0.0);
        
        //handles speed toggle
        if(gamepad1.right_bumper)
            wheelSpeed = fastSpeed;
        else if(gamepad1.left_bumper)
            wheelSpeed = slowSpeed;
        
    }

    @Override
    public void stop() {
    }

}
