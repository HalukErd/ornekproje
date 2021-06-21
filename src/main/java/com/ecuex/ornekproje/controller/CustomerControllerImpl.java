package com.ecuex.ornekproje.controller;

import com.ecuex.ornekproje.model.CustomerDTO;
import com.ecuex.ornekproje.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/customers")
public class CustomerControllerImpl implements ControllerWithResponseEntity<CustomerDTO, Long> {

    CustomerService customerService;

    @Autowired
    public CustomerControllerImpl(@Qualifier("CustomerServiceImpl") CustomerService customerService) {
        this.customerService = customerService;
    }


    @GetMapping(produces = "application/json")
    public ResponseEntity<List<CustomerDTO>> getAll() {
        System.out.println("invoked getAll method");
        List customers = customerService.getCustomers();
        if (customers == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        ResponseEntity<List<CustomerDTO>> resultCustomerDTO = new ResponseEntity<>(customers, HttpStatus.OK);
        return resultCustomerDTO;
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<CustomerDTO> save(@RequestBody @Valid CustomerDTO customerDTO) {
        CustomerDTO resultCustomerDTO = (CustomerDTO) customerService.saveCustomer(customerDTO);
        if (resultCustomerDTO == null) {
            System.out.println("bad req");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        ResponseEntity<CustomerDTO> resultResponse = new ResponseEntity<>(resultCustomerDTO, HttpStatus.OK);
        return resultResponse;
    }

    @Override
    @GetMapping(value = "{tckn}")
    public ResponseEntity<CustomerDTO> get(@PathVariable("tckn") Long tckn) {
        CustomerDTO resultCustomerDTO = (CustomerDTO) customerService.getCustomer(tckn);
        if (resultCustomerDTO == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        ResponseEntity<CustomerDTO> resultResponse = new ResponseEntity<>(resultCustomerDTO, HttpStatus.OK);
        return resultResponse;
    }

    @PutMapping
    public ResponseEntity<CustomerDTO> put(@RequestBody @Valid CustomerDTO customerDTO) {
        CustomerDTO resultCustomerDTO = (CustomerDTO) customerService.saveCustomer(customerDTO);
        if (resultCustomerDTO == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        ResponseEntity<CustomerDTO> resultResponse = new ResponseEntity<>(resultCustomerDTO, HttpStatus.OK);
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