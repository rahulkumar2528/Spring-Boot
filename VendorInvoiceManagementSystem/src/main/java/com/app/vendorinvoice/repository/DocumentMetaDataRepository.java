package com.app.vendorinvoice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.vendorinvoice.entity.DocumentMetaData;

public interface DocumentMetaDataRepository extends JpaRepository<DocumentMetaData, Long> {

}
