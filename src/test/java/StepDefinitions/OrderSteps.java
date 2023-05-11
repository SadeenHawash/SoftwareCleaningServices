package StepDefinitions;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.Customer;
import org.example.Order;
import org.example.Product;
import java.util.ArrayList;
import java.util.List;

public class OrderSteps {
    Order order = new Order();
    Customer customer = new Customer();
    Product product ;
    List<Order> orders = new ArrayList<>();
    @Given("the customer is going to make an order")
    public void the_customer_is_going_to_make_an_order() {

    }
    @When("the customer enters the product information name, material, area,treatment, picture")
    public void theCustomerEntersTheProductInformationNameMaterialAreaTreatmentPicture() {
        product = new Product("Carpet", "Nylon", 40.6F, "No", " ");
        order.addProduct(product);
    }
    @Then("a new order should be registered")
    public void a_new_order_should_be_registered() {
        customer.addOrder(order);
        orders.add(order);
        //functions.customerAddOrder();
    }

}
