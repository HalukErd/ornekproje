package com.ecuex.ornekproje.service;

import com.ecuex.ornekproje.model.CustomerEntity;
import com.ecuex.ornekproje.repository.AddressRepository;
import com.ecuex.ornekproje.repository.CustomerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("CustomerServiceImpl")
public class CustomerServiceImpl implements CustomerService<CustomerEntity> {

    CustomerRepository customerRepository;
    AddressRepository addressRepository;
    ModelMapper modelMapper;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository, AddressRepository addressRepository, ModelMapper modelMapper) {
        this.customerRepository = customerRepository;
        this.addressRepository = addressRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<CustomerEntity> getCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public CustomerEntity saveCustomer(CustomerEntity customerEntity) {
        if (customerEntity.getName().isEmpty() || customerEntity.getName() == null) {
            return null;
        }
        return customerRepository.save(customerEntity);
    }

    @Override
    public CustomerEntity getCustomer(Long tckn) {
        return customerRepository.findById(tckn).orElseThrow();
    }

    @Override
    public CustomerEntity putCustomer(CustomerEntity customerEntity) {
        return customerRepository.save(customerEntity);
    }

    @Override
    public boolean deleteCustomer(Long tckn) {
        boolean exists = customerRepository.existsById(tckn);
        if (!exists) {
            return false;
        }
        customerRepository.deleteById(tckn);
        return true;
    }
}