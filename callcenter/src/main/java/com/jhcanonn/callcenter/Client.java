package com.jhcanonn.callcenter;

/**
 *
 * @author Jair Cañon
 */
public class Client extends Person {
    
    private String city;

    public Client(String firstName, String lastName, String city) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.city = city;
    }
    
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
    
}
