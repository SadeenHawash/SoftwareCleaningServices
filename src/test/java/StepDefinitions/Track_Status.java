package StepDefinitions;

import org.example.Order;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.ArrayList;
import java.util.List;

public class Track_Status {

    private final List<Order> orders = new ArrayList<>();
    Order order = new Order(1);
    @Given("a list of orders with status {string}")
    public void a_list_of_orders_with_status(String status) {
        // Create a list of orders for testing
        order.setStatus(Order.Status.WAITING);
        order.setTotalPrice(320.6);
        order.setCustomerId("12029027");
        orders.add(order);

    }

    @When("an order is selected for treatment")
    public void an_order_is_selected_for_treatment() {

    }

    @When("the order status is updated to {string}")
    public void the_order_status_is_updated_to(String status) {
        order.setStatus(Order.Status.IN_TREATMENT);
    }

    @Then("the order status should reflect {string}")
    public void the_order_status_should_reflect(String status) {
        // Verify that the status of the selected order is updated


    }

    @When("an order is selected as completed")
    public void an_order_is_selected_as_completed() {
        order.setStatus(Order.Status.COMPLETE);
    }
}

