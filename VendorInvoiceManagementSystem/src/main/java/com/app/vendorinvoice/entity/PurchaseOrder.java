package com.app.vendorinvoice.entity;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Data;

@Entity
@Table(name = "purchase_order", uniqueConstraints = {
		@UniqueConstraint(name = "purchase_order_po_number_key", columnNames= "po_number") })
@Data
public class PurchaseOrder {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "po_id")
	private Long poId;
	
	@Column(name = "po_number", length = 20, nullable = false)
	private String poNumber;
	
	@Column(name = "vendor_id")
	private Long vendorId;
	
	@Column(name = "po_date")
	private OffsetDateTime poDate;
	
	@Column(name = "total_po_amount", precision = 15,  scale = 2)
	private BigDecimal totalPoAmount;
	
	@Column(name = "remaining_amount", precision = 15, scale = 2)
	private BigDecimal remainingAmount;
	
	@Column(name = "status")
	private Short status;
	
	@Column(name = "created_by", length = 15)
	private String createdBy;
	
	@Column(name = "created_date")
	private OffsetDateTime createdDate;
}
