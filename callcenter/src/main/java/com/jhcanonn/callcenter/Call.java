package com.jhcanonn.callcenter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author Jair Cañon
 */
public class Call {

    private final Integer MIN_SECS = 5;
    private final Integer MAX_SECS = 10;

    private String description;
    private Date date;
    private Integer duration;
    private Integer counterSec;
    private Client client;
    private Employee employee;
    private Dispatcher dispatcher;

    private Timer timer = new Timer();
    private final TimerTask task = new TimerTask() {
        @Override
        public void run() {
            counterSec++;
            if (counterSec > duration) {
                employee.setState(State.FREE);
                logger("End");
                timer.cancel();
                timer.purge();
                dispatcher.dispatchWaitingCall(employee);
            }
        }
    };

    public Call(Client client, String description) {
        counterSec = 0;
        duration = getDuration();
        this.client = client;
        this.description = description;
    }

    public void start() {
        logger("Start");
        timer.scheduleAtFixedRate(task, date, 1000);
    }

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

    public Integer getDuration() {
        Random rand = new Random();
        return rand.nextInt((MAX_SECS - MIN_SECS) + 1) + MIN_SECS;
    }

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
