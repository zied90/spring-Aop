package com.exampleaop.springAop.service;

import com.exampleaop.springAop.model.Customer;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Component
public class CustomerAnotherService {
    private static Map<Long, Customer> store = new HashMap<>();

    static {
        store.put(1L, new Customer(1, "zied", "Miladi"));
        store.put(2L, new Customer(2, "sami", "ferjani"));
    }

    public Customer getCustumerById(Long id) {
        return store.get(id);
    }

    public Customer setCustumerFirstName(String firstName, Long id) {
        Customer cust = store.get(id);
        cust.setFirstName(firstName);
        return cust;
    }

    public Customer setCustomerLastName(String lastName, long id) {
        Customer cust = store.get(id);
        cust.setLastName(lastName);
        return cust;
    }

    public List<Customer> findCustomerByLastName(String lastName) {
        List<Customer> listOfCustumer = new ArrayList<>();
        for (Long id : store.keySet()) {
            if (store.get(id).getLastName().equals(lastName)) {
                listOfCustumer.add(store.get(id));
            }
        }
        return listOfCustumer;
    }

    public List<Customer> findAllCustomers() {
        List<Customer> listOfCustumer = new ArrayList<>();
        for (Long id : store.keySet()) {
            listOfCustumer.add(store.get(id));
        }
        return listOfCustumer;
    }
}
