package com.finalpro.marketplace;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
class CustomerController {

    private final CustomerRepository customerRepository;

    CustomerController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }


    // Aggregate root
    // tag::get-aggregate-root[]
    @GetMapping("/customers")
    List<Customer> all() {
        return customerRepository.findAll();
    }
    // end::get-aggregate-root[]

    @PostMapping("/customers")
    Customer newCustomer(@RequestBody Customer newCustomer) {
        return customerRepository.save(newCustomer);
    }

//    // Single item

    @GetMapping("/customers/{id}")
    Customer one(@PathVariable Long id) {

        return customerRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    @PutMapping("/customers/{id}")
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

    @DeleteMapping("/customers/{id}")
    void deleteEmployee(@PathVariable Long id) {
        customerRepository.deleteById(id);
    }
}
