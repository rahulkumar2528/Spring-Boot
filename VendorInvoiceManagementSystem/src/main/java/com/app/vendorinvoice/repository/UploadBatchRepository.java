package com.app.vendorinvoice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.vendorinvoice.entity.UploadBatch;

public interface UploadBatchRepository extends JpaRepository<UploadBatch, Long> {

}
