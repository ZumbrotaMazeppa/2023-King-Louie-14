// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.DriverStation;
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

  double speed = 0;
  double oldDesired = 0;
  double currentThrottle = 0;
  double step = 0;

  public DriveTrain() {
    m_right.setInverted(true);
  }

  public void resetEncoders() {
    RelativeEncoder encoder = m_rearLeft.getEncoder();
    encoder.setPosition(0);

    encoder = m_rearRight.getEncoder();
    encoder.setPosition(0);

    encoder = m_frontRight.getEncoder();
    encoder.setPosition(0);

    encoder = m_frontLeft.getEncoder();
    encoder.setPosition(0);
  }

  public void printEncoders(){
    RelativeEncoder encoder = m_rearLeft.getEncoder();
    SmartDashboard.putNumber("Rear Left", encoder.getPosition());

    encoder = m_rearRight.getEncoder();
    SmartDashboard.putNumber("Rear Right", encoder.getPosition());

    encoder = m_frontRight.getEncoder();
    SmartDashboard.putNumber("Front Right", encoder.getPosition());

    encoder = m_frontLeft.getEncoder();
    SmartDashboard.putNumber("Front Left", encoder.getPosition());
  }

  public void initDrive() {
    speed = 0;
    oldDesired = 0;
    currentThrottle = 0;
    step = 0;

    resetEncoders();
  }

  // We would like use the Logitech controller for driving
  public void driveWithJoystick(Joystick joystick) {
    if (joystick.getY() > 0.1 || joystick.getY() < -0.1) {
      speed = joystick.getY();
    } else {
      speed = speed * .99;
    }

    m_drive.arcadeDrive(-joystick.getY() * 0.5, joystick.getTwist() * 0.4);
  }

  public void autonInit() {
    initDrive();
  }

  public void TurnWithDegrees(double numberofdegrees) {
    autonInit();
    m_drive.arcadeDrive(0, .4);

    RelativeEncoder encoder = m_rearLeft.getEncoder();
    encoder.getPosition();

    encoder = m_rearRight.getEncoder();
    encoder.getPosition();

    encoder = m_frontRight.getEncoder();
    encoder.getPosition();

    encoder = m_frontLeft.getEncoder();
    encoder.getPosition();

    double turnSpeed = 0.4;
    if (numberofdegrees < 0) {
      turnSpeed = -turnSpeed;
    }

    while (Math.abs(encoder.getPosition() * 2.25) < Math.abs(numberofdegrees)) {
      m_drive.arcadeDrive(0, turnSpeed);
    }

    m_drive.arcadeDrive(0, 0);

  }

  public void DrivewithFeet(double numberoffeet) {
    autonInit();
    m_drive.arcadeDrive(0.5, 0);

    RelativeEncoder encoder = m_rearLeft.getEncoder();
    encoder.getPosition();

    encoder = m_rearRight.getEncoder();
    encoder.getPosition();

    encoder = m_frontRight.getEncoder();
    encoder.getPosition();

    encoder = m_frontLeft.getEncoder();
    encoder.getPosition();

    double movementspeed = 0.5;
    if (numberoffeet < 0) {
      movementspeed = -movementspeed;
    }

    while (Math.abs(encoder.getPosition()) < Math.abs(numberoffeet)) {
      m_drive.arcadeDrive(movementspeed, 0);
    }

    m_drive.arcadeDrive(0, 0);

  }

  public void driveAuton() {

    DriverStation.getAlliance(); // Red, Blue, or Invalid
    DriverStation.getLocation(); // 1, 2, or 3

    resetEncoders();
    printEncoders();

    try {
      Thread.sleep(5000);
    } catch (Exception e) {}

    driveAndTurnWithAuton(0, 0.4, 1000);
    printEncoders();


    /*
     * if (DriverStation.getAlliance()== DriverStation.Alliance.Red &&
     * DriverStation.getLocation()== 2) {
     * TurnWithDegrees(180);
     * DrivewithFeet(1);
     * }
     */

    // We can use the DriverStation class to find out what Alliance and Station
    // we are at by the Field Management System (FMS). We can then decide what
    // action we want to take based on our location.

    // If we are at station 2, then the robot should go backwards, drop off
    // cargo and then drive forward. Easy. If at station 1 or 3, the robot
    // needs to drive left or right to get to chariging station.

    // We should also use our encoders here to get a consistent distance

    // One concern: is driving on the charging station consistent?
    // What happens if our wheels slip?
  }

  public void driveAndTurnWithAuton(double speed, double turn, long drivetime) {
    m_drive.arcadeDrive(speed, turn);
    long targettime = System.currentTimeMillis() + drivetime;
    while (System.currentTimeMillis() < targettime) {
      m_drive.arcadeDrive(speed, turn);
    }
    m_drive.arcadeDrive(0, 0);
  }

  public void driveTest(Joystick joystick) {

    // Old method (robot will mirror joystick position exactly)
    // Prone to tipping, but very responsive
    if (joystick.getY() > 0.1 || joystick.getY() < -0.1) {
      speed = joystick.getY();
    } else {
      speed = speed * .99;
    }

    // New method (robot will step to the joystick position)
    // Allows smoother transitions to between speeds at cost of responsiveness
    double desiredThrottle = joystick.getY();

    if (oldDesired != desiredThrottle) {
      // Recalc diff/error
      oldDesired = desiredThrottle;
      double diff = desiredThrottle - currentThrottle;
      step = diff / 32; // Dividing by 32 works well; 64 is too slow
    }

    if (Math.abs(desiredThrottle - currentThrottle) > 0.04
        && (currentThrottle > -1 || currentThrottle < 1)) { // Ensure currentThrottle doesn't go over max
      currentThrottle += step;
    }

    if (joystick.getThrottle() > 0) { // Put throttle lever up to use old method
      m_drive.arcadeDrive(speed * -0.5, joystick.getTwist() * -0.4);
    } else { // Put throttle lever down to use new method
      m_drive.arcadeDrive(currentThrottle * -0.5, joystick.getTwist() * -0.4);
    }
  }
}