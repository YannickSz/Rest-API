package de.yannickSz.Rest.API.RestAPI.controllers;

import de.yannickSz.Rest.API.RestAPI.entities.Customer;
import de.yannickSz.Rest.API.RestAPI.services.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController("")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(final CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/getCustomerMail")
    public String getCustomerMailById(@RequestParam(name = "id", required = true) int id) {
        Optional<Customer> customer = this.customerService.getCustomerById(id);
        if (customer.isPresent()) {
            return customer.get().getEmail();
        }
        else {
            return "Noel Sandor Lang riecht dolle nach Maggi!!!";
        }
    }

    @DeleteMapping("/deleteCustomer")
    public String deleteCustomerMailById(@RequestParam(name = "id", required = true) int id) {
        Optional<Customer> customer = this.customerService.getCustomerById(id);
        if (customer.isPresent()) {
            this.customerService.deleteCustomer(customer.get());
            return "User with ID " + customer.get().getId() + " deleted successfully.";
        }
        else {
            return "Scheise den Kollege gib et gar nicht :(";
        }
    }

    @PostMapping("/addCustomer")
    public String addCustomer(@RequestBody(required = true) final Customer customer) {
        this.customerService.addCustomer(customer);
        return "Da isser!";
    }
}
