package com.exampleaop.springAop.controller;

import com.exampleaop.springAop.service.CustomerAdvanceService;
import com.exampleaop.springAop.service.CustomerAnotherService;
import com.exampleaop.springAop.service.CustomerLoggableService;
import com.exampleaop.springAop.service.CustomerService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebController {
    ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");
    CustomerService service = context.getBean(CustomerService.class);
    CustomerAnotherService anotherService = context.getBean(CustomerAnotherService.class);
    CustomerAdvanceService advanceService = context.getBean(CustomerAdvanceService.class);
    CustomerLoggableService loggableService = context.getBean(CustomerLoggableService.class);

    @RequestMapping("/aspectnormal")
    public String normalAspect() {
        String result = "";

        result += service.getCustomerById(1L).getFirstName() + " ";
        result += service.setCustomerFirstName("Peter", 1L) + " ";
        result += service.setCustomerLastName("Levin", 2);

        return result;
    }

    @RequestMapping("/aspectpointcut")
    public String pointcutAspect() {
        String result = "";

        result += anotherService.findCustomerByLastName("Smith") + "";
        result += anotherService.findAllCustomers();

        return result;
    }

    @RequestMapping("/aspectjoinpoint")
    public String joinpointAspect() {
        String result = "";

        result += advanceService.setCustomerFirstName("Peter", 1L) + "";
        result += advanceService.findAllCustomers();

        return result;
    }

    @RequestMapping("/aspectloggable")
    public String loggableAspect() {
        return loggableService.getCustomerById(1).getFirstName();
    }
}
