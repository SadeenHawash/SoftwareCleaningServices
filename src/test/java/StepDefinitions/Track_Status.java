package StepDefinitions;

import org.example.Order;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;

public class Track_Status {

    private List<Order> orders;
    private int selectedOrderId;

    @Given("a list of orders with status {string}")
    public void a_list_of_orders_with_status(String status) {
        // Create a list of orders for testing
        orders = new ArrayList<>();
        orders.add(new Order(1, 2, 10.0, 20.0, status));
        orders.add(new Order(2, 1, 5.0, 10.0, status));
        orders.add(new Order(3, 3, 15.0, 35.0, status));
        orders.add(new Order(4, 0, 0.0, 0.0, status));
    }

    @When("an order is selected for treatment")
    public void an_order_is_selected_for_treatment() {
        // Select the first order with "waiting" status
        for (Order order : orders) {
            if (order.getStatus() == Order.Status.WAITING) {
                selectedOrderId = order.getOrderId();
                break;
            }
        }
    }

    @When("the order status is updated to {string}")
    public void the_order_status_is_updated_to(String status) {
        // Update the status of the selected order
        for (Order order : orders) {
            if (order.getOrderId() == selectedOrderId) {
                order.setStatus(Order.Status.valueOf(status));
                break;
            }
        }
    }

    @Then("the order status should reflect {string}")
    public void the_order_status_should_reflect(String status) {
        // Verify that the status of the selected order is updated

        for (Order order : orders) {
            if (order.getOrderId() == selectedOrderId) {
                Assert.assertEquals(status,String.valueOf(order.getStatus()));
                break;
            }
        }
    }

    @When("an order is selected as completed")
    public void an_order_is_selected_as_completed() {
        // Select the first order with "in treatment" status
        for (Order order : orders) {
            if (order.getStatus()== Order.Status.IN_TREATMENT) {
                selectedOrderId = order.getOrderId();
                break;
            }
        }
    }
}


