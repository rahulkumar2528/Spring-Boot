package com.app.vendorinvoice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.vendorinvoice.entity.InvoiceError;

public interface InvoiceErrorRepository extends JpaRepository<InvoiceError, Long> {

}
