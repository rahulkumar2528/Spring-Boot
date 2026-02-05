package com.app.vendorinvoice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.vendorinvoice.entity.Invoice;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {

}
