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
public class ProductController {

    @Autowired
    private ProductRepository proRepo;

    //Get for Customer
    @GetMapping("/product/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable Long id){
        if (!proRepo.existsById(id))
           return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
        
        Optional<Product> product = proRepo.findById(id);
        return new ResponseEntity<Product>(product.get(), HttpStatus.OK);
    }
    //Get All customer
    @GetMapping("/product")
    public ResponseEntity<Collection> getAllProduct(){
        return new ResponseEntity<Collection>(proRepo.findAll(), HttpStatus.OK);
    }


    //Post for creating a customer
    @PostMapping("/product")
    public ResponseEntity<String> createProduct(@RequestBody Product product){
        proRepo.save(product);
        return new ResponseEntity<String>("Product created", HttpStatus.CREATED);
    }

    //Delete for deleting a customer by id
    @DeleteMapping("product/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id){
       proRepo.deleteById(id);
        
        return new ResponseEntity<String>("Product deleted", HttpStatus.NO_CONTENT);
    }
}