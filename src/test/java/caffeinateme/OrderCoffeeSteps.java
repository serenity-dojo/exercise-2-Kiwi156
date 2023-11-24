package caffeinateme;

import caffeinateme.model.CoffeeShop;
import caffeinateme.model.Customer;
import caffeinateme.model.Order;
import caffeinateme.model.OrderStatus;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.assertj.core.api.Assertions;

import static org.assertj.core.api.Assertions.assertThat;

public class OrderCoffeeSteps {

    Customer cathy = Customer.named("Cathy");
    CoffeeShop coffeeShop = new CoffeeShop();
    Order order;
    @Given("^Cathy is (\\d*) metres from the coffee shop$")
    public void cathy_is_metres_from_the_coffee_shop(Integer distanceInMeters) {

        cathy.setDistanceFromShop(distanceInMeters);
    }
    @When("^Cathy orders a(.*)$")
    public void cathy_orders_a_(String orderedProduct) {

        this.order = Order.of(1, orderedProduct).forCustomer(cathy);
        cathy.placesAnOrderFor(order).at(coffeeShop);
    }
    @Then("Barry should receive the order")
    public void barry_should_receive_the_order() {

        Assertions.assertThat(coffeeShop.getPendingOrders()).contains(order);
    }
    @Then("^Barry should know that the order is (.*)$")
    public void barry_should_know_that_the_order_is(OrderStatus expectedStatus)
    {

        Order cathysOrder = coffeeShop.getOrderFor(cathy)
                .orElseThrow(()->new AssertionError("No order found"));
        assertThat(cathysOrder.getStatus()).isEqualTo(expectedStatus);

    }


}