package com.app.vendorinvoice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.vendorinvoice.entity.PurchaseOrderItem;

public interface PurchaseOrderItemRepository extends JpaRepository<PurchaseOrderItem, Long> {

}
