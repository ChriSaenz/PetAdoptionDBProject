package com.sprinboot.backend.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
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
	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private EmployeeRepository employeeRepository;
	@Autowired
	private PetRepository petRepository;
	@Autowired
	private ReceiptRepository receiptRepository;

	// tested: works
	@GetMapping("/request")
	public List<Request> getAllRequests() {
		return requestRepository.findAll();
	}

	// tested: works
	@PostMapping("/request/{cid}/{pid}/{eid}")
	public Request postRequest(@RequestBody Request request, @PathVariable("cid") Long cid,
			@PathVariable("pid") Long pid, @PathVariable("eid") Long eid) {
		if (request.getDate() == null)
			request.setDate(LocalDate.now());
		if (request.getStatus() == null)
			request.setStatus(Status.Pending);
		return requestRepository.save(fixRequest(request, cid, pid, eid));
	}

	// tested: works
	@GetMapping("/request/single/{id}")
	public Request getSingleRequestById(@PathVariable("id") Long id) {
		Optional<Request> optional = requestRepository.findById(id);
		if (!optional.isPresent())
			throw new RuntimeException("Unable to find request ID");
		return optional.get();
	}

	// tested: works
	// delete request
	@DeleteMapping("/request/{id}")
	public void deleteRequestById(@PathVariable("id") Long id) {
		requestRepository.deleteById(id);
	}

	// helper method
	private Request fixRequest(Request old, Long cid, Long pid, Long eid) {
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
		old.setEmployee(employee);
		old.setPet(pet);
		return old;
	}

	// tested: works
	// update request
	@PutMapping("/request/{id}/{cid}/{pid}/{eid}")
	public void updateRequest(@RequestBody Request request, @PathVariable("id") Long id, @PathVariable("cid") Long cid,
			@PathVariable("pid") Long pid, @PathVariable("eid") Long eid) {
		Request old = getSingleRequestById(id);
		fixRequest(old, cid, pid, eid);
		if (request.getDate() != null)
			old.setDate(request.getDate());
		if (request.getStatus() != null)
			old.setStatus(request.getStatus());
		requestRepository.save(old);
	}

	// tested: works
	// approve request
	@PutMapping("/request/approve/{id}")
	public Request approveRequest(@PathVariable("id") Long id) {
		Request request = getSingleRequestById(id);
		request.setStatus(Status.Approved);
		requestRepository.save(request);

		Receipt receipt = new Receipt();
		receipt.setCost(request.getPet().getCost());
		receipt.setCustomer_id(request.getCustomer().getId());
		receipt.setEmployee_id(request.getEmployee().getId());
		receipt.setDate(LocalDate.now());
		receipt.setRequest_id(id);
		receiptRepository.save(receipt);
		return requestRepository.save(request);
	}

	// tested: works
	// deny request
	@PutMapping("/request/reject/{id}")
	public Request rejectRequest(@PathVariable("id") Long id) {
		Request request = getSingleRequestById(id);
		request.setStatus(Status.Rejected);
		return requestRepository.save(request);
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

	// tested: works
	// find all requests by customer
	@GetMapping("/request/customer/{id}")
	public List<Request> getRequestByCustomerId(@PathVariable("id") Long id) {
		return requestRepository.findByCustomerId(id);
	}

	// tested: works
	// find all requests by status type
	@GetMapping("/request/status/{type}")
	public List<Request> getRequestByStatusType(@PathVariable("type") String type) {
		String strType = type.toString().toLowerCase();
		strType = strType.substring(0, 1).toUpperCase() + strType.substring(1);
		return requestRepository.findByStatusType(Status.valueOf(strType));
	}

	// tested: works
	// before date
	@GetMapping("/request/before")
	public List<Request> getRequestBeforeDate(@RequestParam("date") String date) {
		return requestRepository.findBeforeDate(LocalDate.parse(date));
	}

	// tested: works
	// after date
	@GetMapping("/request/after")
	public List<Request> getRequestAfterDate(@RequestParam("date") String date) {
		return requestRepository.findAfterDate(LocalDate.parse(date));
	}

	// tested: works
	// between dates
	@GetMapping("/request/between")
	public List<Request> getRequestBetweenDate(@RequestParam("from") String date,
			@RequestParam("to") String date1) {
		return requestRepository.findBetweenDate(LocalDate.parse(date), LocalDate.parse(date1));
	}
}
