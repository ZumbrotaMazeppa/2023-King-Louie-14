// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;


// gripper code here

// MOTOR CONTROLLER ONLY WORKS ONE DIRECTION, NEED TO SWAP
public class Grippy extends SubsystemBase {
    WPI_TalonSRX m_grip = new WPI_TalonSRX(4);

    public Grippy() {
    
    }

    // Use controller for Grippy
    public void GrippyMovement(XboxController xController) {
        m_grip.set(xController.getRightY());
    }
}   