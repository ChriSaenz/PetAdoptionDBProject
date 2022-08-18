/**
* RequestController handles enpoints related to
* creating and retrieving requests in the DB
*
* @author  Felix Taylor
*/
package com.sprinboot.backend.controller;

import java.security.Principal;
import java.time.LocalDate;
import java.util.ArrayList;
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

import com.sprinboot.backend.dto.RequestDto;
import com.sprinboot.backend.dto.UserEditDto;
import com.sprinboot.backend.enums.Status;
import com.sprinboot.backend.exceptions.InvalidEntryException;
import com.sprinboot.backend.exceptions.MissingEntryException;
import com.sprinboot.backend.model.Customer;
import com.sprinboot.backend.model.Employee;
import com.sprinboot.backend.model.Pet;
import com.sprinboot.backend.model.Receipt;
import com.sprinboot.backend.model.Request;
import com.sprinboot.backend.model.UserProfile;
import com.sprinboot.backend.repository.CustomerRepository;
import com.sprinboot.backend.repository.EmployeeRepository;
import com.sprinboot.backend.repository.PetRepository;
import com.sprinboot.backend.repository.ReceiptRepository;
import com.sprinboot.backend.repository.RequestRepository;
import com.sprinboot.backend.repository.UserRepository;

@RestController
public class RequestController {

	@Autowired
	private RequestRepository requestRepository;
	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private EmployeeRepository employeeRepository;
	@Autowired
	private PetRepository petRepository;
	@Autowired
	private ReceiptRepository receiptRepository;
	
	/*
	 * Converts a request to a DTO
	 * @param request object
	 * @return resulting DTO
	 */
	private RequestDto convertToDto(Request request) {
		RequestDto dto = new RequestDto();
		dto.setId(request.getId());
		dto.setStatus(request.getStatus());
		dto.setDate(request.getDate());
		dto.setC_id(request.getCustomer().getId());
		dto.setC_name(request.getCustomer().getNickname());
//		dto.setC_phone(request.getCustomer().getPhone());
//		dto.setC_date_joined(request.getCustomer().getDate_joined());
//		dto.setC_birthday(request.getCustomer().getBirthday());
		dto.setP_id(request.getPet().getId());
		dto.setP_name(request.getPet().getName());
		dto.setP_age(request.getPet().getAge());
		dto.setP_date_acquired(request.getPet().getDate_acquired());
		dto.setP_species(request.getPet().getSpecies());
		dto.setP_color(request.getPet().getColor());
		dto.setP_breed(request.getPet().getBreed());
		dto.setP_neutered(request.getPet().isNeutered());
		dto.setP_vaccinated(request.getPet().isVaccinated());
		dto.setP_sex(request.getPet().getSex());
		dto.setP_cost(request.getPet().getCost());
		if(request.getEmployee() != null) {
			dto.setE_id(request.getEmployee().getId());
			dto.setE_name(request.getEmployee().getNickname());
		}
		return dto;
	}
	
	/*
	 * Converts a list of requests to a list of DTOs
	 * @param list of requests
	 * @return list of dtos
	 */
	private List<RequestDto> convertListToDto(List<Request> list) {
		List<RequestDto> dtoList = new ArrayList<>();
		for (Request r : list) {
			dtoList.add(convertToDto(r));
		}
		return dtoList;
	}

	/*
	 * End point to receive all request DTOs
	 * @return List<RequestDto> - all request DTOs
	 */
	@GetMapping("/request")
	public List<RequestDto> getAllRequests() {
		return convertListToDto(requestRepository.findAll());
	}

	
	
