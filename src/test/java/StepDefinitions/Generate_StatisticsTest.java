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
    @Then("the correct total debts amount should be displayed")
    public void the_correct_total_debts_amount_should_be_displayed() {
        // Write code here that turns the phrase above into concrete actions
        //     throw new io.cucumber.java.PendingException();
    }

    @When("the total number of delivered items is calculated")
    public void the_total_number_of_delivered_items_is_calculated() {

    }



    @Then("the correct total number of delivered items should be displayed")
    public void the_correct_total_number_of_delivered_items_should_be_displayed() {

    }

    @When("the total cash collected is calculated")
    public void the_total_cash_collected_is_calculated() {


    }

    @Then("the correct total cash amount should be displayed")
    public void the_correct_total_cash_amount_should_be_displayed() {

    }



    @When("the total amount paid by customers is calculated")
    public void the_total_amount_paid_by_customers_is_calculated() {

    }

    @Then("the correct total paid amount should be displayed")
    public void the_correct_total_paid_amount_should_be_displayed() {

    }


    @When("the total amount owed by customers is calculated")
    public void the_total_amount_owed_by_customers_is_calculated() {

    }
}