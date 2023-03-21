package com.example.springboot3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@SpringBootApplication
@RestController
@RequestMapping("api/v1/customers")
public class Springboot3Application {

	private final CustomerRepository customerRepository;

	public Springboot3Application(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(Springboot3Application.class, args);
	}

	@GetMapping
	public List<Customer> getCustomer(){
		return customerRepository.findAll();
	}

	@PostMapping
	public void addCustomer(@RequestBody NewCustomerRequest customerRequest){
		Customer customer = new Customer();
		customer.setAge(customerRequest.age());
		customer.setEmail(customerRequest.email());
		customer.setName(customerRequest.name());
		customerRepository.save(customer);
	}

	@DeleteMapping("{customerId}")
	public void deleteCustomer(@PathVariable("customerId") Integer id){
		customerRepository.deleteById(id);
		 }

	@PutMapping("{customerId}")
	public void updateCustomer(@PathVariable("customerId") Integer id,@RequestBody NewCustomerRequest customerRequest) throws Exception {
		Customer customer = customerRepository.findById(id).orElseThrow(() -> new Exception("Customer not found for this id :: " + id));;
		customer.setAge(customerRequest.age());
		customer.setEmail(customerRequest.email());
		customer.setName(customerRequest.name());
		customerRepository.save(customer);
	}
}

//	@GetMapping("/")
//	public String hello() {
//		return "Hello";
//	}
//
//	@GetMapping("/hello")
//	public Person hello1() {
//		Person person = new Person("manideep",27,0);
//		return person;
//	}
//	@GetMapping("/greet")
//	public GreetResponse greet(){
//		GreetResponse response = new GreetResponse("Hello",List.of("Java","C"), new Person("manideep",27,0));
//		return response;
//	}
//
//	}
//
//	record GreetResponse(String greet, List<String> favProgLang,Person person){}
//	record Person(String name,int age,double savings){}

