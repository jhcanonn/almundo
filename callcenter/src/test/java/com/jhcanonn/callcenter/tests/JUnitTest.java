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
 * Se encarga de realizar las pruebas unitarias de 2 escenarios:
 * 1. check10Calls -> Realiza la prueba de 10 llamadas concurrentes
 * 2. check20Calls -> Realiza la prueba de 20 llamadas concurrentes para simular el caso en que quedan en cola
 * @author Jair Cañon
 */
public class JUnitTest {
    
    // Constantes para definir el numero de clientes que llaman
    private final Integer TEN_CLIENTS_CALLING = 10;
    private final Integer TWENTY_CLIENTS_CALLING = 20;
    
    /**
     * Contructor
     */
    public JUnitTest() {
    }

    @BeforeClass
    public static void beforeClass() {
        System.out.println("\n" + " *********** IN BEFORE CLASS *********** ");
    }

    @AfterClass
    public static void afterClass() {
        System.out.println("\n" + " *********** IN AFTER CLASS *********** " + "\n");
    }
    
    @Test
    /**
     * Primer escenario en el que ingresan 10 llamadas concurrentes
     */
    public void check10Calls() {
        System.out.println("\n" + "CHECK 10 CALLS" + "\n");
        triggerCalls(TEN_CLIENTS_CALLING);
    }
    
    @Test
    /**
     * Segundo escenario en el que ingresan 20 llamadas concurrentes
     */
    public void check20Calls() {
        System.out.println("\n" + "CHECK 10 CALLS WITH WAITING CALLS" + "\n");
        triggerCalls(TWENTY_CLIENTS_CALLING);
    }
    
    /**
     * Dispara las llamadas pasandole el numero de llamadas a atender por el CallCenter
     * @param callNumbers Numero de llamadas
     */
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
    
    /**
     * Se encarga de ofrecer la simulacion de los clientes que estan llamado, crando una Lista de llamadas cada una con su respectivo Cliente
     * @param clientNumber Numero de clientes que van a llamar al CallCenter
     * @return Lista de llamadas
     */
    private List<Call> clientsCalling(Integer clientNumber) {
        List<Call> incomingCalls = new ArrayList<>();
        for (int i = 1; i <= clientNumber; i++) {
            // Se crea el objeto cliente con informacion muy simple para evitar hacerlo 1 a 1
            Client client = new Client("Firstname " + i, " Lastname " + i, ", City " + i);
            // Se agrega el cliente a la llamada y se defiene la descripcion de la misma
            incomingCalls.add(new Call(client, "Description " + i));
        }
        return incomingCalls;
    }

}
