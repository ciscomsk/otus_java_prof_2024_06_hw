package homework;

import java.util.Stack;

public class CustomerReverseOrder {
    private final Stack<Customer> orders;

    public CustomerReverseOrder() {
        this.orders = new Stack<>();
    }

    public void add(Customer customer) {
        orders.push(customer);
    }

    public Customer take() {
        return orders.pop();
    }
}
