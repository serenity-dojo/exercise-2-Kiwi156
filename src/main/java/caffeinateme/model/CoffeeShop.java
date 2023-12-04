package caffeinateme.model;

import java.util.*;

public class CoffeeShop {

    public CoffeeShop(Queue<Order> orders) {
        this.orders = orders;
    }

    private Queue<Order> orders = new LinkedList<>();

    public CoffeeShop() {


    }


    public void placeOrder(Order order, Float distanceInMetres) {
        if (distanceInMetres <= 200) {
            order = order.withStatus(OrderStatus.Urgent);
        }
        orders.add(order);
    }

    public List<Order> getPendingOrders() {
        return new ArrayList<>(orders);
    }

    public Optional<Order> getOrderFor(Customer customer) {
        return orders.stream()
                .filter(order -> order.getCustomer().equals(customer))
                .findFirst();


    }
    public void setCustomerETA(Customer customer, int etaInMinutes) {
        getOrderFor(customer).ifPresent(
                order -> {
                    if (etaInMinutes < 5) {
                        order.updateStatusTo(OrderStatus.Urgent);
                    } else if (etaInMinutes > 10) {
                        order.updateStatusTo(OrderStatus.Normal);
                    } else {
                        order.updateStatusTo(OrderStatus.High);
                    }
                }
        );
    }
}
