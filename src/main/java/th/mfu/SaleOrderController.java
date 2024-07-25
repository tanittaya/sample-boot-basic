package th.mfu;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SaleOrderController {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private SaleOrderRepository saleOrderRepository;

    //create order by customer
    @PostMapping("/customers/{customerId}/orders")
    public ResponseEntity<String> createOrder(@PathVariable Long customerId, @RequestBody SaleOrder saleOrder){
        //Check if customer exists

        if (!customerRepository.existsById(customerId)) {
            return new ResponseEntity<String>("Customer is not found", HttpStatus.NOT_FOUND);
        }

        Optional<Customer> optCustomer = customerRepository.findById(customerId);
        //Set customer to order
        saleOrder.setCustomer(optCustomer.get());
        saleOrderRepository.save(saleOrder);
        return new ResponseEntity<String>("Order Created", HttpStatus.OK);
    }


    //Get all Order
    @GetMapping("/orders")
    public ResponseEntity<Collection<SaleOrder>> getAllOrder(){

        return new ResponseEntity<Collection<SaleOrder>>(saleOrderRepository.findAll(), HttpStatus.OK);
    }
    //get order by customerID
    @GetMapping("/customers/{customerId}/orders")
    public ResponseEntity<Collection<SaleOrder>>getOrderByCustomer(@PathVariable Long customerId){
        //check cust exit
        if(!customerRepository.existsById(customerId)){
            return new ResponseEntity<Collection<SaleOrder>>(HttpStatus.NOT_FOUND);
        }
        Collection<SaleOrder> orders = saleOrderRepository.findByCustomerId(customerId);
        return new ResponseEntity<Collection<SaleOrder>>(orders,HttpStatus.OK);

    }

}