	/*
	 * End point to post a new request
	 * @return RequestDto - newly created object
	 */
	@PostMapping("/request/{cid}/{pid}/{eid}")
	public RequestDto postRequest(@RequestBody Request request, @PathVariable("cid") Long cid,
			@PathVariable("pid") Long pid, @PathVariable("eid") Long eid) {
		if (request.getDate() == null) // If no date was entered, make it today
			request.setDate(LocalDate.now());
		if (request.getStatus() == null) // If not status was entered, make it pending
			request.setStatus(Status.Pending);
		// Add the customer, pet, and employee objects to the request
		fixRequest(request, cid, pid, eid);
		return convertToDto(requestRepository.save(request)); // save request
	}
	@PostMapping("/request/adoption/{uid}/{pid}/{eid}")
	public RequestDto postRequestAdoption(@RequestBody Request request, @PathVariable("uid") Long uid,
			@PathVariable("pid") Long pid, @PathVariable("eid") Long eid) {
		if (request.getDate() == null) // If no date was entered, make it today
			request.setDate(LocalDate.now());
		if (request.getStatus() == null) // If not status was entered, make it pending
			request.setStatus(Status.Pending);
		// Add the customer, pet, and employee objects to the request
		Optional<UserProfile> optionalU = userRepository.findById(uid);
		Optional<Pet> optionalP = petRepository.findById(pid);
		Optional<UserProfile> optionalE = userRepository.findById(eid);
		if (!optionalU.isPresent())
			throw new MissingEntryException("Unable to find user ID");
		if (!optionalP.isPresent())
			throw new MissingEntryException("Unable to find pet ID");
		if (!optionalE.isPresent())
			throw new MissingEntryException("Unable to find employee ID");
		request.setCustomer(optionalU.get());
		request.setEmployee(optionalE.get());
		request.setPet(optionalP.get());
		return convertToDto(requestRepository.save(request)); // save request
	}
		
	
	/*
	 * Retrieves a request by its ID
	 * @param id
	 * @return resulting request
	 */
	private Request getRequestById(Long id) {
		Optional<Request> optional = requestRepository.findById(id);
		if (!optional.isPresent())
			throw new MissingEntryException("Unable to find request ID");
		return optional.get();
	}

	/*
	 * Finds a single request that matches an ID
	 * @return RequestDto - resulting object
	 * Throws MissingIDException if ID not found
	 */
	@GetMapping("/request/{id}")
	public RequestDto getRequestDtoById(@PathVariable("id") Long id) {
		Optional<Request> op = requestRepository.findById(id);
		if (!op.isPresent())
			throw new MissingEntryException("Unable to find request ID");
		return convertToDto(op.get());
	}

	// Deletes a single request from the DB
	@DeleteMapping("/request/{id}")
	public void deleteRequestById(@PathVariable("id") Long id) {
		requestRepository.deleteById(id);
	}

	/*
	 * Helper method: adds customer, pet, and employee to a request
	 * Throws MissingIDException if any of the three IDs can't be found in the DB
	 */
	private void fixRequest(Request old, Long cid, Long pid, Long eid) {
		Optional<UserProfile> optionalC = userRepository.findById(cid);
		Optional<Pet> optionalP = petRepository.findById(pid);
		Optional<UserProfile> optionalE = userRepository.findById(eid);
		if (!optionalC.isPresent())
			throw new MissingEntryException("Unable to find customer ID");
		if (!optionalP.isPresent())
			throw new MissingEntryException("Unable to find pet ID");
		if (!optionalE.isPresent())
			throw new MissingEntryException("Unable to find employee ID");
		old.setCustomer(optionalC.get());
		old.setEmployee(optionalE.get());
		old.setPet(optionalP.get());
	}

	/*
	 * Updates a request with new data
	 * @param request ID
	 * @param customer ID
	 * @param pet ID
	 * @param employee ID
	 * @return updated request DTO
	 */
	@PutMapping("/request/{id}/{cid}/{pid}/{eid}")
	public RequestDto updateRequest(@RequestBody Request request, @PathVariable("id") Long id, @PathVariable("cid") Long cid,
			@PathVariable("pid") Long pid, @PathVariable("eid") Long eid) {
		Request old = getRequestById(id); // find the request
		fixRequest(old, cid, pid, eid); // Save new customer, employee, and/or pet
		if (request.getDate() != null) // if date provided
			old.setDate(request.getDate());
		if (request.getStatus() != null) // if status provided
			old.setStatus(request.getStatus());
		return convertToDto(requestRepository.save(old)); // save updated request
	}

