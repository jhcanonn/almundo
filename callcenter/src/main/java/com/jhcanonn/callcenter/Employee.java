package com.jhcanonn.callcenter;

/**
 * Clase que define la informacion de un empleado del CallCenter
 * @author Jair Cañon
 */
public class Employee extends Person {
    
    // Cargo del empleado
    private Position position;
    // Estado del empleado
    private State state;
    
    /**
     * Contructor
     * @param firstName Nombres del empleado
     * @param lastName Apellidos del empleado
     * @param position Cargo del empleado
     * @param state Estado del empleado
     */
    public Employee(String firstName, String lastName, Position position, State state) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.position = position;
        this.state = state;
    }
    
    /* Getters and Setters */

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }
    
}
