package com.sprinboot.backend.controller;

import java.sql.Date;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sprinboot.backend.enums.Status;
import com.sprinboot.backend.model.Customer;
import com.sprinboot.backend.model.Employee;
import com.sprinboot.backend.model.Pet;
import com.sprinboot.backend.model.Receipt;
import com.sprinboot.backend.model.Request;
import com.sprinboot.backend.repository.CustomerRepository;
import com.sprinboot.backend.repository.EmployeeRepository;
import com.sprinboot.backend.repository.PetRepository;
import com.sprinboot.backend.repository.ReceiptRepository;
import com.sprinboot.backend.repository.RequestRepository;

@RestController
public class RequestController {

	@Autowired
	private RequestRepository requestRepository;
	private CustomerRepository customerRepository;
	private EmployeeRepository employeeRepository;
	private PetRepository petRepository;
	private ReceiptRepository receiptRepository;

	@GetMapping("/request")
	public List<Request> getAllRequests() {
		return requestRepository.findAll();
	}

	@PostMapping("/request")
	public void postRequest(@RequestBody Request request) {
		requestRepository.save(request);
	}

	@GetMapping("/request/single/{id}")
	public Request getSingleRequestById(@PathVariable("id") Long id) {
		Optional<Request> optional = requestRepository.findById(id);
		if (!optional.isPresent())
			throw new RuntimeException("Unable to find request ID");
		return optional.get();
	}

	// delete request
	@DeleteMapping("/request/{id}")
	public void deleteRequestById(@PathVariable("id") Long id) {
		requestRepository.deleteById(id);
	}

	// update request
	@PutMapping("/request/{id}/{cid}/{pid}/{eid}")
	public void updateRequest(@RequestBody Request request, @PathVariable("id") Long id, @PathVariable("cid") Long cid,
			@PathVariable("pid") Long pid, @PathVariable("eid") Long eid) {
		Request old = getSingleRequestById(id);
		Optional<Customer> optionalC = customerRepository.findById(cid);
		Optional<Pet> optionalP = petRepository.findById(pid);
		Optional<Employee> optionalE = employeeRepository.findById(eid);
		if (!optionalC.isPresent())
			throw new RuntimeException("Unable to find customer ID");
		if (!optionalP.isPresent())
			throw new RuntimeException("Unable to find pet ID");
		if (!optionalE.isPresent())
			throw new RuntimeException("Unable to find employee ID");
		Customer customer = optionalC.get();
		Pet pet = optionalP.get();
		Employee employee = optionalE.get();
		old.setCustomer(customer);
		old.setDate(request.getDate());
		old.setEmployee(employee);
		old.setPet(pet);
		old.setStatus(request.getStatus());
		requestRepository.save(old);
	}

	// approve request
	@PutMapping("/request/approve/{id}")
	public void approveRequest(@PathVariable("id") Long id) {
		Request request = getSingleRequestById(id);
		request.setStatus(Status.Approved);
		requestRepository.save(request);
				
		Receipt receipt = new Receipt();
		receipt.setCost(request.getPet().getCost());
		receipt.setCustomer_id(request.getCustomer().getId());
		receipt.setEmployee_id(request.getEmployee().getId());
		receipt.setDate(LocalTime.now().toString());
		receipt.setRequest_id(id);
		receiptRepository.save(receipt);
	}

	// deny request
	@PutMapping("/request/reject/{id}")
	public void rejectRequest(@PathVariable("id") Long id) {
		Request request = getSingleRequestById(id);
		request.setStatus(Status.Rejected);
		requestRepository.save(request);
	}

	// find all requests by pet
	@GetMapping("/request/pet/{id}")
	public List<Request> getRequestByPetId(@PathVariable("id") Long id) {
		return requestRepository.findByPetId(id);
	}

	// find all requests by employee
	@GetMapping("/request/employee/{id}")
	public List<Request> getRequestByEmployeeId(@PathVariable("id") Long id) {
		return requestRepository.findByEmployeeId(id);
	}

	// find all requests by customer
	@GetMapping("/request/customer/{id}")
	public List<Request> getRequestByCustomerId(@PathVariable("id") Long id) {
		return requestRepository.findByCustomerId(id);
	}

	// find all requests by status type
	@GetMapping("/request/status/{id}")
	public List<Request> getRequestByStatusType(@PathVariable("type") String type) {
		return requestRepository.findByStatusType(type);
	}

	// before date
	@GetMapping("/request/before")
	public List<Request> getRequestBeforeDate(@RequestBody Date date) {
		return requestRepository.findBeforeDate(date);
	}

	// after date
	@GetMapping("/request/after")
	public List<Request> getRequestAfterDate(@RequestBody Date date) {
		return requestRepository.findAfterDate(date);
	}

	// between dates
	@GetMapping("/request/between")
	public List<Request> getRequestBetweenDate(@RequestBody Date date, @RequestBody Date date1) {
		return requestRepository.findBetweenDate(date, date1);
	}
}
