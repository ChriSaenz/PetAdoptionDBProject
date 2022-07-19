package com.sprinboot.backend.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;

import com.sprinboot.backend.model.Receipt;
import com.sprinboot.backend.repository.ReceiptRepository;

@RestController
public class ReceiptController {
	@Autowired
	private ReceiptRepository receiptRepository;
	
	@PostMapping("/receipt")
	public void postReceipt(@RequestBody Receipt receipt) {
		receiptRepository.save(receipt);
	}
	
	@GetMapping("/receipt")
	public List<Receipt> getAllReceipt() {
		return receiptRepository.findAll();
	}
	
	@GetMapping("/receipt/{id}")
	public Receipt getReceiptById(@PathVariable("id") Long id) {
		Optional<Receipt> op = receiptRepository.findById(id);
		if(op.isPresent())
			return op.get();
		else
			throw new RuntimeException("Could not find receipt with id " + id);
	}
}
