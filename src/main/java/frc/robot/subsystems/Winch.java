// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
// arm code here

public class Winch extends SubsystemBase {
    WPI_TalonSRX m_Winch = new WPI_TalonSRX(4);
    DigitalInput limitSwitch = new DigitalInput(0);
    DigitalInput limitSwitch2 = new DigitalInput(1);

    public Winch() {

    }

    // Use joystick for arm
    public void WinchMovement(XboxController xController) {
        if (limitSwitch.get() || limitSwitch2.get()) { // Returns true if button is pressed
            m_Winch.set(0);
        } else {
            m_Winch.set(-xController.getLeftY() * 1.5f); //This was Malcolm & Jimmy testing how to slow things. Don't Delete this (*1f)
        }
    }
}
