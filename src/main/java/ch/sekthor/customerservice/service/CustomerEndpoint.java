package ch.sekthor.customerservice.service;

import ch.sekthor.customerservice.models.Customer;
import ch.sekthor.customerservice.persitence.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CustomerEndpoint {

    @Autowired
    CustomerRepository customerRepository;

    @GetMapping("/customers")
    public List<Customer> findAllCustomers() {
        return customerRepository.findAll();
    }

    @GetMapping("/customer/{customerId}")
    public Customer findCustomerById(@PathVariable Long customerId) {
        return customerRepository.findById(customerId).get();
    }

    @PostMapping("/customer")
    public ResponseEntity<Void> addNewCustomer(@RequestBody Customer customer){
        try {
            customerRepository.save(customer);
            return ResponseEntity.accepted().build();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/customer/{customerId}")
    public ResponseEntity<Void> deleteMovieById(@PathVariable Long customerId) {
        try {
            customerRepository.deleteById(customerId);
            return ResponseEntity.accepted().build();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }
}
