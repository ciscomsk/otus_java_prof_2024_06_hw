package homework;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

public class CustomerReverseOrder {
    // Stack -> Deque
    private final Deque<Customer> orders;

    public CustomerReverseOrder() {
        this.orders = new ArrayDeque<>();
    }

    public void add(Customer customer) {
        orders.push(customer);
    }

    public Customer take() {
        return orders.pop();
    }
}
