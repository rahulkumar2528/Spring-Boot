package com.app.vendorinvoice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.vendorinvoice.entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

}
