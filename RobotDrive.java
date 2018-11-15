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
        
        //Get Driver 1 Controller input and control wheels with it
        double leftPower = -gamepad1.left_stick_y;
        double rightPower = -gamepad1.right_stick_y ;
        
        //Throttle Input
        leftPower /= 1.5;
        rightPower /= 1.5;
        
        //Set power of Motors
        robot.setRightVelocity(rightPower);
        robot.setLeftVelocity(leftPower);
        
        //Move Base with Driver 2 left Stick
        robot.moveServo(robot.getBase(), gamepad2.left_stick_y);

        //Move Arm with Driver 2 right Stick
        robot.moveServo(robot.getArm(), gamepad2.right_stick_y);
        
        //Move Claw if it is not held
        if(!isHeld)
        {
            //if Driver 2 left trigger close, right trigger open, nothing stay
            if(gamepad2.left_trigger > 0)
                robot.setServoPosition(robot.getClaw(), 1);
            else if(gamepad2.right_trigger > 0)
                robot.setServoPosition(robot.getClaw(), 0);
            else
                robot.setServoPosition(robot.getClaw(), .5);
        }
        
        //Lock and unlock with Driver 2 right bumper and left
        if(gamepad2.right_bumper)
            isHeld = true;
        if(gamepad2.left_bumper)
            isHeld = false;
        
        //Show time and wheel power.
        telemetry.addData("Status", "Run Time: " + runtime.toString());
        telemetry.addData("Motors", "left (%.2f), right (%.2f)", leftPower, rightPower);
    }

        
    @Override
    public void stop() {
    }

}
