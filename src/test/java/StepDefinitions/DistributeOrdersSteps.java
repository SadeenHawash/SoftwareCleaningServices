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

import static org.junit.Assert.assertTrue;

public class DistributeOrdersSteps {
    Functions functions = new Functions();
    DistributeOrders distributeOrders = new DistributeOrders("orders.txt","workers.txt");
    private final ArrayList<Order> waitingOrders = new ArrayList<>();
    private List<Worker> availableWorkers = new ArrayList<>();
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
        for(Order order : waitingOrders){
            for(Worker worker: availableWorkers){
                if(worker.getStatus().equals("Available")){
                    worker.updateStatus();
                    order.setStatus(Order.Status.IN_TREATMENT);
                    break;
                }
            }
        }
    }

    @Then("the status of the workers and orders should be updated")
    public void the_status_of_the_workers_and_orders_should_be_updated() {
        for(Order order:waitingOrders){
            //order.setStatus(Order.Status.IN_TREATMENT);
            assert(order.getStatus().equals(Order.Status.IN_TREATMENT));
        }
        for(Worker worker:availableWorkers){
            //worker.updateStatus();
            assertTrue(worker.updateStatus(),true);
        }
    }
    @Then("^the following changes should be made to the orders:$")
    public void the_following_changes_should_be_made_to_the_orders(List<Order> orders) {
        // Implement the logic to check that the orders have been updated according to the table passed as an argument
        for (Order order : orders) {
            assert(this.waitingOrders.get(order.getOrderId()).equals(order.getStatus()));
        }
    }

    @Then("^the following changes should be made to the workers:$")
    public void the_following_changes_should_be_made_to_the_workers(List<Worker> workers) {
        // Implement the logic to check that the workers have been updated according to the table passed as an argument
        for (Worker worker : workers) {
            assert(this.availableWorkers.get(Integer.parseInt(worker.getId())).equals(worker.getStatus()));
        }
    }
}

