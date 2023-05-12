package StepDefinitions;

import java.util.ArrayList;
import java.util.List;

import org.example.Order;
import org.example.OrderStatistics;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.Printing;

public class Generate_StatisticsTest {
    static int totalNumberOfItems = 0;
    static double totalCache = 0.0;
    static double totalAmountPaid = 0.0;
    static double totalDebtsAmount = 0.0;
    Printing printing = new Printing();
    OrderStatistics orderStatistics = new OrderStatistics();
    private final List<Order> orders = orderStatistics.getOrders();
    private final List<Order> completedOrders = new ArrayList<>();
    @Given("a list of all completed orders")
    public void a_list_of_all_completed_orders() {
        for(Order order:orders){
            if(order.getStatus() == Order.Status.COMPLETE) completedOrders.add(order);
        }
    }
    @When("the total number of delivered items is calculated")
    public void the_total_number_of_delivered_items_is_calculated() {
        for( Order order : completedOrders ){
            totalNumberOfItems += order.getProducts().size();
        }
    }
    @Then("the correct total number of delivered items should be displayed")
    public void the_correct_total_number_of_delivered_items_should_be_displayed() {
        printing.printSomething("Total Number of Items: "+totalNumberOfItems);
    }

    @When("the total cash collected is calculated")
    public void the_total_cash_collected_is_calculated() {
        for( Order order : completedOrders ){
            totalCache += order.getCashCollected();
        }
    }
    @Then("the correct total cash amount should be displayed")
    public void the_correct_total_cash_amount_should_be_displayed() {
        printing.printSomething("Total Cache: "+totalCache);
    }
    @When("the total amount paid by customers is calculated")
    public void the_total_amount_paid_by_customers_is_calculated() {
        for( Order order : completedOrders ){
            totalAmountPaid += order.getAmountPaid();
        }
    }
    @Then("the correct total paid amount should be displayed")
    public void the_correct_total_paid_amount_should_be_displayed() {
        printing.printSomething("Total Cache: "+totalAmountPaid);
    }
    @When("the correct total debts amount is calculated")
    public void theCorrectTotalDebtsAmountIsCalculated() {
        if(!completedOrders.isEmpty()){
            for( Order order : completedOrders ){
                totalDebtsAmount += order.getTotalPrice() - totalAmountPaid;
            }
        }
    }
    @Then("the correct total debts amount should be displayed")
    public void the_correct_total_debts_amount_should_be_displayed() {
        printing.printSomething("Total Debts: "+totalDebtsAmount);
    }
}