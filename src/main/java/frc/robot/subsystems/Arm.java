// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
// arm code here

public class Arm extends SubsystemBase {
    WPI_TalonSRX m_Winch = new WPI_TalonSRX(3);
    DigitalInput limitSwitch = new DigitalInput(0);

    public Arm() {

    }

    // Use joystick for arm
    public void ArmMovement(Joystick joystick) {
        if (limitSwitch.get()) { // Returns true if button is pressed
            m_Winch.set(0);
        } else {
            m_Winch.set(joystick.getY());
        }
    }
}
