package StepDefinitions;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.*;

public class CustomerActions {
    User user = new User();
    Customer customer = new Customer("12029088","Ahmad","0569306241","Nablus","ahmadH@gmail.com");
    Functions functions = new Functions();
    static boolean updated = false;
    static boolean deleteProfile = false;
    Printing printing = new Printing();
    Product product = new Product();
    Invoice invoice ;
    Order order ;
    @Given("customer log in with true {string} and {string}")
    public void customerLogInWithTrueAnd(String arg0, String arg1) {
        customer.setPassword("12345");
        if(arg0.equals(customer.getId()) && arg1.equalsIgnoreCase(customer.getPassword())){
            user.setLogstate(true);
        }
    }
    @Given("I am logged in")
    public void i_am_logged_in() {
        user.setLogstate(true);
    }
    @When("I navigate to My Profile")
    public void i_navigate_to_my_profile() {
        if(user.getLogstate()){
            functions.customerPageList();
        }
    }

    @When("I update my profile information depends on {int} and {string}")
    public void i_update_my_profile_information_depends_on_and(Integer i, String val) {
        if( i == 1){
            customer.setName(val);
            updated = true;
        } else if (i == 2) {
            customer.setPhone(val);
            updated = true;
        } else if (i==3) {
            customer.setAddress(val);
            updated = true;
        }else{
            customer.setEmail(val);
            updated = true;
        }
    }
    @Then("my profile should be updated successfully")
    public void my_profile_should_be_updated_successfully() {
        if(updated){
            printing.printSomething("Updated successfully");
        }
    }
    @When("I navigate to the Order page")
    public void i_navigate_to_the_order_page() {
       order = new Order(1);
    }
    @When("I select the desired items")
    public void i_select_the_desired_items() {
        product = new Product("Carpet","Nylon",40.6F,"No"," ");
        product.setCustomerId(customer.getId());
        product.setOrderId(order.getOrderId());
        order.addProduct(product);
        product = new Product("Cover","Velvet",47.6F,"No"," ");
        product.setCustomerId(customer.getId());
        product.setOrderId(order.getOrderId());
        order.addProduct(product);
        customer.addOrder(order);
        customer.setNumberOfOrders(customer.getOrders().size());
    }
    @Then("the order should be placed successfully")
    public void the_order_should_be_placed_successfully() {
        if(!(customer.getOrders().isEmpty())){
            printing.printSomething("Order added successfully");
        }
    }
    @When("I choose to delete my profile")
    public void i_choose_to_delete_my_profile() {
        deleteProfile = true;
    }
    @When("I confirm the deletion")
    public void i_confirm_the_deletion(){

    }
    @Then("my profile should be deleted successfully")
    public void my_profile_should_be_deleted_successfully() {
        printing.printSomething("\nAccount Successfully Deleted\n\n");
    }
    @When("I navigate to Invoices")
    public void i_navigate_to_invoices()  {
        printing.printSomething("\nNumber of orders:"+ customer.getNumberOfOrders());
        invoice = new Invoice(customer);
        invoice.invoiceRes(customer);
    }
    @Then("I should see a my invoice")
    public void i_should_see_a_my_invoice() {

    }

}