	/*
	 * Approve a request that matches an id
	 * then create a new receipt
	 * @param request ID
	 * @return newly-created receipt
	 */
	@PutMapping("/request/approve/{id}")
	public void approveRequest(@PathVariable("id") Long id) {
		Request request = getRequestById(id); // find the request
		request.setStatus(Status.Approved); // change status to approved
		requestRepository.save(request); // save to DB
		// create receipt object
		Receipt receipt = new Receipt();
		receipt.setCost(request.getPet().getCost());
		receipt.setCustomer(request.getCustomer());
		receipt.setEmployee(request.getEmployee());
		receipt.setDate(LocalDate.now());
		receipt.setRequest(request);
		receiptRepository.save(receipt); // save receipt
	}

	/*
	 * Reject a request that matches an ID
	 * @param request ID
	 * @return updated request DTO
	 */
	@PutMapping("/request/reject/{id}")
	public RequestDto rejectRequest(@PathVariable("id") Long id) {
		Request request = getRequestById(id); // find the request
		request.setStatus(Status.Rejected); // change status to rejected
		return convertToDto(requestRepository.save(request)); // save request
	}

	/*
	 * Find requests with a particular pet ID
	 * @param pet ID
	 * @return list of request DTOs
	 */
	@GetMapping("/request/pet/{id}")
	public List<RequestDto> getRequestByPetId(@PathVariable("id") Long id) {
		return convertListToDto(requestRepository.findByPetId(id));
	}

	/*
	 * Find requests with a particular employee ID
	 * @param employee ID
	 * @return list of request DTOs
	 */	
	@GetMapping("/request/employee/{id}")
	public List<RequestDto> getRequestByEmployeeId(@PathVariable("id") Long id) {
		return convertListToDto(requestRepository.findByEmployeeId(id));
	}

	/*
	 * Find requests with a particular customer ID
	 * @param customer ID
	 * @return list of request DTOs
	 */
	@GetMapping("/request/customer/{id}")
	public List<RequestDto> getRequestByCustomerId(@PathVariable("id") Long id) {
		return convertListToDto(requestRepository.findByCustomerId(id));
	}

	/*
	 * Find requests with a particular status
	 * @param status type
	 * @return list of request DTOs
	 */
	@GetMapping("/request/status/{type}")
	public List<RequestDto> getRequestByStatusType(@PathVariable("type") String type) {
		String strType = type.toString().toLowerCase();
		strType = strType.substring(0, 1).toUpperCase() + strType.substring(1);
		return convertListToDto(requestRepository.findByStatusType(Status.valueOf(strType)));
	}

	/*
	 * Find requests at certain date
	 * @param date
	 * @return list of request DTOs
	 */
	@GetMapping("/request/at")
	public List<RequestDto> getRequestAtDate(@RequestParam("date") String date) {
		return convertListToDto(requestRepository.findAtDate(LocalDate.parse(date)));
	}
	
	/*
	 * Find requests before a date
	 * @param date
	 * @return list of request DTOs
	 */
	@GetMapping("/request/before")
	public List<RequestDto> getRequestBeforeDate(@RequestParam("date") String date) {
		return convertListToDto(requestRepository.findBeforeDate(LocalDate.parse(date)));
	}

	/*
	 * Find requests after a date
	 * @param date
	 * @return list of request DTOs
	 */
	@GetMapping("/request/after")
	public List<RequestDto> getRequestAfterDate(@RequestParam("date") String date) {
		return convertListToDto(requestRepository.findAfterDate(LocalDate.parse(date)));
	}

