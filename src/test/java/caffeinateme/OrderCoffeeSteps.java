package caffeinateme;

import caffeinateme.model.*;
import io.cucumber.java.ParameterType;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.assertj.core.api.Assertions;


import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;

public class OrderCoffeeSteps {

    CoffeeShop coffeeShop = new CoffeeShop();

    Customer cathy = Customer.named("Cathy");

    Order order;

    @Given("Cathy is {float} metre(s) from the coffee shop")
    public void cathy_is_metres_from_the_coffee_shop(Float distanceInMeters) {
        cathy.setDistanceFromShop(distanceInMeters);


    }
    @When("^Cathy (?:orders|has ordered) an? (.*)$")
    public void cathy_orders_a(String OrderedProduct) {
     // cathy.placesAnOrderFor(order);
        this.order = Order.of(1 , OrderedProduct).forCustomer(cathy);
        cathy.placesAnOrderFor(order).at(coffeeShop);
    }

    @And("Cathy is {int} minutes away")
    public void customerIsMinutesAway(int etaMinutes){
        System.out.println("eta Minutes" + etaMinutes);
        coffeeShop.setCustomerETA(cathy, etaMinutes);
    }

    @Then("^Barry should receive the order$")
    public void barry_should_receive_the_order() {

     Assertions.assertThat(coffeeShop.getPendingOrders().contains(order));

    }

    @ParameterType(value="(Normal|High|Urgent)")
    public OrderStatus orderStatus(String statusValue){
        return OrderStatus.valueOf(statusValue);
    }
    @Then("Barry should know that the order is {orderStatus}")
    public void barry_should_know_that_the_order_is(OrderStatus expectedStatus) {
        Order cathysOrder = coffeeShop.getOrderFor(cathy)
                .orElseThrow(()->new AssertionError("No order found"));
        assertThat(cathysOrder.getStatus()).isEqualTo(expectedStatus);
    }
}
