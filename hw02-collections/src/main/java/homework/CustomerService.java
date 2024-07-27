package homework;

import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

public class CustomerService {
    // лучше писать интерфейс TreeMap -> NavigableMap
    private final NavigableMap<Customer, String> customerData;

    public CustomerService() {
        this.customerData = new TreeMap<>();
    }

    public Map.Entry<Customer, String> getSmallest() {
        Map.Entry<Customer, String> smallest = customerData.firstEntry();
        return cloneEntry(smallest);
    }

    public Map.Entry<Customer, String> getNext(Customer customer) {
        Map.Entry<Customer, String> higher = customerData.higherEntry(customer);
        return cloneEntry(higher);
    }

    public void add(Customer customer, String data) {
        customerData.put(customer, data);
    }

    private Map.Entry<Customer, String> cloneEntry(Map.Entry<Customer, String> entry) {
        if (entry == null) {
            return null;
        }

        Customer sourceCustomer = entry.getKey();
        Customer clonedCustomer =
                new Customer(sourceCustomer.getId(), sourceCustomer.getName(), sourceCustomer.getScores());

        return Map.entry(clonedCustomer, entry.getValue());
    }
}
