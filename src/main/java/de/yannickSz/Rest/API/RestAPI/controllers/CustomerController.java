package de.yannickSz.Rest.API.RestAPI.controllers;

import de.yannickSz.Rest.API.RestAPI.entities.Customer;
import de.yannickSz.Rest.API.RestAPI.services.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController("")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(final CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/getCustomerMail")
    public String getCustomerMail(@RequestParam(name = "id", required = true) int id) {
        Optional<Customer> customer = this.customerService.getCustomerById(id);
        if (customer.isPresent()) {
            return customer.get().getEmail();
        }
        else {
            return "Customer not found!";
        }
    }

    @DeleteMapping("/deleteCustomer")
    public String deleteCustomer(@RequestParam(name = "id", required = true) int id) {
        Optional<Customer> customer = this.customerService.getCustomerById(id);
        if (customer.isPresent()) {
            this.customerService.deleteCustomer(customer.get());
            return "User with ID " + customer.get().getId() + " deleted successfully.";
        }
        else {
            return "Customer not found!";
        }
    }

    @PostMapping("/addCustomer")
    public String addCustomer(@Valid @RequestBody(required = true) final Customer customer) {
        this.customerService.addCustomer(customer);
        return "New customer created!";
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(final MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
