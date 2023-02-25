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
    WPI_TalonSRX m_Winch = new WPI_TalonSRX(3);
    DigitalInput limitSwitch0 = new DigitalInput(0);
    DigitalInput limitSwitch1 = new DigitalInput(1);

    public Winch() {

    }

    public void winchAuton(boolean down, long lifttime){
    
    if (down == false) {
      long targettime = System.currentTimeMillis() +  lifttime;
    while(System.currentTimeMillis() < targettime) {
     m_Winch.set(1);
    }
     m_Winch.set(0);
    }

    if (down == true) {
      long targettime = System.currentTimeMillis() +  lifttime;
    while(System.currentTimeMillis() < targettime) {
     m_Winch.set(-1);
    }
     m_Winch.set(0);
    }
    }

    // Use joystick for arm
    public void WinchMovement(XboxController xController) {
      if (-xController.getLeftY() >= 0)
      {
        if (limitSwitch0.get()) { // Returns true if button is pressed
            m_Winch.set(0);
        } else {
            m_Winch.set(-xController.getLeftY() * 1.); //This was Malcolm & Jimmy testing how to slow things. Don't Delete this (*1f)
        }
      }
      else
      {
        if (limitSwitch1.get()) { // Returns true if button is pressed
            m_Winch.set(0);
        } else {
            m_Winch.set(-xController.getLeftY() * 1.); //This was Malcolm & Jimmy testing how to slow things. Don't Delete this (*1f)
        }
      }
    }
}
