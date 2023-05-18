package org.example;
import java.util.ArrayList;
import java.util.List;
import java.io.*;
import java.util.*;

import static org.example.Functions.printing;

public class DistributeOrders {
    private final List<Order> waitingOrders;
    private final List<Worker> availableWorkers;
    private final String ordersFile;
    private final String workersFile;
    public DistributeOrders(String ordersFile, String workersFile) {
        waitingOrders = new ArrayList<>();
        availableWorkers = new ArrayList<>();
        this.ordersFile = ordersFile;
        this.workersFile = workersFile;
        loadOrdersFromFile();
        loadWorkersFromFile();
        distributeOrders();
    }
    public void addWaitingOrder(Order order) {
        //clearFile("orders.txt");
        waitingOrders.add(order);
        distributeOrders();
        saveOrdersToFile();
    }
    public void addAvailableWorker(Worker worker) {
        //clearFile("workers.txt");
        Functions functions = new Functions();
        functions.workerList();
        availableWorkers.add(worker);
        distributeOrders();
        saveWorkersToFile();
    }
    public List<Worker> getAvailableWorkers() {
        return availableWorkers;
    }

    public void distributeOrders() {
        int ordersPerWorker = waitingOrders.size() / availableWorkers.size();
        int extraOrders = waitingOrders.size() % availableWorkers.size();
        int ordersDistributed = 0;

        for (Worker worker : availableWorkers) {
            List<Order> workerOrders = new ArrayList<>();
            int numOrders = ordersPerWorker;

            if (extraOrders > 0) {
                numOrders++;
                extraOrders--;
            }

            for (int i = 0; i < numOrders; i++) {
                if (ordersDistributed < waitingOrders.size()) {
                    Order order = waitingOrders.get(ordersDistributed);
                    order.assignedWorker(worker);
                    workerOrders.add(order);
                    ordersDistributed++;
                }
            }

            worker.setOrders(workerOrders);
            worker.updateStatus();
        }
    }
    private void loadOrdersFromFile() {
        try {
            File file = new File(ordersFile);
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(" , ");
                String customerId = parts[0];
                String total = parts[5];
                String status = parts[4];
                String workerId = parts[2];
                String workerName = parts[3];
                Worker worker = new Worker();
                worker.setId(workerId);
                worker.setName(workerName);
                Order order = new Order();
                order.setCustomerId(customerId);
                order.setStatus(Order.Status.valueOf(status));
                order.setTotalPrice(Double.parseDouble(total));
                order.assignedWorker(worker);
                waitingOrders.add(order);
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            printing.printSomething("Orders file not found.");
        }
    }

    private void saveOrdersToFile() {
        try(FileWriter writer = new FileWriter(ordersFile)) {

            for (Order order : waitingOrders) {
                writer.write(order.toString() + "\n");
            }

        } catch (IOException e) {
            printing.printSomething("Error saving orders to file.");
        }
    }

    private void loadWorkersFromFile() {
        try {
            File file = new File(workersFile);
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(" , ");
                int id = Integer.parseInt((parts[0]));
                String status = parts[2];
                Worker worker = new Worker(id, status);
                availableWorkers.add(worker);
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            printing.printSomething("Workers file not found.");
        }
    }

    private void saveWorkersToFile() {
        try {
            FileWriter writer = new FileWriter(workersFile);

            for (Worker worker : availableWorkers) {
                writer.write(worker.toString() + "\n");
            }

            writer.close();
        } catch (IOException e) {
            printing.printSomething("Error saving workers to file.");
        }
    }
    /*public void clearFile(String fileName) {
        try {
            FileWriter writer1 = new FileWriter(fileName);
            writer1.write("");
            writer1.close();
            printing.printSomething("File contents cleared.");
        } catch (IOException e) {
            printing.printSomething("An error occurred: " + e.getMessage());
        }
    }*/
}



