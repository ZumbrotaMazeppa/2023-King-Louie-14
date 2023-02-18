// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.DoubleSolenoid;

// gripper code here

// MOTOR CONTROLLER ONLY WORKS ONE DIRECTION, NEED TO SWAP
//Ayden Changed things plz dont hurt me
public class Grippy extends SubsystemBase {
    DoubleSolenoid grippysDoubleSolenoid = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, 0, 1);

    public Grippy() {

    }

    public void autonPosition() {
        grippysDoubleSolenoid.set(Value.kForward);
    }

    public void startPosition() {
        grippysDoubleSolenoid.set(Value.kReverse);
    }

    // Use controller for Grippy
    // A for Reverse/Grab
    // B for Forward/Let Go
    public void GrippyMovement(XboxController xController) {
        if (xController.getAButton())

            grippysDoubleSolenoid.set(DoubleSolenoid.Value.kReverse);

        if (xController.getBButton()) {

            grippysDoubleSolenoid.set(DoubleSolenoid.Value.kForward);
        }
    }
}