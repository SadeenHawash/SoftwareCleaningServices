package StepDefinitions;

import static org.junit.Assert.assertTrue;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Discount {
    int num=0;
    int price;
    boolean flagDis=false;
    boolean flagDis10=false;
    boolean flagDis5=false;

    public Discount() {

    }

    @Given("the user is login with email {string}")
    public void the_user_is_login_with_email(String string) {
        // Write code here that turns the phrase above into concrete actions

    }

    @Given("the price of the user {string}")
    public void the_price_of_the_user(String string) {
        // Write code here that turns the phrase above into concrete actions

    }

    @Then("the user pay the price of product after discount {int}% {string}")
    public void the_user_pay_the_price_of_product_after_discount(Integer int1, String string) {
        // Write code here that turns the phrase above into concrete actions

    }

}