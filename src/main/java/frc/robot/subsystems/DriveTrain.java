// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.CAN;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class DriveTrain extends SubsystemBase {
  
  CANSparkMaxLowLevel m_rearRight = new CANSparkMax(1, MotorType.kBrushless);
  CANSparkMaxLowLevel m_frontRight = new CANSparkMax(2, MotorType.kBrushless);
    MotorControllerGroup m_right = new MotorControllerGroup(m_frontRight, m_rearRight);

  CANSparkMaxLowLevel m_rearLeft = new CANSparkMax(5, MotorType.kBrushless);
  CANSparkMaxLowLevel m_frontLeft = new CANSparkMax(6, MotorType.kBrushless);;
    MotorControllerGroup m_left = new MotorControllerGroup(m_frontLeft, m_rearLeft);

    DifferentialDrive m_drive = new DifferentialDrive(m_left, m_right);
  
  public DriveTrain() {
  
    m_right.setInverted(true);
  }

  public void driveWithJoystick(Joystick joystick) {
    double throttle = -joystick.getThrottle();
    if (throttle < 0.5) {
        m_drive.arcadeDrive(-joystick.getY() * 0.6, joystick.getTwist() * 0.5);
    } else {
        m_drive.arcadeDrive(-joystick.getY() * 0.80, joystick.getTwist() * 0.65);
    }
  }
}

  