// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
// arm code here
import com.fasterxml.jackson.annotation.JsonPropertyDescription;

 // Use joystick for arm
public class Arm extends SubsystemBase{
    WPI_VictorSPX m_Winch = new WPI_VictorSPX(3);

    public void ArmMovement(Joystick joystick) {
    m_Winch.set(joystick.getY());
    }
}
