package com.jhcanonn.callcenter;

/**
 * Define lo mas basico de una persona
 * @author Jair Cañon
 */
public class Person {
    
    // Nombres de una persona
    protected String firstName;
    // Apellidos de una persona
    protected String lastName;
    
    /* Getters and Setters */
    
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFullName() {
        return this.firstName + " " + this.lastName;
    }
    
}
