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
import com.sprinboot.backend.exceptions.MissingIDException;
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

	/*
	 * End point to receive all requests
	 * @return List<Request> - all requests
	 */
	@GetMapping("/request")
	public List<Request> getAllRequests() {
		return requestRepository.findAll();
	}

	/*
	 * End point to post a new request
	 * @return Request - newly created object
	 */
	@PostMapping("/request/{cid}/{pid}/{eid}")
	public Request postRequest(@RequestBody Request request, @PathVariable("cid") Long cid,
			@PathVariable("pid") Long pid, @PathVariable("eid") Long eid) {
		if (request.getDate() == null) // If no date was entered, make it today
			request.setDate(LocalDate.now());
		if (request.getStatus() == null) // If not status was entered, make it pending
			request.setStatus(Status.Pending);
		// Add the customer, pet, and employee objects to the request
		fixRequest(request, cid, pid, eid);
		return requestRepository.save(request); // save request
	}

	/*
	 * Finds a single request that matches an ID
	 * @return Request - resulting object
	 * Throws MissingIDException if ID not found
	 */
	@GetMapping("/request/{id}")
	public Request getSingleRequestById(@PathVariable("id") Long id) {
		Optional<Request> optional = requestRepository.findById(id);
		if (!optional.isPresent())
			throw new MissingIDException("Unable to find request ID");
		return optional.get();
	}

	/*
	 * Deletes a single request from the DB
	 */
	@DeleteMapping("/request/{id}")
	public void deleteRequestById(@PathVariable("id") Long id) {
		requestRepository.deleteById(id);
	}

	/*
	 * Helper method: adds customer, pet, and employee to a request
	 * Throws MissingIDException if any of the three IDs can't be found in the DB
	 */
	private void fixRequest(Request old, Long cid, Long pid, Long eid) {
		Optional<Customer> optionalC = customerRepository.findById(cid);
		Optional<Pet> optionalP = petRepository.findById(pid);
		Optional<Employee> optionalE = employeeRepository.findById(eid);
		if (!optionalC.isPresent())
			throw new MissingIDException("Unable to find customer ID");
		if (!optionalP.isPresent())
			throw new MissingIDException("Unable to find pet ID");
		if (!optionalE.isPresent())
			throw new MissingIDException("Unable to find employee ID");
		old.setCustomer(optionalC.get());
		old.setEmployee(optionalE.get());
		old.setPet(optionalP.get());
	}

	/*
	 * Updates a request with new data
	 * @return updated request
	 */
	@PutMapping("/request/{id}/{cid}/{pid}/{eid}")
	public Request updateRequest(@RequestBody Request request, @PathVariable("id") Long id, @PathVariable("cid") Long cid,
			@PathVariable("pid") Long pid, @PathVariable("eid") Long eid) {
		Request old = getSingleRequestById(id); // find the request
		fixRequest(old, cid, pid, eid); // Save new customer, employee, and/or pet
		if (request.getDate() != null) // if date provided
			old.setDate(request.getDate());
		if (request.getStatus() != null) // if status provided
			old.setStatus(request.getStatus());
		return requestRepository.save(old); // save updated request
	}

	/*
	 * Approve a request that matches an id
	 * then create a new receipt
	 * @return newly-created receipt
	 */
	@PutMapping("/request/approve/{id}")
	public Receipt approveRequest(@PathVariable("id") Long id) {
		Request request = getSingleRequestById(id); // find the request
		request.setStatus(Status.Approved); // change status to approved
		requestRepository.save(request); // save to DB
		// create receipt object
		Receipt receipt = new Receipt();
		receipt.setCost(request.getPet().getCost());
		receipt.setCustomer_id(request.getCustomer().getId());
		receipt.setEmployee_id(request.getEmployee().getId());
		receipt.setDate(LocalDate.now());
		receipt.setRequest_id(id);
		return receiptRepository.save(receipt); // save receipt
	}

	/*
	 * Reject a request that matches an ID
	 * @return updated request
	 */
	@PutMapping("/request/reject/{id}")
	public Request rejectRequest(@PathVariable("id") Long id) {
		Request request = getSingleRequestById(id); // find the request
		request.setStatus(Status.Rejected); // change status to rejected
		return requestRepository.save(request); // save request
	}

	/*
	 * Find requests with a particular pet ID
	 * @return List<Request>
	 */
	@GetMapping("/request/pet/{id}")
	public List<Request> getRequestByPetId(@PathVariable("id") Long id) {
		return requestRepository.findByPetId(id);
	}

	/*
	 * Find requests with a particular employee ID
	 * @return List<Request>
	 */	@GetMapping("/request/employee/{id}")
	public List<Request> getRequestByEmployeeId(@PathVariable("id") Long id) {
		return requestRepository.findByEmployeeId(id);
	}

	/*
	 * Find requests with a particular customer ID
	 * @return List<Request>
	 */
	@GetMapping("/request/customer/{id}")
	public List<Request> getRequestByCustomerId(@PathVariable("id") Long id) {
		return requestRepository.findByCustomerId(id);
	}

	/*
	 * Find requests with a particular status
	 * @return List<Request>
	 */
	@GetMapping("/request/status/{type}")
	public List<Request> getRequestByStatusType(@PathVariable("type") String type) {
		String strType = type.toString().toLowerCase();
		strType = strType.substring(0, 1).toUpperCase() + strType.substring(1);
		return requestRepository.findByStatusType(Status.valueOf(strType));
	}

	/*
	 * Find requests before a date
	 * @return List<Request>
	 */
	@GetMapping("/request/before")
	public List<Request> getRequestBeforeDate(@RequestParam("date") String date) {
		return requestRepository.findBeforeDate(LocalDate.parse(date));
	}

	/*
	 * Find requests after a date
	 * @return List<Request>
	 */
	@GetMapping("/request/after")
	public List<Request> getRequestAfterDate(@RequestParam("date") String date) {
		return requestRepository.findAfterDate(LocalDate.parse(date));
	}

	/*
	 * Find requests between two dates
	 * @return List<Request>
	 */
	@GetMapping("/request/between")
	public List<Request> getRequestBetweenDate(@RequestParam("from") String date, @RequestParam("to") String date1) {
		return requestRepository.findBetweenDate(LocalDate.parse(date), LocalDate.parse(date1));
	}
}
