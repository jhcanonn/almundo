package com.jhcanonn.callcenter;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jair Cañon
 */
public class Dispatcher {

    private List<Call> calls;
    private List<Employee> employees = new ArrayList<>();
    private List<Call> callWaiting = new ArrayList<>();

    public boolean dispatchCall() {
        initEmployees();
        calls.forEach(call -> {
            Employee employee = getEmployeeFree();
            if (employee != null) {
                startCall(employee, call);
            } else {
                System.out.println(call.getClient().getFullName() + " from " + call.getClient().getCity() + " is waiting for a employee...");
                callWaiting.add(call);
            }
        });
        return true;
    }

    public void dispatchWaitingCall(Employee employee) {
        if (callWaiting.size() > 0) {
            // Se remueve la llamada de llamadas en espera
            Call call = callWaiting.get(0);
            callWaiting.remove(call);
            // Se inicia a atender la primera llamada en espera
            startCall(employee, call);
        }
    }

    private void startCall(Employee employee, Call call) {
        employee.setState(State.BUSY);
        call.setEmployee(employee);
        call.setDispatcher(this);
        call.start();
    }

    public boolean checkEmployeesFree() {
        for (Employee employee : employees) {
            if (employee.getState() == State.BUSY) {
                return false;
            }
        }
        return true;
    }

    private void initEmployees() {
        employees.add(new Employee("Diana", "Suarez", Position.OPERATOR, State.FREE));
        employees.add(new Employee("Julian", "Tellez", Position.OPERATOR, State.FREE));
        employees.add(new Employee("Andres", "Ocampo", Position.OPERATOR, State.FREE));
        employees.add(new Employee("Jorge", "Ramirez", Position.OPERATOR, State.FREE));
        employees.add(new Employee("Alejandro", "Muñoz", Position.OPERATOR, State.FREE));
        employees.add(new Employee("Rodrigo", "Pelaez", Position.OPERATOR, State.FREE));
        employees.add(new Employee("Sergio", "Rico", Position.SUPERVISOR, State.FREE));
        employees.add(new Employee("Mario", "Perez", Position.SUPERVISOR, State.FREE));
        employees.add(new Employee("Juan", "Gutierrez", Position.SUPERVISOR, State.FREE));
        employees.add(new Employee("Jair", "Cañon", Position.DIRECTOR, State.FREE));
    }

    private Employee getEmployeePosition(Position position) {
        return employees.stream().filter(e -> e.getState() == State.FREE && e.getPosition() == position).findFirst().orElse(null);
    }

    private Employee getEmployeeFree() {
        Employee employee = null;
        for (Position position : Position.values()) {
            employee = getEmployeePosition(position);
            if (employee != null) {
                break;
            }
        }
        return employee;
    }

    public List<Call> getCalls() {
        return calls;
    }

    public void setCalls(List<Call> calls) {
        this.calls = calls;
    }

    public List<Call> getCallWaiting() {
        return callWaiting;
    }

    public void setCallWaiting(List<Call> callWaiting) {
        this.callWaiting = callWaiting;
    }

}
