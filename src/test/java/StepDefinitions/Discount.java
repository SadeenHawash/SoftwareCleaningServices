package StepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.Customer;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public class Discount {
    private Customer customer;
    private BigDecimal totalPrice;
    private BigDecimal expectedPrice;

    @Given("a customer with name {string} and total spent amount of {int} NIS")
    public void a_customer_with_name_and_total_spent_amount_of_nis(String name, Integer totalSpentAmount) {
        customer = new Customer(name, new BigDecimal(totalSpentAmount));
    }

    @When("the discount eligibility is checked")
    public void the_discount_eligibility_is_checked() {
        expectedPrice = totalPrice;
        if (!Objects.equals(customer.calculateDiscountedPrice(totalPrice), totalPrice)) {
            BigDecimal discount = totalPrice.multiply(new BigDecimal("0.1"));
            expectedPrice = totalPrice.subtract(discount);
        }
    }

    @Then("the customer should receive a {int}% discount")
    public void the_customer_should_receive_a_discount(Integer discountPercentage)  {
        BigDecimal discount = totalPrice.multiply(new BigDecimal(discountPercentage).divide(new BigDecimal("100"), RoundingMode.DOWN));
        BigDecimal discountedPrice = totalPrice.subtract(discount);
        assertEquals(discountedPrice, customer.calculateDiscountedPrice(totalPrice));
    }

    @Then("the discounted price should be calculated correctly")
    public void the_discounted_price_should_be_calculated_correctly() {
        totalPrice = new BigDecimal("500");
        assertEquals(expectedPrice, customer.calculateDiscountedPrice(totalPrice));
    }

    @Then("the customer should not receive a discount")
    public void the_customer_should_not_receive_a_discount() {
        totalPrice = new BigDecimal("300");
        assertEquals(totalPrice, customer.calculateDiscountedPrice(totalPrice));
    }

    @Then("the original price should be charged")
    public void the_original_price_should_be_charged() {
        totalPrice = new BigDecimal("300");
        assertEquals(totalPrice, customer.calculateDiscountedPrice(totalPrice));
    }
}

