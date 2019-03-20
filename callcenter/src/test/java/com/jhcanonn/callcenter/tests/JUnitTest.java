package com.jhcanonn.callcenter.tests;

import com.jhcanonn.callcenter.Call;
import com.jhcanonn.callcenter.Client;
import com.jhcanonn.callcenter.Dispatcher;
import java.util.ArrayList;
import java.util.List;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Jair Cañon
 */
public class JUnitTest {

    private final Integer TEN_CLIENTS_CALLING = 10;
    private final Integer TWENTY_CLIENTS_CALLING = 20;

    public JUnitTest() {
    }

    @BeforeClass
    public static void beforeClass() {
        System.out.println("In before class");
    }

    @AfterClass
    public static void afterClass() {
        System.out.println("In after class");
    }

    @Test
    public void check10Calls() {
        triggerCalls(TEN_CLIENTS_CALLING);
    }
    
    @Test
    public void check20Calls() {
        triggerCalls(TWENTY_CLIENTS_CALLING);
    }
    
    private void triggerCalls(Integer callNumbers) {
        // Se instancia el objeto Dispatcher
        Dispatcher dispatcher = new Dispatcher();
        
        // Se define cuantos clientes estan llamando y se setean dichas llamadas al Dispatcher
        dispatcher.setCalls(clientsCalling(callNumbers));
        
        // Valida que se despachen todas las llamadas a los empleados
        Assert.assertTrue(dispatcher.dispatchCall());
        
        // Mientras todos los empleados no esten libres se mantiene corriendo el Test
        while (!dispatcher.checkEmployeesFree());
        
        // Si todos los empleados estan libres significa que todas las llamadas fueron atendidas
        Assert.assertTrue(true);
    }

    private List<Call> clientsCalling(Integer clientNumber) {
        List<Call> incomingCalls = new ArrayList<>();
        for (int i = 1; i <= clientNumber; i++) {
            Client client = new Client("Firstname " + i, " Lastname " + i, ", City " + i);
            incomingCalls.add(new Call(client, "Description " + i));
        }
        return incomingCalls;
    }

}
