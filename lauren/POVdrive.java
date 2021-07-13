// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {

  Talon r1 = new Talon(0);
  Talon r2 = new Talon(1);
  Talon l1 = new Talon(8);
  Talon l2 = new Talon(9);
  Talon other = new Talon(5);

  SpeedControllerGroup r = new SpeedControllerGroup(r1, r2);
  SpeedControllerGroup l = new SpeedControllerGroup(l1, l2);

  DifferentialDrive dT = new DifferentialDrive(l, r);

  Joystick driver = new Joystick(0);
  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {}

  @Override
  public void robotPeriodic() {}

  @Override
  public void autonomousInit() {}

  @Override
  public void autonomousPeriodic() {}

  @Override
  public void teleopInit() {}

  @Override
  public void teleopPeriodic() {
  
    
    System.out.println(driver.getPOV());

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
      
       
    dT.arcadeDrive(speed, rotation);
    other.set(0.5);

    
    
    



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
