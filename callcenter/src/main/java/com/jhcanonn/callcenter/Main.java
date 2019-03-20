package com.jhcanonn.callcenter;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jair Cañon
 */
public class Main {

    private static List<Client> clients = new ArrayList<>();

    public static void main(String[] args) {
        initClients();

        List<Call> calls = new ArrayList<>();
        for (int i = 0; i < clients.size(); i++) {
            calls.add(new Call(clients.get(i), "Description " + i));
        }

        Dispatcher dispatcher = new Dispatcher();
        dispatcher.setCalls(calls);
        dispatcher.dispatchCall();
    }

    public static void initClients() {
        clients.add(new Client("Aura", "Gonzales", "Pereira"));
        clients.add(new Client("Dora", "Nieto", "Manizales"));
        clients.add(new Client("Nora", "Lopez", "Circacia"));
        clients.add(new Client("Francisco", "Perea", "Armenia"));
        clients.add(new Client("James", "Estrada", "Cali"));
        clients.add(new Client("Rosio", "Pantera", "Bogotá"));
        clients.add(new Client("Aura", "Gonzales", "Pereira"));
        clients.add(new Client("Dora", "Nieto", "Manizales"));
        clients.add(new Client("Nora", "Lopez", "Circacia"));
        clients.add(new Client("Francisco", "Perea", "Armenia"));
        clients.add(new Client("James", "Estrada", "Cali"));
        clients.add(new Client("Rosio", "Pantera", "Bogotá"));
    }

}
