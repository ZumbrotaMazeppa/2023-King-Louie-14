// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.DoubleSolenoid;

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
        if (xController.getAButton()) {
            grippysDoubleSolenoid.set(DoubleSolenoid.Value.kReverse);
        } else if (xController.getBButton()) {
            grippysDoubleSolenoid.set(DoubleSolenoid.Value.kForward);
        } else {
            grippysDoubleSolenoid.set(DoubleSolenoid.Value.kOff);
        }
    }

    public void objectdropauton() {
        grippysDoubleSolenoid.set(DoubleSolenoid.Value.kReverse);

        try {
            Thread.sleep(1500);
        } catch (Exception e) {
        }

        grippysDoubleSolenoid.set(DoubleSolenoid.Value.kForward);
    }
}