	/*
	 * Find requests between two dates
	 * @param date - start date
	 * @param date1 - end date
	 * @return list of request DTOs
	 */
	@GetMapping("/request/between")
	public List<RequestDto> getRequestBetweenDate(@RequestParam("from") String date, @RequestParam("to") String date1) {
		return convertListToDto(requestRepository.findBetweenDate(LocalDate.parse(date), LocalDate.parse(date1)));
	}
	
	
	@GetMapping("/request/equalTo/{price}")
	public List<RequestDto> getRequestByPriceEqualTo(@PathVariable("price") Double price) {
		return convertListToDto(requestRepository.findByPriceEqualTo(price));
	}
	
	@GetMapping("/request/greaterThan/{price}")
	public List<RequestDto> getRequestByPriceGreaterThan(@PathVariable("price") Double price) {
		return convertListToDto(requestRepository.findByPriceGreaterThan(price));
	}
	
	@GetMapping("/request/lessThan/{price}")
	public List<RequestDto> getRequestByPriceLessThan(@PathVariable("price") Double price) {
		return convertListToDto(requestRepository.findByPriceLessThan(price));
	}
	
	@GetMapping("/request/range/{price1}/{price2}")
	public List<RequestDto> getRequestByPriceGreaterThan(@PathVariable("price1") Double price1, @PathVariable("price2") Double price2) {
		return convertListToDto(requestRepository.findByPriceBetween(price1, price2));
	}
	
	
	//Alter
	@PostMapping("/request-alter/adoption/{uid}/{pid}")
	public RequestDto postRequestAdoptionAlter(@RequestBody Request request, @PathVariable("uid") Long uid,
			@PathVariable("pid") Long pid) {
		if (request.getDate() == null) // If no date was entered, make it today
			request.setDate(LocalDate.now());
		if (request.getStatus() == null) // If not status was entered, make it pending
			request.setStatus(Status.Pending);
		// Add the customer, pet, and employee objects to the request
		Optional<UserProfile> optionalU = userRepository.findById(uid);
		Optional<Pet> optionalP = petRepository.findById(pid);
		if (!optionalU.isPresent())
			throw new MissingEntryException("Unable to find user ID");
		if (!optionalP.isPresent())
			throw new MissingEntryException("Unable to find pet ID");
		request.setCustomer(optionalU.get());
		request.setEmployee(null);
		request.setPet(optionalP.get());
		return convertToDto(requestRepository.save(request)); // save request
	}

	@PutMapping("/request-alter/approve/{id}")
	public void approveRequestAlter(Principal principal, @PathVariable("id") Long id) {
		Optional<UserProfile> optional = userRepository.getByUsername(principal.getName());
		if(!optional.isPresent())
			throw new InvalidEntryException("[approveRequestAlter] Username does not exist");
		
		UserProfile u = optional.get();
		
		Request request = getRequestById(id); // find the request
		request.setStatus(Status.Approved); // change status to approved
		request.setEmployee(u);
		requestRepository.save(request); // save to DB
		// create receipt object
		Receipt receipt = new Receipt();
		receipt.setCost(request.getPet().getCost());
		receipt.setCustomer(request.getCustomer());
		receipt.setEmployee(request.getEmployee());
		receipt.setDate(LocalDate.now());
		receipt.setRequest(request);
		receiptRepository.save(receipt); // save receipt
	}

	@PutMapping("/request-alter/reject/{id}")
	public RequestDto rejectRequestAlter(Principal principal, @PathVariable("id") Long id) {
		Optional<UserProfile> optional = userRepository.getByUsername(principal.getName());
		if(!optional.isPresent())
			throw new InvalidEntryException("[approveRequestAlter] Username does not exist");
		
		UserProfile u = optional.get();
		
		Request request = getRequestById(id); // find the request
		request.setStatus(Status.Rejected); // change status to rejected
		request.setEmployee(u);
		return convertToDto(requestRepository.save(request)); // save request
	}
	
	@GetMapping("/request-alter/pets-in-cart")
	public Integer[] getPetsInCarts()
	{
		return requestRepository.getPetsInCarts();
	}
}
