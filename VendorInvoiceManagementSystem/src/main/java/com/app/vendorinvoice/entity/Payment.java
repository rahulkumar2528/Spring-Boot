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

@Entity
@Table(name = "payment")
public class Payment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "payment_id")
	private Long paymentId;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "invoice_id", nullable = false)
	private Invoice invoice;

	@Column(name = "payment_date")
	private OffsetDateTime paymentDate;

	@Column(name = "paid_amount", precision = 15, scale = 2, nullable = false)
	private BigDecimal paidAmount;

	@Column(name = "payment_mode", length = 50, nullable = false)
	private String paymentMode;

	@Column(name = "transaction_reference", length = 50)
	private String transactionReference;

	@Column(name = "status")
	private Short status;

}
