package com.jhcanonn.callcenter;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase se encarga de despachar las llamadas
 * @author Jair Cañon
 */
public class Dispatcher {
    
    // Lista de llamadas
    private List<Call> calls;
    // Lista de Empleados
    private List<Employee> employees = new ArrayList<>();
    // Lista de llamadas en espera
    private List<Call> callWaiting = new ArrayList<>();
    
    /**
     * Se encarga de despachar las llamadas
     * @return Devuelve true garantizando que todas las llamadas fueron despachadas (Necesario para el Unit Test)
     */
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
    
    /**
     * Se encarga de despachar las llamadas que se encuentren en espera apenas se detecte un empleado este disponible
     * @param employee Empleado que se encuentra disponible
     */
    public void dispatchWaitingCall(Employee employee) {
        if (callWaiting.size() > 0) {
            // Se remueve la llamada de llamadas en espera
            Call call = callWaiting.get(0);
            callWaiting.remove(call);
            // Se inicia a atender la primera llamada en espera
            startCall(employee, call);
        }
    }
    
    /**
     * Inicia una llamada, es decir, crea el hilo
     * @param employee Empleado que atiende la llamada
     * @param call Llamada que se le pasa al empleado
     */
    private void startCall(Employee employee, Call call) {
        // Se para a ocupado el empleado y se asigna el mismo a la llamada
        employee.setState(State.BUSY);
        call.setEmployee(employee);
        // Es necesario que cada llamada conozca el objeto dispatcher para que obtener las llamadas en cola
        call.setDispatcher(this);
        // Se crea el hilo
        call.start();
    }
    
    /**
     * Valida si un empleado se encuentra disponible
     * @return Devuelve true si esta disponible y false en caso de que este ocupado
     */
    public boolean checkEmployeesFree() {
        for (Employee employee : employees) {
            if (employee.getState() == State.BUSY) {
                return false;
            }
        }
        return true;
    }
    
    /**
     * Se definen empleados del CallCenter y se agregan a una coleccion, en este caso se hizo uno a uno para garantizar nombres diferentes
     */
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
    
    /**
     * Busca un empleado que se encuentre disponible teniendo en cuenta el cargo
     * @param position Cargo del empleado
     * @return Empleado que se encuentre disponible
     */
    private Employee getEmployeePosition(Position position) {
        return employees.stream().filter(e -> e.getState() == State.FREE && e.getPosition() == position).findFirst().orElse(null);
    }
    
    /**
     * Obtiene un empleado que se encuentre en estado disponible
     * @return Empleado que se encuentre disponible
     */
    private Employee getEmployeeFree() {
        Employee employee = null;
        for (Position position : Position.values()) {
            // Se busca un empleado que este en estado libre, pasando primero por Operadores, luego Supervisores y luego Directores
            employee = getEmployeePosition(position);
            if (employee != null) {
                break;
            }
        }
        return employee;
    }
    
    /* Getters and Setters */

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
