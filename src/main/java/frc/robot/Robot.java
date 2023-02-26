// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Grippy;
import frc.robot.subsystems.Scissor;
import frc.robot.subsystems.Winch;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.cscore.UsbCamera;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.Joystick;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the
 * name of this class or
 * the package after creating this project, you must also update the
 * build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private final Joystick m_stick = new Joystick(0);
  private final XboxController m_controller = new XboxController(1);
  private final DriveTrain m_driveTrain = new DriveTrain();
  private final Winch m_winch = new Winch();
  private final Scissor m_scissor = new Scissor();
  private final Grippy m_grip = new Grippy();

  UsbCamera camera1;
  UsbCamera camera2;
  NetworkTableEntry cameraSelection;
  /**
   * This function is run when the robot is first started up and should be used
   * for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    m_driveTrain.initDrive();

    camera1 = CameraServer.startAutomaticCapture(0);
    camera2 = CameraServer.startAutomaticCapture(1);

    cameraSelection = NetworkTableInstance.getDefault().getTable("").getEntry("CameraSelection");
    camera1.setResolution(320, 240);
    camera1.setFPS(15);
    camera2.setResolution(320, 240);
    camera2.setFPS(15);
  }

  /**
   * This function is called every 20 ms, no matter the mode. Use this for items
   * like diagnostics
   * that you want ran during disabled, autonomous, teleoperated and test.
   *
   * <p>
   * This runs after the mode specific periodic functions, but before LiveWindow
   * and
   * SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {

  }

  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different
   * autonomous modes using the dashboard. The sendable chooser code works with
   * the Java
   * SmartDashboard. If you prefer the LabVIEW Dashboard, remove all of the
   * chooser code and
   * uncomment the getString line to get the auto name from the text box below the
   * Gyro
   *
   * <p>
   * You can add additional auto modes by adding additional comparisons to the
   * switch structure
   * below with additional strings. If using the SendableChooser make sure to add
   * them to the
   * chooser code above as well.
   */
  @Override
  public void autonomousInit() {
    m_driveTrain.autonInit();
    m_driveTrain.DrivewithFeet(3);
    m_winch.winchAuton(false, 7000);
    m_scissor.ScissorAuton(true, 7000);
    m_grip.objectdropauton();
    m_scissor.ScissorAuton(false, 7000);
    m_winch.winchAuton(true, 7000);
    m_driveTrain.DrivewithFeet(-3);
    m_driveTrain.TurnWithDegrees(180);
    //m_driveTrain.TurnWithDegrees(180);
    //m_driveTrain.driveAuton();
    /*
    
    
    
    m_scissor.ScissorAuton(false, 1500);
    m_winch.winchAuton(true, 1000);
    */
  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {
    // Probably want to calculate and perform movements here
  }

  /** This function is called once when teleop is enabled. */
  @Override
  public void teleopInit() {
    m_driveTrain.initDrive();
  }

  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() {
    m_driveTrain.driveTest(m_stick);
    // m_driveTrain.driveWithJoystick(m_stick);
    m_winch.WinchMovement(m_controller);
    m_grip.GrippyMovement(m_controller);
    m_scissor.ScissorMovement(m_controller);
  }

  /** This function is called once when the robot is disabled. */
  @Override
  public void disabledInit() {
  }

  /** This function is called periodically when disabled. */
  @Override
  public void disabledPeriodic() {
  }

  /** This function is called once when test mode is enabled. */
  @Override
  public void testInit() {
  }

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {
  }

  /** This function is called once when the robot is first started up. */
  @Override
  public void simulationInit() {
  }

  /** This function is called periodically whilst in simulation. */
  @Override
  public void simulationPeriodic() {
  }
}
