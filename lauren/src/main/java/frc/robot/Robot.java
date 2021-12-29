// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;



import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.Timer;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {

  /**
   *Instantiates the motor controller objects
   *r=right motor, l=left motor
   *given parameter is port on roborio
   */
  Talon r1 = new Talon(0);
  Talon r2 = new Talon(1);
  Talon l1 = new Talon(8);
  Talon l2 = new Talon(9);
  Talon other = new Talon(5);

  //unncessary gyro
  ADXRS450_Gyro gyro = new ADXRS450_Gyro();

  /**
   * Groups motors together
   * r = right side, l = left side
   */
  SpeedControllerGroup r = new SpeedControllerGroup(r1, r2);
  SpeedControllerGroup l = new SpeedControllerGroup(l1, l2);

  /**
   * Groups speed controllers together into single drive train object
   * easier to maneuver this way i guess
   */
  DifferentialDrive dT = new DifferentialDrive(l, r);

  /**
   * Instantites new driver controller in code so a driver can interact with the robot
   * Be sure to move the usb to port 0 through driverstation
   */
  Joystick driver = new Joystick(0);

  Timer timer = new Timer();
  
  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {}

  @Override
  public void robotPeriodic() {}

  @Override
  public void autonomousInit() { 
    timer.start();
    timer.reset();
  }

  /**
   * function runs every small-number seconds while the robot is enabled and in the autonomous mode
   * functionally makes robot run for 4 seconds forward at 80% of the full power, then makes it turn left(?) with slight forward velocity for half a second 
   */
  @Override
  public void autonomousPeriodic() {
    
    if(timer.get() < 4.0){
      dT.arcadeDrive(.8, 0);
      
    }
    else if(timer.get()<4.5){
      dT.arcadeDrive(.3, -90);
    }
  }

  @Override
  public void teleopInit() {}

  /**
   * runS periodically during teleop period
   * gets the direction of a single joystick and sets the turning and forward speed accordingly
   * actually does it twice, the first implementation being half redundant
   * also the way the driving is done makes it so the turning and movement is updated if and only if the joystick is in the forward, backwrd, left, right, or neutral positions
   * additionally has the functionality of moving the spare motor at a set speed when a button is pressed
   */
  @Override
  public void teleopPeriodic() {
  
    
    

    double speed;
    double rotation;
    if (driver.getPOV() == -1){
      speed = 0.0;
      rotation = 0.0;
    }
    else {
      speed = 0.8;
      rotation = driver.getPOV();
      }
      if(rotation == 180){
        rotation = 0;
        speed = -.8;
      }
      else if(rotation == 90){
       rotation = 1;
        speed = .8;
      }
      else if(rotation == 270){
        rotation = -1;
        speed = -.8;}
        else if (driver.getPOV() == 0){
          rotation = 0;
          speed = .8;
        }
      
    //arcade drive's rotation argument takes values from -1.0 to 1.0
    //might be better if you use trig values and multiply by the 0.8 value for the speed and rotation s.t. speed^2 + rotation^2 = 0.8^2
    dT.arcadeDrive(speed, rotation);
    
    double motorSpeed = 0.0;
    if(driver.getRawButton(1) == true){
      // the (boolean)==True is logically equivalent to (boolean) so it's redundant
      motorSpeed = .8;
    }
    other.setSpeed(motorSpeed);
    
    
    
    



  }

  @Override
  public void disabledInit() {}

  @Override
  public void disabledPeriodic() {}

  @Override
  public void testInit() {}

  @Override
  public void testPeriodic() {}
}
