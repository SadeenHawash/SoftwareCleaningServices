package StepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.example.*;

import static org.junit.Assert.assertEquals;

public class GenerateInvoice {
    private Customer customer;
    private Order order;
    private double totalPrice;
    private String address;
    private Invoice invoice;
    @Given("a customer with a list of items to be cleaned")
    public void a_customer_with_a_list_of_items_to_be_cleaned() {
        customer = new Customer("1102345","AhmadShaker","0599231231","Nablus/Rafedia","AhmadShaker@gmail.com");
        order = new Order(1);
        Product product =new Product("Carpet", "Nylon",30.5F, "Yes"," ");
        Product product1 = new Product("Carpet",  "Nylon", 30.5F, "No"," ");
        order.addProduct(product); order.addProduct(product1);
        customer.addOrder(order);

    }
    @And("an invoice is generated")
    public void anInvoiceIsGenerated() {
        totalPrice = order.getTotalPrice();
        address = customer.getAddress();
        invoice = new Invoice(customer);
        invoice.invoiceRes(customer);
    }
    @Then("the invoice should include the delivery information, price, address, and items to be cleaned")
    public void theInvoiceShouldIncludeTheDeliveryInformationPriceAddressAndItemsToBeCleaned() {
        assertEquals(customer.getName(), invoice.getCustomerName());
        assertEquals(address, invoice.getAddress());
        assertEquals(totalPrice, invoice.getTotalPrice(), 0.001);
        assertEquals(order.getProducts(), invoice.getProducts());
    }

}

