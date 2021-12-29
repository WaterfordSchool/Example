// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;


import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.XboxController;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {

  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  Spark control = new Spark(0);
  XboxController driver = new XboxController(1);
  
  
  @Override
  public void robotInit() {
    control.set(-0.95);
  }

  @Override
  public void robotPeriodic() {}

  @Override
  public void autonomousInit() {
    
    
  }

  @Override
  public void autonomousPeriodic() {
    
  }

  @Override
  public void teleopInit() {
    
    
  }
  

  @Override
  public void teleopPeriodic() {
    if(driver.getPOV() == 90){
      control.set(0.57);
      //pink

    }
    if(driver.getPOV() == 180){
      control.set(0.75);
      //dark green 
    }
    if(driver.getPOV() == 270){
      control.set(0.67);
      //gold
    }
    if(driver.getPOV() == 0){
      control.set(0.87);
      //blue
    }
  
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
