package th.mfu;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {

    @Autowired
    private CustomerRepository custRepo;

    //Get for Customer
    @GetMapping("/customer/{id}")
    public ResponseEntity<Customer> getCustomer(@PathVariable Long id){
        if (!custRepo.existsById(id))
           return new ResponseEntity<Customer>(HttpStatus.NOT_FOUND);
        
        Optional<Customer> customer = custRepo.findById(id);
        return new ResponseEntity<Customer>(customer.get(), HttpStatus.OK);
    }
    //Get All customer
    @GetMapping("/customer")
    public ResponseEntity<Collection> getAllCustomer(){
        return new ResponseEntity<Collection>(custRepo.findAll(), HttpStatus.OK);
    }


    //Post for creating a customer
    @PostMapping("/customer")
    public ResponseEntity<String> createCustomer(@RequestBody Customer customer){
        if(custRepo.findByName(customer.getName()).isEmpty()){
            //return conflig
            return new ResponseEntity<String>("Customer already exists", HttpStatus.CONFLICT);
        }
        custRepo.save(customer);
        return new ResponseEntity<String>("Customer created", HttpStatus.CREATED);
    }

    //Delete for deleting a customer by id
    @DeleteMapping("customer/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable Long id){
       custRepo.deleteById(id);
        
        return new ResponseEntity<String>("Customer deleted", HttpStatus.NO_CONTENT);
    }
}