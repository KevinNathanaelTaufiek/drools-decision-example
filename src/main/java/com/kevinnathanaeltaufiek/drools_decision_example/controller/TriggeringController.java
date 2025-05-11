package com.kevinnathanaeltaufiek.drools_decision_example.controller;

import com.kevinnathanaeltaufiek.drools_decision_example.model.Customer;
import com.kevinnathanaeltaufiek.drools_decision_example.model.Order;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TriggeringController {
    // For the sake of simplicity let us define logic here

    @Autowired
    @Qualifier("customerKieSession")
    private KieSession customerSession;

    @Autowired
    @Qualifier("orderKieSession")
    private KieSession orderSession;

    @PostMapping("/customer")
    public Customer orderNow(@RequestBody Customer customer) {
        customerSession.insert(customer);
        customerSession.fireAllRules();
        return customer;
    }


    @PostMapping("/order")
    public Order orderNow(@RequestBody Order order) {
        orderSession.insert(order);
        orderSession.fireAllRules();
        return order;
    }
}
