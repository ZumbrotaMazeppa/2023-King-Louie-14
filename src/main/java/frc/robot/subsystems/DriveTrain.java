// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMax.IdleMode;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class DriveTrain extends SubsystemBase {

  CANSparkMax m_rearRight = new CANSparkMax(1, MotorType.kBrushless);
  CANSparkMax m_frontRight = new CANSparkMax(2, MotorType.kBrushless);
  MotorControllerGroup m_right = new MotorControllerGroup(m_frontRight, m_rearRight);

  CANSparkMax m_rearLeft = new CANSparkMax(5, MotorType.kBrushless);
  CANSparkMax m_frontLeft = new CANSparkMax(6, MotorType.kBrushless);;
  MotorControllerGroup m_left = new MotorControllerGroup(m_frontLeft, m_rearLeft);

  DifferentialDrive m_drive = new DifferentialDrive(m_left, m_right);

  public DriveTrain() {

    m_right.setInverted(true);
  }

  double speed = 0;

  // We would like use the Logitech controller for driving
  // Use joystick for arm
  public void driveWithXController(XboxController XController) {
    if (XController.getLeftY() > 0.1 || XController.getLeftY() < -0.1) {
      speed = XController.getLeftY();
    } else {
      speed = speed * .99;
    }

    m_drive.arcadeDrive(-XController.getLeftY() * 0.5, XController.getLeftX() * 0.4);

  }
 

  //double oldDesired = 0;
  //double currentThrottle = 0;
 // double step = 0;

 // public void driveTest(XboxController xController) {

  //  if (xController.getLeftY() > 0.1 || xController.getLeftY() < -0.1) {
   //   speed = xController.getLeftY();
   // } else {
   //   speed = speed * .99;
  //  }

   // m_drive.arcadeDrive(-speed * 0.5, xController.getLeftX() * 0.4);

   // double desiredThrottle =  xController.getLeftY();
    
   // if (oldDesired != desiredThrottle) {
      // Recalc diff/error
   //   oldDesired = desiredThrottle;
   //   double diff = desiredThrottle - currentThrottle;
   //   step = diff * 1/50;
   // }

   // if (desiredThrottle - currentThrottle != 0) {
   //   currentThrottle += step;
   // }

    //SmartDashboard.putNumber("Current throttle", currentThrottle);
    //SmartDashboard.putNumber("Desired throttle", desiredThrottle);
   // SmartDashboard.putNumber("Old Desired throttle", oldDesired);
   // SmartDashboard.putNumber("Step", step);
    //m_drive.arcadeDrive(-currentThrottle * 0.5, xController.getLeftX() * 0.4);
 // }
}