package com.sprinboot.backend.controller;

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

import com.sprinboot.backend.dto.ReceiptDto;
import com.sprinboot.backend.exceptions.MissingEntryException;
import com.sprinboot.backend.model.Customer;
import com.sprinboot.backend.model.Employee;
import com.sprinboot.backend.model.Receipt;
import com.sprinboot.backend.model.Request;
import com.sprinboot.backend.model.UserProfile;
import com.sprinboot.backend.repository.CustomerRepository;
import com.sprinboot.backend.repository.EmployeeRepository;
import com.sprinboot.backend.repository.ReceiptRepository;
import com.sprinboot.backend.repository.RequestRepository;
import com.sprinboot.backend.repository.UserRepository;

@RestController
public class ReceiptController {
	@Autowired
	private ReceiptRepository receiptRepository;
	@Autowired
	private UserRepository customerRepository;
	@Autowired
	private EmployeeRepository employeeRepository;
	@Autowired
	private RequestRepository requestRepository;


	private ReceiptDto convertToDto(Receipt r) {
		ReceiptDto dto = new ReceiptDto();
	//	dto.setC_birthday(r.getCustomer().getBirthday());
	//	dto.setC_date_joined(r.getCustomer().getDate_joined());
		dto.setC_id(r.getCustomer().getId());
	//	dto.setC_phone(r.getCustomer().getPhone());
		dto.setC_name(r.getCustomer().getNickname());
		dto.setCost(r.getCost());
		dto.setDate(r.getDate());
		dto.setId(r.getId());
		dto.setR_id(r.getRequest().getId());
		dto.setR_date(r.getRequest().getDate());
		dto.setE_id(r.getEmployee().getId());
		dto.setE_name(r.getEmployee().getName());
		dto.setP_breed(r.getRequest().getPet().getBreed());
		dto.setP_age(r.getRequest().getPet().getAge());
		dto.setP_color(r.getRequest().getPet().getColor());
		dto.setP_id(r.getRequest().getPet().getId());
		dto.setP_date_acquired(r.getRequest().getPet().getDate_acquired());
		dto.setP_name(r.getRequest().getPet().getName());
		dto.setP_neutered(r.getRequest().getPet().isNeutered());
		dto.setP_vaccinated(r.getRequest().getPet().isVaccinated());
		dto.setP_species(r.getRequest().getPet().getSpecies());
		dto.setP_sex(r.getRequest().getPet().getSex());
		return dto;
	}

	private List<ReceiptDto> convertToDtoList(List<Receipt> list) {
		List<ReceiptDto> dtoList = new ArrayList<>();
		for (Receipt r : list) {
			dtoList.add(convertToDto(r));
		}
		return dtoList;
	}

	private void fixReceipt(Receipt old, Long cid, Long eid, Long rid) {
		Optional<UserProfile> optionalC = customerRepository.findById(cid);
		Optional<Employee> optionalE = employeeRepository.findById(eid);
		Optional<Request> optionalR = requestRepository.findById(rid);
		if (!optionalC.isPresent())
			throw new MissingEntryException("Unable to find customer ID");
		if (!optionalE.isPresent())
			throw new MissingEntryException("Unable to find employee ID");
		if (!optionalR.isPresent())
			throw new MissingEntryException("Unable to find request ID");
		old.setCustomer(optionalC.get());
		old.setEmployee(optionalE.get());
		old.setRequest(optionalR.get());
	}

	@PostMapping("/receipt/{cid}/{eid}/{rid}")
	public ReceiptDto postReceipt(@RequestBody Receipt receipt, @PathVariable("cid") Long cid,
			@PathVariable("eid") Long eid, @PathVariable("rid") Long rid) {
		if (receipt.getDate() == null)
			receipt.setDate(LocalDate.now());
		fixReceipt(receipt, cid, eid, rid);
		return convertToDto(receiptRepository.save(receipt));
	}

	@GetMapping("/receipt")
	public List<ReceiptDto> getAllReceipt() {
		return convertToDtoList(receiptRepository.findAll());
	}

