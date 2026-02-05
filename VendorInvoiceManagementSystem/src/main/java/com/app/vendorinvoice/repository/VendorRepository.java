package com.app.vendorinvoice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.vendorinvoice.entity.Vendor;

public interface VendorRepository extends JpaRepository<Vendor, Long> {

}
