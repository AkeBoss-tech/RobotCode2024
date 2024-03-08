package frc.robot.subsystems.intake;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDSubsystem;
import frc.robot.Constants;

public class IntakeMotorSubsystem extends PIDSubsystem {
    private final CANSparkMax m_motorTop = new CANSparkMax(Constants.intakeConstants.TOP_INTAKE_MOTOR_CAN_ID, MotorType.kBrushless);
    private final CANSparkMax m_motorBottom = new CANSparkMax(Constants.intakeConstants.BOTTOM_INTAKE_MOTOR_CAN_ID, MotorType.kBrushless);


    public IntakeMotorSubsystem() {
        super(new PIDController(Constants.intakeConstants.kP,
            Constants.intakeConstants.kI,
            Constants.intakeConstants.kD)
        );
        m_motorTop.setInverted(true);
        m_motorBottom.setInverted(true);
    }
    public void spinMotor() {
        this.setSetpoint(200);
    }

    public void stopMotor() {
        this.setSetpoint(0);
   }

   @Override
   public double getMeasurement(){
    return this.m_motorTop.getEncoder().getVelocity();
   }

   @Override
   public void useOutput(double output, double setpoint)
   {
    m_motorTop.set(output);
    m_motorBottom.set(output);
   }

}
