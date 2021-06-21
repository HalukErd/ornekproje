package com.ecuex.ornekproje.controller;

import com.ecuex.ornekproje.model.CustomerEntity;
import com.ecuex.ornekproje.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/customers")
public class CustomerControllerImpl implements ControllerWithResponseEntity<CustomerEntity, Long> {

    CustomerService customerService;

    @Autowired
    public CustomerControllerImpl(@Qualifier("CustomerServiceImpl") CustomerService customerService) {
        this.customerService = customerService;
    }


    @GetMapping(produces = "application/json")
    public ResponseEntity<List<CustomerEntity>> getAll() {
        System.out.println("invoked getAll method");
        List customers = customerService.getCustomers();
        if (customers == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        ResponseEntity<List<CustomerEntity>> resultCustomerEntity = new ResponseEntity<>(customers, HttpStatus.OK);
        return resultCustomerEntity;
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<CustomerEntity> save(@RequestBody CustomerEntity customerEntity) {
        CustomerEntity resultCustomerEntity = (CustomerEntity) customerService.saveCustomer(customerEntity);
        if (resultCustomerEntity == null) {
            System.out.println("bad req");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        ResponseEntity<CustomerEntity> resultResponse = new ResponseEntity<>(resultCustomerEntity, HttpStatus.OK);
        return resultResponse;
    }

    @Override
    @GetMapping(value = "{tckn}")
    public ResponseEntity<CustomerEntity> get(@PathVariable("tckn") Long tckn) {
        CustomerEntity resultCustomerEntity = (CustomerEntity) customerService.getCustomer(tckn);
        if (resultCustomerEntity == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        ResponseEntity<CustomerEntity> resultResponse = new ResponseEntity<>(resultCustomerEntity, HttpStatus.OK);
        return resultResponse;
    }

    @PutMapping
    public ResponseEntity<CustomerEntity> put(@RequestBody CustomerEntity customerEntity) {
        CustomerEntity resultCustomerEntity = (CustomerEntity) customerService.saveCustomer(customerEntity);
        if (resultCustomerEntity == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        ResponseEntity<CustomerEntity> resultResponse = new ResponseEntity<>(resultCustomerEntity, HttpStatus.OK);
        return resultResponse;
    }

    @DeleteMapping(value = "{tckn}")
    public ResponseEntity delete(@PathVariable("tckn") Long tckn) {
        boolean foundAndDeleted = customerService.deleteCustomer(tckn);
        if (!foundAndDeleted) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(String.valueOf(tckn), HttpStatus.OK);
    }
}