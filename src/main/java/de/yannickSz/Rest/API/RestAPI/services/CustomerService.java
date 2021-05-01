package de.yannickSz.Rest.API.RestAPI.services;

import de.yannickSz.Rest.API.RestAPI.entities.Customer;
import de.yannickSz.Rest.API.RestAPI.repositories.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("CustomerService")
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(final CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Optional<Customer> getCustomerById(final int id) {
        return this.customerRepository.findById(id);
    }

    public void addCustomer(final Customer customer) {
        this.customerRepository.save(customer);
    }

    public void deleteCustomer(final Customer customer) {
        this.customerRepository.delete(customer);
    }
}
