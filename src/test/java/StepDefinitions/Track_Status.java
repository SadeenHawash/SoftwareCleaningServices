package StepDefinitions;

import org.example.Order;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.util.List;

public class Track_Status {

    private List<Order> orders;


    @Given("a list of orders with status {string}")
    public void a_list_of_orders_with_status(String status) {
        // Create a list of orders for testing

    }

    @When("an order is selected for treatment")
    public void an_order_is_selected_for_treatment() {

    }

    @When("the order status is updated to {string}")
    public void the_order_status_is_updated_to(String status) {
        // Update the status of the selected order

    }

    @Then("the order status should reflect {string}")
    public void the_order_status_should_reflect(String status) {
        // Verify that the status of the selected order is updated


    }

    @When("an order is selected as completed")
    public void an_order_is_selected_as_completed() {

    }
}

