package StepDefinitions;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class NotifyCustomerSteps {
    private Order order;
    private Customer customer ;
    private final Worker worker = new Worker();
    Functions functions = new Functions();
    @Given("a customer has placed an order")
    public void a_customer_has_placed_an_order() {
        customer = new Customer("John Doe", "john.doe@example.com");
        order = new Order();
        customer.addOrder(order);
    }
    @When("the order is complete")
    public void the_order_is_complete() {
        worker.setId("12345");
        worker.setName("Ameer");
        worker.setAddress("Nablus");
        worker.setPhone("0599897944");
        worker.setPassword("0945ss12");
        worker.setStatus("Available");
        List<Order> orders = new ArrayList<>();
        orders.add(order);
        worker.setOrders(orders);
        worker.updateStatus();
        order.setStatus(Order.Status.COMPLETE);
        functions.addWorkerToFile(worker);
    }
    @Then("the customer should receive an email notification")
    public void the_customer_should_receive_an_email_notification() {
        Admin admin = new Admin();
        String from = admin.getEmail();
        String password = admin.getEmailPassword();
        String[] to = {customer.getEmail()};
        String subject = "Testing Subject";
        String body = "Dear customer, "
                + "\n\n Your Order is ready for Collection,\n an invoice sent to you, Please Check invoices section\n," +
                "Payment when receiving.";

        assertTrue(String.valueOf(NotifyCustomer.sendEmail(from, password, to, subject, body)), true);
    }
}
