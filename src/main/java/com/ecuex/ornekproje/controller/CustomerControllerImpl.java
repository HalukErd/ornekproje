package com.ecuex.ornekproje.controller;

import com.ecuex.ornekproje.model.CustomerEntity;
import com.ecuex.ornekproje.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/customers")
public class CustomerControllerImpl implements Controller<CustomerEntity> {

    CustomerService customerService;

    @Autowired
    public CustomerControllerImpl(@Qualifier("CustomerServiceImpl") CustomerService customerService) {
        this.customerService = customerService;
    }

    @Override
    @GetMapping(produces = "application/json")
    public List<CustomerEntity> getAll() {
        return customerService.getCustomers();
    }

    @Override
    @PostMapping(consumes = "application/json")
    public CustomerEntity save(@RequestBody CustomerEntity customerEntity) {
        return (CustomerEntity) customerService.saveCustomer(customerEntity);
    }

    @Override
    @GetMapping(value = "{tckn}")
    public CustomerEntity get(@PathVariable("tckn") Long tckn) {
        return (CustomerEntity) customerService.getCustomer(tckn);
    }

    @Override
    @PutMapping
    public CustomerEntity put(@RequestBody CustomerEntity customerEntity) {
        return (CustomerEntity) customerService.putCustomer(customerEntity);
    }

    @Override
    @DeleteMapping(value = "{tckn}")
    public boolean delete(@PathVariable("tckn") Long tckn) {
        customerService.deleteCustomer(tckn);
        return true; // edit here
    }
}