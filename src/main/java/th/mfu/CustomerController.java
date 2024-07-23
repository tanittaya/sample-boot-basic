package th.mfu;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.deser.std.CollectionDeserializer;

@RestController
public class CustomerController {

    public static Map<Integer, Customer> customerDB = new HashMap<Integer, Customer>();
    private int nextId =0;
    // GET for a customer
    @GetMapping("/customers/{id}")
    public ResponseEntity<Customer> getCustomer(@PathVariable Integer id){
        if(customerDB.get(id) == null)
            return new ResponseEntity<Customer>(HttpStatus.NOT_FOUND);
        Customer customer = customerDB.get(id);
        return new ResponseEntity<Customer>(customer, HttpStatus.OK);
    }

    // Get all customer
    @GetMapping("/customers")
    public ResponseEntity<Collection> getAllCustomers(){
        return new ResponseEntity<Collection>(customerDB.values(), HttpStatus.OK);
    }


    // POST for creating a customer
    @PostMapping("/customers")
    public ResponseEntity<String> createCustomer(@RequestBody Customer customer){
        customerDB.put(nextId, customer);
        nextId++;
        return new ResponseEntity<String>("Customer created", HttpStatus.CREATED);
    }

    // DELETE for deleting a customer by id
    @DeleteMapping("customers/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable Integer id){
        customerDB.remove(id);
        return new ResponseEntity<String>("Customer deleted", HttpStatus.NO_CONTENT);
    }

}
