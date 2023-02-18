// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
// arm code here

public class Scissor extends SubsystemBase {
    WPI_TalonSRX m_Scissor = new WPI_TalonSRX(3);
    DigitalInput limitSwitch = new DigitalInput(0);
    DigitalInput limitSwitch2 = new DigitalInput(1);

    public Scissor() {

    }

    // Use joystick for arm
    public void ScissorMovement(XboxController xController) {
        if (limitSwitch.get() || limitSwitch2.get()) { // Returns true if button is pressed
            m_Scissor.set(0);
        } else {
            m_Scissor.set(xController.getRightX() * .25f); //This was Malcolm & Jimmy testing how to slow things. Don't Delete this (*1f)
        }
    }
}
