package com.app.vendorinvoice.entity;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "purchase_order_items")
@Data
public class PurchaseOrderItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "po_id", nullable = false)
	private PurchaseOrder purchaseOrder;
	
	@Column(name = "product_name", length = 255, nullable = false)
	private String productName;
	
	@Column(name = "quantity", nullable = false)
	private Integer quantity;
	
	@Column(name = "unit_price", nullable = false, precision = 15, scale = 2)
	private BigDecimal unitPrice;
	
	@Column(name = "line_amount", nullable = false, precision = 15, scale = 2)
	private BigDecimal lineAmount;
	
	@PrePersist
	@PreUpdate
	public void calculateLineAmt() {
		this.lineAmount = unitPrice.multiply(BigDecimal.valueOf(this.quantity));
	}
}
