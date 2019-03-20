package com.jhcanonn.callcenter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Contiene toda la informacion de una llamada
 * @author Jair Cañon
 */
public class Call {
    // Constantes para definir el rango minimo y maximo de duracion de una llamada
    private final Integer MIN_SECS = 5;
    private final Integer MAX_SECS = 10;
    
    // Descripcion de una llamada
    private String description;
    // Manipula la fecha en que se inicia y finaliza llamada
    private Date date;
    // Duracion de una llamada
    private Integer duration;
    // Contador de segundos de una llamada
    private Integer counterSec;
    // Cliente que realiza una llamada
    private Client client;
    // Empleado al que se le asigna una llamada
    private Employee employee;
    // Es necesario que la llamada conozca el dispatche para obtener las llamadas que estan en cola
    private Dispatcher dispatcher;
    
    // Objeto timer necesario para brindar conteo cada segundo de una llamada
    private Timer timer = new Timer();
    // Objeto task necesario para configurar el trabajo que realizará la llamada
    private final TimerTask task = new TimerTask() {
        @Override
        public void run() {
            counterSec++;
            // Se cuenta cada segundo hasta la duracion de la llamada
            if (counterSec > duration) {
                // Cuando finalice la llamada se para a estado libre el empleado y se mata el hilo
                employee.setState(State.FREE);
                logger("End");
                timer.cancel();
                timer.purge();
                // Si existen llamadas en cola (esperando), entonces se toma otra llamada apenas este libre dicho empleado
                dispatcher.dispatchWaitingCall(employee);
            }
        }
    };
    
    /**
     * Constructor
     * @param client Cliente que realiza la llamada
     * @param description Descripcion de la llamada
     */
    public Call(Client client, String description) {
        counterSec = 0;
        duration = getDuration();
        this.client = client;
        this.description = description;
    }
    
    /**
     * Se encarga de darle inici a una llamada (Genera el hilo)
     */
    public void start() {
        logger("Start");
        timer.scheduleAtFixedRate(task, date, 1000);
    }
    
    /**
     * Se encarga de brindar el log de una llamada
     * @param str Texto para indicar cuando Inicia o Finaliza una llamada
     */
    private void logger(String str) {
        date = new Date();
        String msg = str + " Date: " + new SimpleDateFormat("yyyy-MM-dd h:mm:ss a").format(date)
                + ", Duration: " + duration
                + ", Client: " + client.getFullName()
                + ", City: " + client.getCity()
                + ", Description Call: " + description
                + ", Employee: " + employee.getFullName()
                + ", Position: " + employee.getPosition();
        System.out.println(msg);
    }
    
    /**
     * Obtiene la duracion de una llamada entre 5 y 10 segundos
     * @return Duracion de la llamada
     */
    public Integer getDuration() {
        Random rand = new Random();
        return rand.nextInt((MAX_SECS - MIN_SECS) + 1) + MIN_SECS;
    }
    
    /* Getters and Setters */

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Dispatcher getDispatcher() {
        return dispatcher;
    }

    public void setDispatcher(Dispatcher dispatcher) {
        this.dispatcher = dispatcher;
    }

}
