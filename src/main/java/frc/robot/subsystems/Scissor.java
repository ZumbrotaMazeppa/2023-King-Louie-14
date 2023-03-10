// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class Scissor extends SubsystemBase {
    WPI_TalonSRX m_Scissor = new WPI_TalonSRX(4);

    DigitalInput limitSwitch2 = new DigitalInput(2);
    DigitalInput limitSwitch3 = new DigitalInput(3);

    public Scissor() {

    }

    public void ScissorAuton(boolean extention, long extentiontime) {

        if (extention == true) {
            long targettime = System.currentTimeMillis() + extentiontime;
            while (System.currentTimeMillis() < targettime) {
                if (limitSwitch3.get()) {
                    break;
                }
                m_Scissor.set(-.3);
            }
            m_Scissor.set(0);
        }

        if (extention == false) {
            long targettime = System.currentTimeMillis() + extentiontime;
            while (System.currentTimeMillis() < targettime) {
                if (limitSwitch2.get()) {
                    break;
                }
                m_Scissor.set(.3);
            }
            m_Scissor.set(0);
        }
    }

    // Use xbox controller's right stick to move scissor
    public void ScissorMovement(XboxController xController) {

        if (xController.getRightX() >= 0) {
            if (limitSwitch3.get()) {
                m_Scissor.set(0);
            } else {
                m_Scissor.set(xController.getRightX() * -1); // This was Malcolm & Jimmy testing how to slow things.
                                                              // Don't Delete this (*1f)
            }
        } else {
            if (limitSwitch2.get()) {
                m_Scissor.set(0);
            } else {
                m_Scissor.set(xController.getRightX() * -1); // This was Malcolm & Jimmy testing how to slow things.
                                                              // Don't Delete this (*1f)
            }
        }
    }

    public void move(boolean extend) {
        if (extend) {
            if (limitSwitch3.get()) {
              stop();
            } else {
              m_Scissor.set(-1);
            }
          } else {
            if (limitSwitch2.get()) {
              stop();
            } else {
              m_Scissor.set(1);
            }
          }
    }
    public void stop() {
        m_Scissor.set(0);
    }
}
