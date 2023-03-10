// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.shuffleboard.SendableCameraWrapper;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.subsystems.*;
import frc.robot.auton.*;

import java.util.ArrayList;
import java.util.List;

import org.opencv.objdetect.FaceDetectorYN;

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

  private List<Task> taskList;

  @Override
  public void autonomousInit() {
    m_driveTrain.autonInit();
    m_winch.stop();
    m_scissor.stop();

    taskList = new ArrayList<Task>();

    taskList.add(new DriveStraightTask(m_driveTrain, 3));
    taskList.add(new WaitTask(250));
    taskList.add(new PositionArmTask(m_scissor, true));
    taskList.add(new WaitTask(250));
    taskList.add(new DriveStraightTask(m_driveTrain, 9));
    
  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {
    if (taskList.size() > 0) {
      Task t = taskList.get(0);
      if (!t.isInitialized()) {
        t.init();
      } else if (!t.isComplete()) {
        t.execute();
      } else {
        t.done();
        taskList.remove(0);
      }
    }
  }

  /** This function is called once when teleop is enabled. */
  @Override
  public void teleopInit() {
    m_driveTrain.initDrive();
    m_winch.stop();
    m_scissor.stop();
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
