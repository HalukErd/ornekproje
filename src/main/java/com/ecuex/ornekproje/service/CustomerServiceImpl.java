package com.ecuex.ornekproje.service;

import com.ecuex.ornekproje.model.CustomerEntity;
import com.ecuex.ornekproje.model.CustomerDTO;
import com.ecuex.ornekproje.repository.AddressRepository;
import com.ecuex.ornekproje.repository.CustomerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service("CustomerServiceImpl")
public class CustomerServiceImpl implements CustomerService<CustomerDTO> {

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
    public List<CustomerDTO> getCustomers() {
        List<CustomerEntity> customerEntities = customerRepository.findAll();
        List<CustomerDTO> result = customerEntities
                .stream()
                .map(s -> modelMapper.map(s, CustomerDTO.class))
                .collect(Collectors.toList());

        return result;
    }

    @Override
    public CustomerDTO saveCustomer(CustomerDTO customerDTO) {
//        if (customerDTO.getName().isEmpty() || customerDTO.getName() == null) {
//            return null;
//        }
        CustomerEntity customerEntity = modelMapper.map(customerDTO, CustomerEntity.class);
        CustomerDTO result = modelMapper.map(customerRepository.save(customerEntity), CustomerDTO.class);
        return result;
    }

    @Override
    public CustomerDTO getCustomer(Long tckn) {
        CustomerEntity customerEntity = customerRepository.findById(tckn).orElseThrow();
        CustomerDTO result = modelMapper.map(customerEntity, CustomerDTO.class);
        return result;
    }

    @Override
    public CustomerDTO putCustomer(CustomerDTO customerDTO) {
        CustomerEntity customerEntity = modelMapper.map(customerDTO, CustomerEntity.class);
        CustomerDTO result = modelMapper.map(customerRepository.save(customerEntity), CustomerDTO.class);
        return result;
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