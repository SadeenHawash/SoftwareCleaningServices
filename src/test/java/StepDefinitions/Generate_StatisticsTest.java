package StepDefinitions;

import java.util.ArrayList;
import java.util.List;

import org.example.Order;
import org.junit.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Generate_StatisticsTest {

    private List<Order> orders;
    int totalItemsDelivered ;
    double totalCashCollected ;
    double totalAmountPaid ;
    @Before
    public void setUp() {
        orders = new ArrayList<>();
        String status = " ";
        orders.add(new Order(1, 2, 10.0, 20.0, status));
        orders.add(new Order(2, 1, 5.0, 10.0, status));
        orders.add(new Order(3, 3, 15.0, 35.0, status));
        orders.add(new Order(4, 0, 0.0, 0.0, status));
    }

    @Given("a list of all completed orders")
    public void a_list_of_all_completed_orders() {
        // This step definition does not require any action
    }

    @When("the total number of delivered items is calculated")
    public void the_total_number_of_delivered_items_is_calculated() {
        totalItemsDelivered = 0;
        for (Order order : orders) {
            totalItemsDelivered += order.getItemsDelivered();
        }
        // Store the result for later assertion
        //getContext().set("totalItemsDelivered", totalItemsDelivered);
    }

    @Then("the correct total number of delivered items should be displayed")
    public void the_correct_total_number_of_delivered_items_should_be_displayed() {

    }

    @When("the total cash collected is calculated")
    public void the_total_cash_collected_is_calculated() {
        totalCashCollected = 0.0;
        for (Order order : orders) {
            totalCashCollected += order.getCashCollected();
        }
        // Store the result for later assertion
        //getContext().set("totalCashCollected", totalCashCollected);
    }

    @Then("the correct total cash amount should be displayed")
    public void the_correct_total_cash_amount_should_be_displayed() {

    }

    @When("the total amount paid by customers is calculated")
    public void the_total_amount_paid_by_customers_is_calculated() {
        totalAmountPaid = 0;
        for (Order order : orders) {
            totalAmountPaid += order.getAmountPaid();
        }

    }

    @Then("the correct total paid amount should be displayed")
    public void the_correct_total_paid_amount_should_be_displayed() {

    }


    @When("the total amount owed by customers is calculated")
    public void the_total_amount_owed_by_customers_is_calculated() {

    }
}
// Retrieve the stored result and assert it
//int totalItemsDelivered = getContext().get("totalItemsDelivered");
// assertEquals(6, totalItemsDelivered);

// Retrieve the stored result and assert it
// double totalCashCollected = getContext().get("totalCashCollected");
//assertEquals(65.0, totalCashCollected, 0.001);

// Store the result for later assertion
// getContext().set("totalAmountPaid", totalAmountPaid);

// Retrieve the stored result and assert it
//double totalAmountPaid = getContext().get("totalAmountPaid");
//assertEquals(65.0, totalAmountPaid, 0.001);

//double totalAmountOwed = 0.0;
// Store the result for later assertion
// getContext().set("totalAmountOwed", totalAmountOwed);
