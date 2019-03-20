package com.jhcanonn.callcenter;

/**
 * Clase que define la informacion de un cliente que llamará al CallCenter
 * @author Jair Cañon
 */
public class Client extends Person {
    
    // Ciudad desde donde llama el cliente
    private String city;
    
    /**
     * Constructor
     * @param firstName Nombres del cliente
     * @param lastName Apellidos del cliente
     * @param city Ciudad desde donde llama el cliente
     */
    public Client(String firstName, String lastName, String city) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.city = city;
    }
    
    /* Getters and Setters */
    
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
    
}
