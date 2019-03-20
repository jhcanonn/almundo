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

    public void dispatchCall() {
        initEmployees();
        calls.forEach(call -> {
            Employee employee = getEmployeeFree();
            if (employee != null) {
                employee.setState(State.BUSY);
                call.setEmployee(employee);
                call.setDispatcher(this);
                call.start();
            } else {
                System.out.println(call.getClient().getFullName() + " from " + call.getClient().getCity() + " is waiting for a employee...");
                callWaiting.add(call);
            }
        });
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
        Employee employee = getEmployeePosition(Position.OPERATOR);
        if (employee == null) {
            employee = getEmployeePosition(Position.SUPERVISOR);
            if (employee == null) {
                employee = getEmployeePosition(Position.DIRECTOR);
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
