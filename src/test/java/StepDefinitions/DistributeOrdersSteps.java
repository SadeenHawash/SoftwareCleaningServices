package StepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.DistributeOrders;
import org.example.Functions;
import org.example.Order;
import org.example.Worker;

import java.util.ArrayList;
import java.util.List;

public class DistributeOrdersSteps {
    Functions functions = new Functions();
    DistributeOrders distributeOrders = new DistributeOrders("orders.txt","workers.txt");
    static ArrayList<Order> waitingOrders = new ArrayList<>();
    static List<Worker> availableWorkers = new ArrayList<>();
    @Given("there are waiting orders")
    public void there_are_waiting_orders() {
        functions.updateOrdersList();
        for(Order order: functions.getOrders()){
            if(order.getStatus().equals(Order.Status.WAITING)) waitingOrders.add(order);
        }

    }
    @Given("there are available workers")
    public void there_are_available_workers() {
      availableWorkers = distributeOrders.getAvailableWorkers();
    }

    @When("the orders are distributed")
    public void the_orders_are_distributed() {
         distributeOrders.distributeOrders();
    }

    @Then("the status of the workers and orders should be updated")
    public void the_status_of_the_workers_and_orders_should_be_updated() {
        for(Order order:waitingOrders){
            order.setStatus(Order.Status.IN_TREATMENT);
        }
        for(Worker worker:availableWorkers){
            worker.updateStatus();
        }
    }
}

