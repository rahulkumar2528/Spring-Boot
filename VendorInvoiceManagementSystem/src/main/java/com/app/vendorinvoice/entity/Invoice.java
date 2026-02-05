package com.app.vendorinvoice.entity;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Table(name = "invoice")
@Entity
public class Invoice {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long invoiceId;
	
	@Column(name = "invoice_number", length = 20, nullable = false, unique = true)
    private String invoiceNumber;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vendor_id", nullable = false)
    private Vendor vendor;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "po_id", nullable = false)
    private PurchaseOrder purchaseOrder;
	
    private OffsetDateTime invoiceDate; 
    
    @Column(name = "invoice_amount", precision = 15, scale = 2)
    private BigDecimal invoiceAmount;
    
    @Column(name = "tax_amount", precision = 15, scale = 2)
    private BigDecimal taxAmount;
    
    @Column(name = "total_amount", precision = 15, scale = 2)
    private BigDecimal  totalAmount;
    
    @Column(name = "status")
    private Short status;
    
    @Column(name = "created_by", length = 15)
    private String createdBy;
    
    @Column(name = "created_date")
    private OffsetDateTime createdDate;
}
