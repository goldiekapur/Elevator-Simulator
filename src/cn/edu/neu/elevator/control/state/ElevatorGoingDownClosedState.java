package cn.edu.neu.elevator.control.state;

import cn.edu.neu.elevator.util.ElevatorLogger;

public class ElevatorGoingDownClosedState extends ElevatorState{

    @Override
    public void onDoorClosed() {
        ElevatorLogger.error("Door Closed", "Received an signal indicating that the door is closed.");
    }

    @Override
    public void onDoorOpen() {
        ElevatorLogger.error("Invalid Sensor Signal", "Received an invalid signal when running. Door is open unexpectedly.");
    }

    @Override
    public void onDoorBlocked() {
        ElevatorLogger.info("Invalid Operation", "Elevator is going down. Block door operation is invalid.");
    }

    @Override
    public void onFloorButtonPressed(int floor) {
        ElevatorLogger.info("Invalid Operation", "Elevator is going down. You can not go to another floor before it reaches current destination.");
    }

    @Override
    public void onOpenButtonPressed() {
        ElevatorLogger.info("Invalid Operation", "Elevator is going down. Open door operation is invalid.");
    }

    @Override
    public void onCloseButtonPressed() {
        ElevatorLogger.info("Invalid Operation", "Elevator is going down. Close door operation is invalid.");
    }

    public void onFloorReached(int current, int destination) {
        if(current == destination){
            ElevatorLogger.info("Destination Reached","Now at the destination "+destination+"F.");
            context.getElevatorMotor().goBreak();
            context.setCurrentElevatorState(context.ELEVATOR_IDLE_CLOSED_STATE);
            context.getDoorMotor().goOpen();
        } else {
            ElevatorLogger.info("Floor Reached","Now at "+current+"F and destination is "+destination+"F. Continue to go down.");
            context.getElevatorMotor().goDown();
            context.setCurrentFloor(current - 1);
        }
    }
}
