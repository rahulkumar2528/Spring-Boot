package com.app.vendorinvoice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.vendorinvoice.entity.Vendor;

public interface VendorRepository extends JpaRepository<Vendor, Long> {

	boolean existsByVendorCode(String vendorCode);

	Optional<Vendor> findByVendorCode(String vendorCode);
}