	@GetMapping("/receipt/{id}")
	public ReceiptDto getReceiptDtoById(@PathVariable("id") Long id) {
		return convertToDto(getReceiptById(id));
	}

	private Receipt getReceiptById(Long id) {
		Optional<Receipt> op = receiptRepository.findById(id);
		if (!op.isPresent())
			throw new RuntimeException("Could not find receipt with id " + id);
		return op.get();
	}

	@DeleteMapping("/receipt/{id}")
	public void deleteReceiptById(@PathVariable("id") Long id) {
		receiptRepository.deleteById(id);
	}

	@PutMapping("/receipt/{id}/{cid}/{pid}/{eid}")
	public ReceiptDto updateReceipt(@RequestBody Receipt receipt, @PathVariable("id") Long id,
			@PathVariable("cid") Long cid, @PathVariable("eid") Long eid, @PathVariable("rid") Long rid) {
		Receipt old = getReceiptById(id); // find the request
		fixReceipt(old, cid, eid, rid); // Save new customer, employee, and/or pet
		if (receipt.getDate() != null) // if date provided
			old.setDate(receipt.getDate());
		old.setCost(receipt.getCost());
		return convertToDto(receiptRepository.save(old)); // save updated request
	}
	
	@GetMapping("/receipt/employee/{id}")
	public List<ReceiptDto> getReceiptByEmployeeId(@PathVariable("id") Long id) {
		return convertToDtoList(receiptRepository.findByEmployeeId(id));
	}
	
	@GetMapping("/receipt/customer/{id}")
	public List<ReceiptDto> getReceiptByCustomerId(@PathVariable("id") Long id) {
		return convertToDtoList(receiptRepository.findByCustomerId(id));
	}
	
	@GetMapping("/receipt/request/{id}")
	public List<ReceiptDto> getReceiptByRequestId(@PathVariable("id") Long id) {
		return convertToDtoList(receiptRepository.findByRequestId(id));
	}
	
	@GetMapping("/receipt/greaterThan/{price}")
	public List<ReceiptDto> getReceiptByPriceGreaterThan(@PathVariable("price") Double price) {
		return convertToDtoList(receiptRepository.findByPriceGreaterThan(price));
	}
	
	@GetMapping("/receipt/lessThan/{price}")
	public List<ReceiptDto> getReceiptByPriceLessThan(@PathVariable("price") Double price) {
		return convertToDtoList(receiptRepository.findByPriceLessThan(price));
	}
	
	@GetMapping("/receipt/range/{price1}/{price2}")
	public List<ReceiptDto> getReceiptByPriceGreaterThan(@PathVariable("price1") Double price1, @PathVariable("price2") Double price2) {
		return convertToDtoList(receiptRepository.findByPriceBetween(price1, price2));
	}

	@GetMapping("/receipt/equalTo/{price}")
	public List<ReceiptDto> getReceiptByPriceEqualTo(@PathVariable("price") Double price) {
		return convertToDtoList(receiptRepository.findByPriceEqualTo(price));
	}
	
	@GetMapping("/receipt/at")
	public List<ReceiptDto> getRequestOnDate(@RequestParam("date") String date) {
		return convertToDtoList(receiptRepository.findOnDate(LocalDate.parse(date)));
	}
	
	@GetMapping("/receipt/before")
	public List<ReceiptDto> getRequestBeforeDate(@RequestParam("date") String date) {
		return convertToDtoList(receiptRepository.findBeforeDate(LocalDate.parse(date)));
	}

	@GetMapping("/receipt/after")
	public List<ReceiptDto> getRequestAfterDate(@RequestParam("date") String date) {
		return convertToDtoList(receiptRepository.findAfterDate(LocalDate.parse(date)));
	}

	@GetMapping("/receipt/between")
	public List<ReceiptDto> getRequestBetweenDate(@RequestParam("from") String date, @RequestParam("to") String date1) {
		return convertToDtoList(receiptRepository.findBetweenDate(LocalDate.parse(date), LocalDate.parse(date1)));
	}
	
}
