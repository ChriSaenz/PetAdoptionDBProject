package com.sprinboot.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sprinboot.backend.model.Receipt;

public interface ReceiptRepository extends JpaRepository<Receipt, Long> {

}
