package com.jhcanonn.callcenter;

/**
 * Contiene todos los atributos comunes en un empleado
 * @author Jair Cañon
 */
public class Employee extends Person {
    
    private Position position;
    private State state;

    public Employee(String firstName, String lastName, Position position, State state) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.position = position;
        this.state = state;
    }

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
