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
    Order order = new Order(8);
    Customer customer = new Customer();
    Product product ;
    List<Order> orders = new ArrayList<>();
    @Given("the customer is going to make an order")
    public void the_customer_is_going_to_make_an_order() {
        customer.setId("12029026");
        customer.setName("Ahmad");
        customer.setEmail("AhmadHH334@gmail.com");
        customer.setPhone("0599897846");
        customer.setAddress("Nablus");
        order.setCustomerId(customer.getId());

    }
    @When("the customer enters the product information name, material, area,treatment, picture")
    public void theCustomerEntersTheProductInformationNameMaterialAreaTreatmentPicture() {
        product = new Product();
        product.setCustomerId(customer.getId());
        product.setName("Carpet");
        product.setMaterial("Nylon");
        product.setArea(40.6F);
        product.setTreatment("No");
        product.setPicture(" ");
        product.setOrderId(order.getOrderId());
        order.addProduct(product);
    }
    @Then("a new order should be registered")
    public void a_new_order_should_be_registered() {
        customer.addOrder(order);
        orders.add(order);

        //functions.customerAddOrder();
    }

}
