package com.marketplace.rest;

import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.market.RestURIConstants.ALL_CUSTOMERS;
import static com.market.RestURIConstants.CUSTOMERS_ID;

@RestController
class CustomerController {

    private final CustomerRepository customerRepository;

    CustomerController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }


    // Aggregate root
    // tag::get-aggregate-root[]
    @GetMapping(ALL_CUSTOMERS)
//    @Transactional(propagation = Propagation.REQUIRES_NEW)
    List<Customer> all() {
        return customerRepository.findAll();
    }
    // end::get-aggregate-root[]

    @PostMapping(ALL_CUSTOMERS)

    Customer newCustomer(@RequestBody Customer newCustomer) {
        return customerRepository.save(newCustomer);
    }

//    // Single item

    @GetMapping(CUSTOMERS_ID)
    Customer one(@PathVariable Long id) {

        return customerRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    @PutMapping(CUSTOMERS_ID)
    Customer replaceCustomer(@RequestBody Customer newCustomer, @PathVariable Long id) {

        return customerRepository.findById(id)
                .map(customer -> {
                    customer.setName(newCustomer.getName());
                    customer.setRole(newCustomer.getRole());
                    return customerRepository.save(customer);
                })
                .orElseGet(() -> {
                    newCustomer.setId(id);
                    return customerRepository.save(newCustomer);
                });
    }

    @DeleteMapping(CUSTOMERS_ID)
    void deleteEmployee(@PathVariable Long id) {
        customerRepository.deleteById(id);
    }
}
