package com.teste.vetta.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.teste.vetta.models.Customer;
import com.teste.vetta.services.CustomerService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;

@Controller
@RestController()
@Api(value = "Rotas", description = "Rotas dos End-Points")
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	  /**
	   * Creates a Customer in the database.
	   *
	   * @param Customer Information to be assigned to the new Customer
	   * @return Customer with id filled in
	   */
	  @ApiOperation(value = "Cria novos Customers")
	  @CrossOrigin
	  @PostMapping("/Customer")
	  public ResponseEntity<Object> createUser(@RequestBody Customer customer) {
		  Customer createdCustomer = customerService.save(customer);

	    return ResponseEntity.ok(createdCustomer);
	  }

	  /**
	   * Updates a Customer's information.	
	   *
	   * @param Customer Information to update
	   * @param id Identifier of the Customer to be updated
	   * @return Status Ok
	   */
	  @ApiOperation(value = "Atualiza Customers")
	  @CrossOrigin
	  @PutMapping("/Customer/{id}")
	  public ResponseEntity<Object> updateCustomer(
	      @RequestBody Customer Customer, @PathVariable Integer id) {
	    Optional<Customer> CustomerLoad = customerService.findById(id);

	    if (!CustomerLoad.isPresent()) {
	      return ResponseEntity.notFound().build();
	    }

	    Customer.setId(id);

	    customerService.save(Customer);

	    return ResponseEntity.noContent().build();
	  }

	  /**
	   * Gets all registered Customers in the database.
	   *
	   * @return List of registered Customers
	   */
	  @ApiOperation(value = "Busca todos os Customers")
	  @CrossOrigin
	  @GetMapping("/Customer")
	  public List<Customer> searchAllCustomers() {
	    List<Customer> CustomerList = new ArrayList<>();

	    customerService.findAll().forEach(e -> CustomerList.add(e));

	    return CustomerList;
	  }

	  /**
	   * Gets the Customer information by its identifier.
	   *
	   * @param id Customer identificator
	   * @return Customer containing the Id
	   */
	  @ApiOperation(value = "Buscar Customer pelo Id")
	  @CrossOrigin
	  @GetMapping("/Customer/{id}")
	  public ResponseEntity<Customer> searchCustomerById(@PathVariable Integer id) {
	    Optional<Customer> Customer = customerService.findById(id);

	    if (!Customer.isPresent()) {
	      return ResponseEntity.notFound().build();
	    }

	    return ResponseEntity.ok(Customer.get());
	  }

	  /**
	   * Delete Customer by Id.
	   *
	   * @param id Customer identificator
	   */
	  @ApiOperation(value = "Deleta Customers")
	  @CrossOrigin
	  @DeleteMapping("/Customer/{id}")
	  public void deleteCustomer(@PathVariable Integer id) {
	    customerService.deleteById(id);
	  }


}
