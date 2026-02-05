package com.app.vendorinvoice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.vendorinvoice.entity.InvoiceApproval;

public interface InvoiceApprovalRepository extends JpaRepository<InvoiceApproval, Long> {

}
